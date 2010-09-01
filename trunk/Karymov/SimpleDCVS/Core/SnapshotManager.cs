using System;
using System.Collections.Generic;
using System.IO;

namespace Core
{
    public static class SnapshotManager
    {
        //метод который знает рабочую папку,предыдущий снепшот и вычисляет изменение
        public static SnapshotInfo CreateSnapshot(DirectoryInfo workspace, SnapshotInfo previos)
        {
            Environment.CurrentDirectory = workspace.FullName;

            var snapshot = new SnapshotInfo();

            if (previos == null)
            {
                snapshot.Files = new List<TrackedFileInfo>();

                foreach (string file in FileFinder.GetAllFiles(workspace))
                    snapshot.Files.Add(new TrackedFileInfo(file) {Status = FileStatus.New});

                snapshot.Id = Global.MasterCommitName;
            }
            else
            {
                var previosDictionary = new Dictionary<string, KeyValuePair<byte[], FileStatus>>(); // previos
                var currentDictionary = new Dictionary<string, KeyValuePair<byte[], FileStatus>>(); // current
                var hashes = new List<byte[]>();

                snapshot.Files = new List<TrackedFileInfo>();

                foreach (string file in FileFinder.GetAllFiles(workspace))
                    snapshot.Files.Add(new TrackedFileInfo(file));


                foreach (TrackedFileInfo f in previos.Files)
                    previosDictionary.Add(f.Name, new KeyValuePair<byte[], FileStatus>(f.Hash, f.Status));
                foreach (TrackedFileInfo f in snapshot.Files)
                    currentDictionary.Add(f.Name, new KeyValuePair<byte[], FileStatus>(f.Hash, f.Status));

                snapshot.Files = WorkspaceDiff(previosDictionary, currentDictionary);//сравнивает 2 словаря возвращает разницу

                foreach (TrackedFileInfo f in snapshot.Files)
                    hashes.Add(f.Hash);//формируем список хешей всех файлов

                snapshot.Previos = previos.Id;
                snapshot.Id = BitConverter.ToString(Hasher.MergeHashes(hashes)).Replace("-", "");
            }
            return snapshot;
        }
       //вычисляем разницу двух словарей
       //словарь это табличка первый столбец(key) имя файла,второй - пара хеш-статус(value)
       //разница это список всех файлов со статусом 
        public static List<TrackedFileInfo> WorkspaceDiff(Dictionary<string, KeyValuePair<byte[], FileStatus>> old,
                                                          Dictionary<string, KeyValuePair<byte[], FileStatus>> current)
        {
            var diff = new List<TrackedFileInfo>();

            foreach (string fileName in old.Keys)
                //если файл отсутствует во втором словаре и у него статус не "удален",то мы ему присваиваем статус удален
                if (!current.ContainsKey(fileName) && old[fileName].Value != FileStatus.Deleted)
                    diff.Add(new TrackedFileInfo { Name = fileName, Hash = old[fileName].Key, Status = FileStatus.Deleted });
                //если он есть в обоих словарях и у него одинаковые хеши тогда он не изменился
                else if (current.ContainsKey(fileName) && Hasher.CompareByteArrays(old[fileName].Key, current[fileName].Key))
                    diff.Add(new TrackedFileInfo { Name = fileName, Hash = old[fileName].Key, Status = FileStatus.Normal });
                //если он есть в обоих словарях и хеши не совпадают значит он изменился
                else if (current.ContainsKey(fileName) && !Hasher.CompareByteArrays(old[fileName].Key, current[fileName].Key))
                    diff.Add(new TrackedFileInfo { Name = fileName, Hash = current[fileName].Key, Status = FileStatus.Modified });
            
            foreach (string fileName in current.Keys)
              //если файл есть во втором,но его нет в первом,то он новый 
                if (!old.ContainsKey(fileName))
                    diff.Add(new TrackedFileInfo { Name = fileName, Hash = current[fileName].Key, Status = FileStatus.New });

            return diff;
        }
    }
}