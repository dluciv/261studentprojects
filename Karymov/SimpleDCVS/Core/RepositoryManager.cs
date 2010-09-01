using System.IO;

namespace Core
{
    public static class RepositoryManager
    {
        //возвращает текущую рабочую папку
        private static DirectoryInfo GetCurrentWorkspaceDir()
        {
            string path = Global.RepositoryManagerConfigFilePath;
            string xml = File.ReadAllText(path);
            return new DirectoryInfo(XmlUtility.XmlStr2Obj<string>(xml));
        }

        private static void SetCurrentWorkspaceDir(DirectoryInfo workspace)
        {
            string path = Global.RepositoryManagerConfigFilePath;
            string xml = XmlUtility.Obj2XmlStr(workspace.FullName);
            File.WriteAllText(path, xml);
        }
        //возвращает настройки репозитория этой директории
        public static RepositoryConfig ReadRepositoryConfig(DirectoryInfo workspace)
        {
            string rConfFilePath = Path.Combine(workspace.FullName, Global.RepositoryConfigFilePath);
            if (File.Exists(rConfFilePath))
            {
                string xml = File.ReadAllText(rConfFilePath);
                return XmlUtility.XmlStr2Obj<RepositoryConfig>(xml);
            }
            return null;
        }
        
        public static void WriteRepositoryConfig(DirectoryInfo workspace, Repository r)
        {
            string rConfFilePath = Path.Combine(workspace.FullName, Global.RepositoryConfigFilePath);
            string xml = XmlUtility.Obj2XmlStr(r.GetConfiguration());
            File.WriteAllText(rConfFilePath, xml);
        }
        // создает новый репозиторий в этой директории
        public static Repository CreateNewRepository(DirectoryInfo workspace)
        {
            string rPath = Path.Combine(workspace.FullName, Global.RepositoryDirName);
            if (!Directory.Exists(rPath))
            {
                var r = new Repository(workspace);
                Directory.CreateDirectory(rPath);
                Directory.CreateDirectory(r.BranchesDir.FullName);
                Directory.CreateDirectory(r.SnapshotDir.FullName);
                r.CurrentBranch = BranchManager.CreateMaster(workspace);
                SnapshotInfo s = SnapshotManager.CreateSnapshot(workspace, null);
                CommitUtil.Commit(s, workspace, Global.InitialCommitMessage);
                SetCurrentWorkspaceDir(r.Workspace);
                WriteRepositoryConfig(workspace, r);
                return r;
            }
            return null;
        }

        public static Repository OpenRepository(DirectoryInfo workspace)
        {
            string rPath = Path.Combine(workspace.FullName, Global.RepositoryDirName);
            if (Directory.Exists(rPath))
            {
                RepositoryConfig conf = ReadRepositoryConfig(workspace);
                var r = new Repository(workspace);
                r.CurrentBranch = BranchManager.ReadBranch(workspace, conf.CurrentBranch);
                r.Remote = conf.Remote;
                SetCurrentWorkspaceDir(r.Workspace);
                WriteRepositoryConfig(workspace, r);
                return r;
            }
            return null;
        }

        public static Repository OpenLastRepository()
        {
            DirectoryInfo workspace = GetCurrentWorkspaceDir();
            return OpenRepository(workspace);
        }
    }
}