using System.IO;
using System.Windows.Forms;

namespace Core
{
    public static class Global
    {
        public static string RepositoryDirName = ".sdcvs";
        public static string SnapshotsDirPath = Path.Combine(RepositoryDirName, "snapshots");
        public static string BranchesDirPath = Path.Combine(RepositoryDirName, "branches");
        public static string RepositoryConfigFilePath = Path.Combine(RepositoryDirName, "sdcvs.xml");
        public static string MasterBranchName = "master";
        public static string MasterCommitName = "0";
        public static string RepositoryManagerConfigFilePath = Path.Combine(Application.StartupPath, "workspace.xml");
        public static string BranchMetaFilename = "meta.xml";
        public static string CommitMetaFileName = "meta.xml";
        public static string InitialCommitMessage = "initial commit";
    }
}