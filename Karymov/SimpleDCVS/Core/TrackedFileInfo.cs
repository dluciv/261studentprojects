namespace Core
{
    public class TrackedFileInfo
    {
        public byte[] Hash;
        public string Name;
        public FileStatus Status;

        public TrackedFileInfo()
        {
        }

        public TrackedFileInfo(string file)
        {
            Name = file;
            Hash = Hasher.GetFileHash(file);
            Status = FileStatus.Normal;
        }
    }
}