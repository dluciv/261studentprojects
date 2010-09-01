using System;
using System.Collections.Generic;
using System.IO;

namespace Core
{
    //обходит директорию возвращает список имен ее файлов
    public static class FileFinder
    {
        public static List<string> GetAllFiles(DirectoryInfo dir)
        {
            var files = new List<string>();
            FindAllFiles(dir, files, "");
            return files;
        }

        private static void FindAllFiles(DirectoryInfo dir, List<string> files, String path)
        {
            foreach (DirectoryInfo directoryInfo in dir.GetDirectories())
                if (!directoryInfo.Name.Contains(Global.RepositoryDirName))
                    FindAllFiles(directoryInfo, files, Path.Combine(path, directoryInfo.Name));
            foreach (FileInfo f in dir.GetFiles())
                files.Add(Path.Combine(path, f.Name));
        }
    }
}