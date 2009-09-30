/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.IO;

namespace MainModule.TestUtil
{
    /// <summary>
    /// Герератор тестов, для архиваторов
    /// </summary>
    public class TestGenerator
    {
        private string _testDirectory;
        private string _descriptor;
        private const string TestName = "test";
        private const char SingleSymbol = 'a';

        private const int LengthShort = 10;
        private const int LengthShort2 = 100;
        private const int LengthShort3 = 1024;
        private const int LengthMedium1 = 1024 * 10;
        private const int LengthMedium2 = 1024 * 100;
        private const int LengthMedium3 = 1024 * 1024;
        private const int LengthMedium4 = 1024 * 1024 * 2;
        private const int LengthMedium5 = 1024 * 1024 * 10;
        private const int LengthLarge1 = 1024 * 1024 * 40;
        private const int LengthLarge2 = 1024 * 1024 * 100;

        public TestGenerator(string testDirectory, string descriptor)
        {
            _testDirectory = testDirectory;
            _descriptor = descriptor;
        }

        public void Generate()
        {
            Generate(new int[] { LengthShort, LengthShort2, LengthShort3, LengthMedium1, LengthMedium2, LengthMedium3});
        }

        public void Generate(int[] lengths)
        {
            Directory.CreateDirectory(_testDirectory);
            using (BinaryWriter writer = new BinaryWriter(File.Open(_testDirectory + "\\" + _descriptor, FileMode.Create)))
            {
                // Перегоняем в массив, так как при записи строки он пишет ее длину перед ней
                writer.Write(("Test files description: \r\n\r\n").ToCharArray());
                int index = 0;
                GenerateEmptyFile(_testDirectory, TestName + index, writer);
                index++;
                GenerateOneSymbolFile(_testDirectory, TestName + index, writer);
                index++;
                foreach (int l in lengths)
                {
                    GenerateSingleSymbolFile(l, _testDirectory, TestName + index, writer);
                    index++;
                }
                foreach (int l in lengths)
                {
                    GenerateRandomSymbolsFile(l, _testDirectory, TestName + index, writer);
                    index++;
                }
            }
        }
        /// <summary>
        /// Генерация пустого файла
        /// </summary>
        /// <param name="directory"></param>
        /// <param name="name"></param>
        /// <param name="descriptorWriter"></param>
        private void GenerateEmptyFile(string directory, string name, BinaryWriter descriptorWriter)
        {
            using (BinaryWriter writer = new BinaryWriter(File.Open(directory + "\\" + name, FileMode.Create)))
            {
            }
            descriptorWriter.Write(("    [" + name + "] - " + "empty file\r\n").ToCharArray());
        }
        /// <summary>
        /// Генерация файла состоящего из одного символа
        /// </summary>
        /// <param name="directory"></param>
        /// <param name="name"></param>
        /// <param name="descriptorWriter"></param>
        private void GenerateOneSymbolFile(string directory, string name, BinaryWriter descriptorWriter)
        {
            using (BinaryWriter writer = new BinaryWriter(File.Open(directory + "\\" + name, FileMode.Create)))
            {
                writer.Write(SingleSymbol);
            }
            descriptorWriter.Write(("    [" + name + "] - " + "one-symbol file\r\n").ToCharArray());
        }
        /// <summary>
        /// Генерация файла состоящего из строки одинаковых символов
        /// </summary>
        /// <param name="length"></param>
        /// <param name="directory"></param>
        /// <param name="name"></param>
        /// <param name="descriptorWriter"></param>
        private void GenerateSingleSymbolFile(int length, string directory, string name, BinaryWriter descriptorWriter)
        {
            using (BinaryWriter writer = new BinaryWriter(File.Open(directory + "\\" + name, FileMode.Create)))
            {
                for (int i = 0; i < length; i++)
                {
                    writer.Write(SingleSymbol);
                }
            }
            descriptorWriter.Write(("    [" + name + "] - " + "single-symbol file with length " + length + " \r\n").ToCharArray());
        }
        /// <summary>
        /// Генерация файла состоящего и случайных символов
        /// </summary>
        /// <param name="length"></param>
        /// <param name="directory"></param>
        /// <param name="name"></param>
        /// <param name="descriptorWriter"></param>
        private void GenerateRandomSymbolsFile(int length, string directory, string name, BinaryWriter descriptorWriter)
        {
            using (BinaryWriter writer = new BinaryWriter(File.Open(directory + "\\" + name, FileMode.Create)))
            {
                for (int i = 0; i < length; i++)
                {
                    Random random = new Random();
                    writer.Write((byte)random.Next(byte.MinValue, byte.MaxValue));
                }
            }
            descriptorWriter.Write(("    [" + name + "] - " + "random-symbol file with length " + length + " \r\n").ToCharArray());
        }
    }
}
