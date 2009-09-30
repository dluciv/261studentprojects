using System;
using System.IO;
using System.Threading;
using DB3.Entities;
using DB3.Util;

namespace DB3.DBGenerator
{
    class DatabaseGenerator
    {
        private const int DefaultCardsCount = 1000000;
        private readonly int _phoneLength = DatabaseConstants.DefaultPhoneLength;

        public DatabaseGenerator()
        {
        }

        public void Generate(string fileName, int cardsCount )
        {
            if (cardsCount <= 0) cardsCount = DefaultCardsCount;
            using (BinaryWriter writer = new BinaryWriter(File.Open(fileName, FileMode.Create)))
            {
                WriteServiceInformation(writer, cardsCount);
                for (int i = 0; i < cardsCount; i++) {
                    Random r = new Random();
                    Sex sex = GenerateSex(r);
                    WriteName(writer, sex);
                    WriteMiddleName(writer, sex);
                    WriteLastName(writer, sex);
                    WriteSex(writer, sex);
                    WritePhone(writer);
                    WriteAddress(writer);
                    Thread.Sleep(5);
                }
            }
        }

        private void WriteServiceInformation(BinaryWriter writer, int count)
        {
            writer.Write(count);
        }
        private void WriteAddress(BinaryWriter writer)
        {
            string address = RandomAddressGenerator.Generate();
            writer.Write(address);
        }
        private void WritePhone(BinaryWriter writer)
        {
            Random r = new Random();
            string phone = "";
            for (int i = 0; i < _phoneLength; i++) {
                phone += r.Next(10);
            }
            writer.Write(phone);
        }
        private void WriteSex(BinaryWriter writer, Sex sex)
        {
            writer.Write(DatabaseConstants.GetIdBySex(sex));
        }
        private void WriteLastName(BinaryWriter writer, Sex sex)
        {
            string name = RandomLastNameGenerator.Generate(sex);
            writer.Write(name);
        }
        private void WriteMiddleName(BinaryWriter writer, Sex sex)
        {
            string name = RandomMiddleNameGenerator.Generate(sex);
            writer.Write(name);
        }
        private void WriteName(BinaryWriter writer, Sex sex)
        {
            string name = RandomNameGenerator.Generate(sex);
            writer.Write(name);
        }

        private Sex GenerateSex(Random r)
        {
            int result = r.Next(1);
            switch (result)
            {
                case 0:
                    return Sex.MALE;
                case 1:
                    return Sex.FEMALE;
                default:
                    return Sex.UNKNOWN;
            }
        }
    }
}
