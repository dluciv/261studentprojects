using System;
using System.Collections.Generic;
using System.IO;

namespace Core
{
    public class Cvs : ICvs
    {
        private Repository _r;

        #region ICvs Members
        //возвращает список всех коммитов для ветки
        public List<CommitInfo> GetCommits()
        {
            Branch b = _r.CurrentBranch;
            CommitInfo c = CommitUtil.GetCommitInfo(_r.Workspace, b.Head);
            var cs = new List<CommitInfo> {c};
            LoadCommitList(cs, c);
            return cs;
        }

        public SnapshotInfo GetLastSnapshot()
        {
            Branch b = _r.CurrentBranch;
            CommitInfo c = CommitUtil.GetCommitInfo(_r.Workspace, b.Head);
            return new SnapshotInfo(c);
        }

        public SnapshotInfo GetCurrentSnaphot()
        {
            SnapshotInfo s = GetLastSnapshot();
            return SnapshotManager.CreateSnapshot(_r.Workspace, s);
        }

        public List<Branch> GetBranches()
        {
            return BranchManager.GetAllBranches(_r.Workspace);
        }

        public Branch GetCurrentBranch()
        {
            return _r.CurrentBranch;
        }

        public CommitInfo GetCommitInfo(string id)
        {
            return CommitUtil.GetCommitInfo(_r.Workspace, id);
        }

        public bool Checkout(string branchname)
        {
            Branch b = BranchManager.ReadBranch(_r.Workspace, branchname);
            if (b != null)
            {
                _r.CurrentBranch = b;
                RepositoryManager.WriteRepositoryConfig(_r.Workspace, _r);

                CommitInfo commit = CommitUtil.ReadCommit(_r.Workspace, _r.CurrentBranch.Head);

                CommitUtil.RestoreWorkspaceFromCommit(_r.Workspace, commit);

                return true;
            }
            return false;
        }

        public void Commit(string message)
        {
            SnapshotInfo s = GetCurrentSnaphot();
            CommitInfo c = CommitUtil.Commit(s, _r.Workspace, message);
            BranchManager.UpdateBranchHead(_r.Workspace, _r.CurrentBranch, c);
        }

        public void Pull(string origin)
        {
            throw new NotImplementedException();
        }

        public void Push(string origin)
        {
            throw new NotImplementedException();
        }

        public bool Branch(string name)
        {
            return BranchManager.CreateBranch(_r.CurrentBranch, _r.Workspace, name) != null;
        }

        public List<Branch> GetRemoteBranches()
        {
            if (_r.Remote != null)
            {
                var cvs = new Cvs();
                cvs.Open(_r.Remote);
                return cvs.GetBranches();
            }
            return new List<Branch>();
        }

        public void Clone(string src, string dest)
        {
            CommitUtil.CopyAll(src, dest);
            Repository r = RepositoryManager.OpenRepository(new DirectoryInfo(dest));
            r.Remote = src;
            RepositoryManager.WriteRepositoryConfig(r.Workspace, r);
        }

        public void SetRemote()
        {
            throw new NotImplementedException();
        }

        public void OpenLast()
        {
            if (File.Exists(Global.RepositoryManagerConfigFilePath))
                _r = RepositoryManager.OpenLastRepository();
        }

        public void Create(string dir)
        {
            _r = RepositoryManager.CreateNewRepository(new DirectoryInfo(dir));
        }

        public bool Opened()
        {
            return _r != null;
        }

        public void Open(string path)
        {
            _r = RepositoryManager.OpenRepository(new DirectoryInfo(path));
        }

        #endregion
        //начиная с коммита с загружает его предыдущий коммит,делает прдыдущий текущим(обходим ветку загружем все ее коммиты) 
        private void LoadCommitList(List<CommitInfo> cs, CommitInfo c)
        {
            if (c.Previos != null)
            {
                CommitInfo p = CommitUtil.GetCommitInfo(_r.Workspace, c.Previos);
                cs.Add(p);
                LoadCommitList(cs, p);
            }
        }
    }
}