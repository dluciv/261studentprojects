using System.Collections.Generic;

namespace Core
{
    public interface ICvs
    {
        List<CommitInfo> GetCommits();
        SnapshotInfo GetLastSnapshot();
        SnapshotInfo GetCurrentSnaphot();
        List<Branch> GetBranches();
        Branch GetCurrentBranch();
        CommitInfo GetCommitInfo(string id);
        bool Checkout(string branchname);
        void Commit(string message);
        void Pull(string remote);
        void Push(string remote);
        bool Branch(string name);
        List<Branch> GetRemoteBranches();
        void Clone(string src, string dest);
        void SetRemote();
        void Create(string dir);
        void OpenLast();
        bool Opened();
        void Open(string path);
    }
}