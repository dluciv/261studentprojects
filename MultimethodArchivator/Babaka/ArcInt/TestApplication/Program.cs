/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.IO;
using Arithmetical;
using CommonUtils;
using CommonUtils.Coder;
using MainModule.TestUtil;
using Huffman;

namespace MainModule
{
    class Program
    {
        private const int ReadingBuffer = 10;
        private const string TestDirectory = "tests";
        private const string HuffmanEncoded = "HEncoded";
        private const string HuffmanEncodedDirectory = TestDirectory + "\\" + HuffmanEncoded;
        private const string HuffmanDecoded = "HDdecoded";
        private const string HuffmanDecodedDirectory = TestDirectory + "\\" + HuffmanDecoded;
        private const string ArithmEncoded = "AEncoded";
        private const string ArithmEncodedDirectory = TestDirectory + "\\" + ArithmEncoded;
        private const string ArithmDecoded = "ADdecoded";
        private const string ArithmDecodedDirectory = TestDirectory + "\\" + ArithmDecoded;

        private const string TestDescriptor = "readme.txt";

        static void Main(string[] args)
        {
            TestGenerator generator = new TestGenerator(TestDirectory, TestDescriptor);
            generator.Generate();

            DirectoryInfo directory = new DirectoryInfo(TestDirectory);
            FileInfo[] files = directory.GetFiles();

            Test(TestType.Arithm, files, ArithmEncodedDirectory, ArithmDecodedDirectory);
            Test(TestType.Huffman, files, HuffmanEncodedDirectory, HuffmanDecodedDirectory);

            Console.ReadLine();
        }
        /// <summary>
        /// Проведение серии тестов для алгоритма
        /// </summary>
        /// <param name="testType">Тип алгоритма</param>
        /// <param name="files"></param>
        /// <param name="encodedDirectory"></param>
        /// <param name="decodedDirectory"></param>
        private static void Test(TestType testType, FileInfo[] files, string encodedDirectory, string decodedDirectory)
        {
            Directory.CreateDirectory(encodedDirectory);
            Directory.CreateDirectory(decodedDirectory);
            foreach (FileInfo info in files)
            {
                if (info.Name.Equals(TestDescriptor)) continue;
                string encodedFile = encodedDirectory + "\\" + info.Name + ".enc";
                string decodedFile = decodedDirectory + "\\" + info.Name + ".dec";
                IEncoder encoder;
                IDecoder decoder;
                if (testType == TestType.Huffman)
                {
                    encoder = new HuffmanEncoder(info.FullName);
                    decoder = new HuffmanDecoder(encodedFile);
                }
                else
                {
                    encoder = new ArithmeticalEncoder(info.FullName);
                    decoder = new ArithmeticalDecoder(encodedFile);
                }
                encoder.Encode(encodedFile);
                decoder.Decode(decodedFile);
                if (FileComparsion(info.FullName, decodedFile))
                {
                    Console.WriteLine("[OK] Test complete: source file " + info.FullName +
                                      " and decoded file " + decodedFile + " equals");
                }
                else
                {
                    Console.WriteLine("[FAIL] Test complete: source file " + info.FullName +
                                      " and decoded file " + decodedFile + " not equals");
                }
            }
        }
        /// <summary>
        /// Побайтовое сравнение файлов
        /// </summary>
        /// <param name="file1"></param>
        /// <param name="file2"></param>
        /// <returns></returns>
        private static bool FileComparsion(string file1, string file2)
        {
            using (BinaryReader reader1 = new BinaryReader(File.Open(file1, FileMode.Open)))
            {
                using (BinaryReader reader2 = new BinaryReader(File.Open(file2, FileMode.Open)))
                {
                    byte[] readed1 = reader1.ReadBytes(ReadingBuffer);
                    byte[] readed2 = reader2.ReadBytes(ReadingBuffer);
                    if (readed1.Length == readed2.Length && readed1.Length > 0)
                    {
                        for (int i = 0; i < readed1.Length; i++)
                        {
                            if (readed1[i] != readed2[i])
                            {
                                return false;
                            }
                        }
                        reader1.ReadBytes(ReadingBuffer);
                        reader2.ReadBytes(ReadingBuffer);
                    }
                    else
                    {
                        return readed1.Length == readed2.Length && readed1.Length == 0;
                    }
                }
            }
            return true;
        }
    }
}
