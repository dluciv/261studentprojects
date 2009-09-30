using System;
using System.Collections.Generic;
using System.IO;
using DB3.Entities;
using DB3.Util;

namespace DB3.Parser
{
    class DatabaseParser
    {
        private int _size;
        private const int IntLength = 4;
        private string _filename;

        public DatabaseParser(string filename)
        {
            _filename = filename;
            Init();
        }

        private void Init()
        {
            using (BinaryReader reader = new BinaryReader(File.Open(_filename, FileMode.Open)))
            {
                ParseServiceInformation(reader);
            }
        }

        private void ParseServiceInformation(BinaryReader reader)
        {
            _size = reader.ReadInt32();
        }

        public Card ParseSingle(int from)
        {
            List<Card> result = Parse(from, 1);
            return (result == null || result.Count == 0) ? null : result[0];
        }

        public List<Card> Parse()
        {
            return Parse(IntLength, _size);
        }

        public List<Card> Parse(int from, int cardCount)
        {
            int readedCount = 0;
            List<Card> data = new List<Card>();
            using (BinaryReader reader = new BinaryReader(File.Open(_filename, FileMode.Open)))
            {
                int filePosition = from;
                reader.ReadBytes(filePosition);
                try
                {
                    while (cardCount == -1 || readedCount < cardCount)
                    {
                        Card card = ReadNextCard(reader);
                        card.FilePosition = filePosition;
                        data.Add(card);
                        readedCount++;
                        filePosition += card.ByteLenght;
                    }
                }
                catch (Exception)
                {
                    
                    throw new Exception();
                }

            }
            return data;
        }

        private Card ReadNextCard(BinaryReader reader)
        {
            long tmp = reader.BaseStream.Position;
            string name = reader.ReadString();
            string middleName = reader.ReadString();
            string lastName = reader.ReadString();
            string sexID = reader.ReadString();
            string phone = reader.ReadString();
            string address = reader.ReadString();
            Sex sex = DatabaseConstants.GetSexById(sexID.ToString());
            Card card = new Card(name, lastName, middleName, sex, phone, address);
            card.ByteLenght = (int)(reader.BaseStream.Position - tmp);
            return card;
        }



    }
}