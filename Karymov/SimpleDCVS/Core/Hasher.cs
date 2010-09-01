using System;
using System.Collections.Generic;
using System.IO;
using System.Security.Cryptography;

namespace Core
{
    public static class Hasher
    {
        public static bool CompareByteArrays(byte[] a1, byte[] a2)
        {
            for (int i = 0; i < a1.Length; ++i)
                if (a1[i] != a2[i])
                    return false;
            return true;
        }
        //вычисляем хеш файла
        public static byte[] GetFileHash(string fileName)
        {
            SHA1 hasher = SHA1.Create();
            var fileStream = new FileStream(fileName, FileMode.Open, FileAccess.Read, FileShare.ReadWrite);
            byte[] hash = hasher.ComputeHash(fileStream);
            fileStream.Close();
            return hash;
        }
        //накладываем хеши друг на друга,добавляет 3 байта генерирующие из текущей даты(нужно для формирования ID)
        public static byte[] MergeHashes(List<byte[]> hashes)
        {
            const int redurantBytesCount = 3;
            if (hashes.Count == 0)
            {
                const int magicNumber = 20;//длина SHA1 хеша в байтах
                var hash = new byte[magicNumber + redurantBytesCount];

                hash[magicNumber] = (byte) DateTime.Now.GetHashCode();
                hash[magicNumber + 1] = (byte) DateTime.Now.Millisecond.GetHashCode();
                hash[magicNumber + 2] = (byte) DateTime.Now.ToString().GetHashCode();

                return hash;
            }
            else
            {
                int l = hashes[0].Length;
                var hash = new byte[l + redurantBytesCount];
                for (int i = 0; i < l; ++i)
                {
                    hash[i] = 0;
                    foreach (var t in hashes)
                        hash[i] += t[i];
                }

                hash[l] = (byte) DateTime.Now.GetHashCode();
                hash[l + 1] = (byte) DateTime.Now.ToShortTimeString().GetHashCode();
                hash[l + 2] = (byte) DateTime.Now.ToString().GetHashCode();

                return hash;
            }
        }
    }
}