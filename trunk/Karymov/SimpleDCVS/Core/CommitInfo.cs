using System;
using System.Collections.Generic;

namespace Core
{
    public class CommitInfo
    {
        public CommitInfo()
        {
        }

        public CommitInfo(SnapshotInfo info, string message)
        {
            Date = DateTime.Now;
            Previos = info.Previos;
            Message = message;
            Files = info.Files;
            Id = info.Id;
        }

        public string Id { get; set; }
        public string Previos { get; set; }
        public DateTime Date { get; set; }
        public string Message { get; set; }
        public List<TrackedFileInfo> Files { get; set; }
    }
}