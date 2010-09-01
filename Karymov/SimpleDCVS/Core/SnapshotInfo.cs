using System.Collections.Generic;
using System.Text;

namespace Core
{
    public class SnapshotInfo
    {
        public SnapshotInfo()
        {
        }

        public SnapshotInfo(CommitInfo commit)
        {
            Id = commit.Id;
            Previos = commit.Previos;
            Files = commit.Files;
        }

        public string Id { get; set; }
        public string Previos { get; set; }
        public List<TrackedFileInfo> Files { get; set; }
        //говорит сколько файлов изменилось
        public override string ToString()
        {
            int n = 0;
            if (Files != null)
                foreach (TrackedFileInfo f in Files)
                    if (f.Status != FileStatus.Normal)
                        ++n;
            return n > 0 ? "untracked: " + n + " files" : "workspace clean";
        }
        //дает строчку с более детальным описанием снепшота
        public string Details()
        {
            var s = new StringBuilder();
            int n = 0;
            foreach (TrackedFileInfo f in Files)
            {
                switch (f.Status)
                {
                    case FileStatus.Deleted:
                        s.Append("deleted : " + f.Name + "\r\n");
                        ++n;
                        break;
                    case FileStatus.Modified:
                        s.Append("modified : " + f.Name + "\r\n");
                        ++n;
                        break;
                    case FileStatus.New:
                        s.Append("new : " + f.Name + "\r\n");
                        ++n;
                        break;
                }
            }
            return n > 0 ? s.ToString() : "workspace clean";
        }
    }
}