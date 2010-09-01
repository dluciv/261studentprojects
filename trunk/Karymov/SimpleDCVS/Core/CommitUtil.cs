using System.IO;

namespace Core
{
    public static class CommitUtil
    {
        //делает коммит используя инфу из снепшота
        public static CommitInfo Commit(SnapshotInfo snapshot, DirectoryInfo workspace, string message)
        {
            string snapshotsDir = Path.Combine(workspace.FullName, Global.SnapshotsDirPath);
            string snapshotDir = Path.Combine(snapshotsDir, snapshot.Id);
            Directory.CreateDirectory(snapshotDir);
            CopyAllExceptRepository(workspace.FullName, snapshotDir);
            var commit = new CommitInfo(snapshot, message);
            WriteCommit(workspace, commit);
            return commit;
        }
        //по ID получаем всю информацию о коммите
        public static CommitInfo GetCommitInfo(DirectoryInfo workspace, string commitId)
        {
            return ReadCommit(workspace, commitId);
        }
        //копирует все файлы из одной папки в другую когда делаем clone
        public static void CopyAll(string source, string destenation)
        {
            var dir = new DirectoryInfo(source);
            foreach (FileSystemInfo fsi in dir.GetFileSystemInfos())
            {
                string destName = Path.Combine(destenation, fsi.Name);
                if (fsi is FileInfo)
                    File.Copy(fsi.FullName, destName);
                else
                {
                    if (!Directory.Exists(destName))
                        Directory.CreateDirectory(destName);
                    CopyAll(fsi.FullName, destName);
                }
            }
        }
        //тоже что CopyAll только не копирует репозиторий
        public static void CopyAllExceptRepository(string source, string dest)
        {
            var dir = new DirectoryInfo(source);
            foreach (FileSystemInfo fsi in dir.GetFileSystemInfos())
            {
                string destName = Path.Combine(dest, fsi.Name);
                if (fsi is FileInfo)
                    File.Copy(fsi.FullName, destName);
                else if (!fsi.Name.Contains(Global.RepositoryDirName))
                {
                    if (!Directory.Exists(destName))
                        Directory.CreateDirectory(destName);
                    CopyAllExceptRepository(fsi.FullName, destName);
                }
            }
        }
        //востанавливает рабочую директорию по Id коммита,для переключения между ветками
        public static void RestoreWorkspaceFromCommit(DirectoryInfo workspace, CommitInfo commit)
        {
            string snapshotsDir = Path.Combine(workspace.FullName, Global.SnapshotsDirPath);
            string src = Path.Combine(snapshotsDir, commit.Id);
            string dest = workspace.FullName;
            CleanWorkspace(workspace);
            CopyAll(src, dest);
            File.Delete(Path.Combine(dest, Global.CommitMetaFileName));
        }

        public static void WriteCommit(DirectoryInfo workspace, CommitInfo commit)
        {
            string commitsDir = Path.Combine(workspace.FullName, Global.SnapshotsDirPath);
            string commitDir = Path.Combine(commitsDir, commit.Id);
            string commitMetaFilePath = Path.Combine(commitDir, Global.CommitMetaFileName);
            string xml = XmlUtility.Obj2XmlStr(commit);
            File.WriteAllText(commitMetaFilePath, xml);
        }

        public static CommitInfo ReadCommit(DirectoryInfo workspace, string commitId)
        {
            string commitsDir = Path.Combine(workspace.FullName, Global.SnapshotsDirPath);
            string commitDir = Path.Combine(commitsDir, commitId);
            string commitMetaFilePath = Path.Combine(commitDir, Global.CommitMetaFileName);
            string xml = File.ReadAllText(commitMetaFilePath);
            var commit = XmlUtility.XmlStr2Obj<CommitInfo>(xml);
            return commit;
        }

        private static void CleanWorkspace(DirectoryInfo workspace)
        {
            foreach (DirectoryInfo d in workspace.GetDirectories())
                if (d.Name != Global.RepositoryDirName)
                    RemoveDirectory(d);
            foreach (FileInfo f in workspace.GetFiles())
                File.Delete(f.FullName);
        }

        private static void RemoveDirectory(DirectoryInfo dir)
        {
            foreach (DirectoryInfo d in dir.GetDirectories())
                RemoveDirectory(d);
            foreach (FileInfo f in dir.GetFiles())
                File.Delete(f.FullName);
            Directory.Delete(dir.FullName);
        }
    }
}