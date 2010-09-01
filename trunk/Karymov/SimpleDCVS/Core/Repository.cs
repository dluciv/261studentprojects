using System.IO;

namespace Core
{
    public class Repository
    {
        public DirectoryInfo Workspace;

        public Repository(DirectoryInfo workspace)
        {
            Workspace = workspace;
            SnapshotDir = new DirectoryInfo(Path.Combine(workspace.FullName, Global.SnapshotsDirPath));
            BranchesDir = new DirectoryInfo(Path.Combine(workspace.FullName, Global.BranchesDirPath));
        }

        public DirectoryInfo SnapshotDir { get; set; }
        public DirectoryInfo BranchesDir { get; set; }
        public Branch CurrentBranch { get; set; }
        public string Remote { get; set; }

        public RepositoryConfig GetConfiguration()
        {
            return new RepositoryConfig {CurrentBranch = CurrentBranch.Name, Remote = Remote};
        }
    }
}