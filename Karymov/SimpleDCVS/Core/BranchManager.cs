using System.Collections.Generic;
using System.IO;

namespace Core
{
    public static class BranchManager
    {
        public static void WriteBranch(DirectoryInfo workspace, Branch branch)
        {
            string branchesDirPath = Path.Combine(workspace.FullName, Global.BranchesDirPath);
            string branchDirPath = Path.Combine(branchesDirPath, branch.Name);
            string branchMetaFilePath = Path.Combine(branchDirPath, Global.BranchMetaFilename);
            string xml = XmlUtility.Obj2XmlStr(branch);
            File.WriteAllText(branchMetaFilePath, xml);
        }

        public static Branch ReadBranch(DirectoryInfo workspace, string name)
        {
            string branchesDirPath = Path.Combine(workspace.FullName, Global.BranchesDirPath);
            string branchDirPath = Path.Combine(branchesDirPath, name);
            string branchMetaFilePath = Path.Combine(branchDirPath, Global.BranchMetaFilename);
            if (File.Exists(branchMetaFilePath))
            {
                string xml = File.ReadAllText(branchMetaFilePath);
                return XmlUtility.XmlStr2Obj<Branch>(xml);
            }
            return null;
        }

        public static void UpdateBranchHead(DirectoryInfo workspace, Branch b, CommitInfo c)
        {
            b.Head = c.Id;
            WriteBranch(workspace, b);
        }

        public static Branch CreateBranch(Branch parent, DirectoryInfo workspace, string name)
        {
            string branchesDirPath = Path.Combine(workspace.FullName, Global.BranchesDirPath);
            string branchDirPath = Path.Combine(branchesDirPath, name);

            if (!Directory.Exists(branchDirPath))
            {
                Directory.CreateDirectory(branchDirPath);
                string head = parent != null && parent.Head != null ? parent.Head : Global.MasterCommitName;
                var b = new Branch(name, head);
                WriteBranch(workspace, b);
                return b;
            }
            return null;
        }

        public static Branch CreateMaster(DirectoryInfo workspace)
        {
            return CreateBranch(null, workspace, Global.MasterBranchName);
        }

        public static List<Branch> GetAllBranches(DirectoryInfo workspace)
        {
            string branchesDirPath = Path.Combine(workspace.FullName, Global.BranchesDirPath);
            var branches = new List<Branch>();
            foreach (DirectoryInfo d in (new DirectoryInfo(branchesDirPath).GetDirectories()))
                branches.Add(ReadBranch(workspace, d.Name));
            return branches;
        }
    }
}