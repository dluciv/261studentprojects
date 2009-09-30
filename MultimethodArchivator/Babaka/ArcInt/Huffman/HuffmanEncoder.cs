/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.Collections.Generic;
using System.IO;
using CommonUtils.Coder;
using CommonUtils.Util;
using Huffman.Tree;

namespace Huffman
{
    /// <summary>
    /// Класс реализующий кодирование алгоритмом Хаффмана
    /// </summary>
    public class HuffmanEncoder: IEncoder
    {
        public static int ByteReadingCount = 4;
        public static int ByteWritingCount = 4;
        private const int ByteCount = 256;
        private string _input;
        private uint[] _dictionary;
        private byte[] _storageDictionary;
        private Dictionary<byte, List<byte>> _encoderDictionary;
        /// <summary>
        /// 
        /// </summary>
        /// <param name="input"></param>
        public HuffmanEncoder(string input)
        {
            _input = input;
            _dictionary = new uint[ByteCount];
            _storageDictionary = new byte[ByteCount];
            _encoderDictionary = new Dictionary<byte, List<byte>>();
        }
        /// <summary>
        /// Кодирует файл
        /// </summary>
        /// <param name="output">Имя закодированного файла</param>
        public void Encode(string output)
        {
            CreateDictionary(_input);
            using (BinaryReader reader = new BinaryReader(File.Open(_input, FileMode.Open)))
            {
                using (BinaryWriter writer = new BinaryWriter(File.Open(output, FileMode.Create)))
                {
                    if (DictionaryIsEmpty()) return;
                    BuildTree();
                    WriteDictionary(writer, _storageDictionary);
                    WriteLastByteSize(writer);
                    EncodeFile(reader, writer);
                }
            }
        }

        private bool DictionaryIsEmpty()
        {
            for (int i = 0; i < ByteCount; i++)
            {
                if (_dictionary[i] != 0)
                {
                    return false;
                }
            }
            return true;
        }

        private void EncodeFile(BinaryReader reader, BinaryWriter writer)
        {
            Byte[] readed = reader.ReadBytes(ByteReadingCount);
            using (BitListBuffer buffer = new BitListBuffer(writer, ByteWritingCount))
            {
                while (readed.Length > 0)
                {
                    foreach (byte b in readed)
                    {
                        buffer.Add(_encoderDictionary[b]);
                    }
                readed = reader.ReadBytes(ByteReadingCount);
                }
                buffer.Write();
            }
        }

        private void WriteLastByteSize(BinaryWriter writer)
        {
            uint length = 0u;
            for (int i = 0; i < ByteCount; i++)
            {
                length += _storageDictionary[i] * _dictionary[i];
            }
            writer.Write((byte)(length % 8 == 0 ? 8 : length % 8));
        }

        private static void WriteDictionary(BinaryWriter writer, byte[] dictionary)
        {
            List<byte> compressed = RLE.Compress(dictionary);
            WritingUtilities.WriteList(writer, compressed);
        }

        private void CreateDictionary(string input)
        {
            using (BinaryReader reader = new BinaryReader(File.Open(input, FileMode.Open)))
            {
                Byte[] readed = reader.ReadBytes(ByteReadingCount);
                while (readed.Length > 0)
                {
                    foreach (byte b in readed)
                    {
                        _dictionary[b] += 1u;
                    }
                    readed = reader.ReadBytes(ByteReadingCount);
                }
            }
            if (DictionaryIsEmpty()) return;
        }

        private void BuildTree()
        {
            SortedList<Pair<uint, TreeElement>> treeList = new SortedList<Pair<uint, TreeElement>>();
            for (int i = 0; i < ByteCount; i++)
            {
                if (_dictionary[i] != 0)
                {
                    treeList.Add(new Pair<uint, TreeElement>(_dictionary[i], new TreeLeaf((byte)i)));
                }
            }
            BuildTree(treeList);
            TreeElement tree = treeList[0].Second;
            TreeUnfolder unfolder = new TreeUnfolder(tree);
            unfolder.Traverse();
            Dictionary<byte, LevelInfo> levels = unfolder.GetLevels();
            BuildStorageDictionary(levels);
            BuildEncoderDictionary(levels);
        }

        private void BuildEncoderDictionary(Dictionary<byte, LevelInfo> levels)
        {
            foreach (KeyValuePair<byte, LevelInfo> pair in levels)
            {
                SortedList<byte> bytes = pair.Value.Bytes();
                foreach (byte b in bytes)
                {
                    _encoderDictionary.Add(b, BinaryUtils.GenerateCode(pair.Key, pair.Value.Index(b)));
                }
            }
        }

        private void BuildStorageDictionary(Dictionary<byte, LevelInfo> levels)
        {
            foreach (KeyValuePair<byte, LevelInfo> pair in levels)
            {
                SortedList<byte> bytes = pair.Value.Bytes();
                foreach (byte b in bytes)
                {
                    _storageDictionary[b] = pair.Key;
                }
            }
        }

        private static void BuildTree(SortedList<Pair<uint,TreeElement>> treeList)
        {
            if (treeList.Count() == 1)
            {
                treeList[0].Second.IncrementLevel();
            }
            else
            {
                while (treeList.Count() > 1)
                {
                    Pair<uint, TreeElement> fst = treeList[0];
                    treeList.RemoveAt(0);
                    Pair<uint, TreeElement> snd = treeList[0];
                    treeList.RemoveAt(0);
                    treeList.Add(new Pair<uint, TreeElement>(fst.First+snd.First, 
                        new TreeNode(fst.Second, snd.Second)));
                }
            }
        }
    }
}
