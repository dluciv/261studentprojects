/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.Collections.Generic;
using System.IO;
using CommonUtils.Coder;
using CommonUtils.Exceptions;
using CommonUtils.Util;

namespace Huffman
{
    public class HuffmanDecoder : IDecoder
    {
        public static int ByteReadingCount = 4;
        public static int ByteWritingCount = 4;
        public static int DictionaryReadingCount = 2;
        public static int LastBitIdentificatorLength = 1;
        private const int ByteCount = 256;
        private const int ByteSize = 8;
        private string _input;
        private byte[] _lengthDictionary;
        private Dictionary<List<byte>, byte> _codeDictionary;
        private bool _empty;
        private int _lastBitsLength;
        private int _startFromByte;
        private int _maxLength;
        private byte _lastByte;

        public HuffmanDecoder(string input)
        {
            _input = input;
            _lengthDictionary = new byte[ByteCount];
        }

        private void MakeCodeDictionary()
        {
            // Подсчитаем максимальную длину кода
            for (int i = 0; i < ByteCount; i++)
            {
                if (_lengthDictionary[i] > _maxLength) _maxLength = _lengthDictionary[i];
            }
            // Теперь найдем распределение по длинам кодов (сколько каких длин у нас есть)
            byte[] lengths = new byte[_maxLength];

            for (int i = 0; i < ByteCount; i++)
            {
                if (_lengthDictionary[i] != 0)
                {
                    lengths[_lengthDictionary[i] - 1] += 1;
                }
            }

            // Теперь применим алгоритм восстановления численных значений кодов для
            // каждого первого символа на уровне.
            byte[] startValues = new byte[_maxLength];
            startValues[_maxLength - 1] = 0;

            for (int i = _maxLength - 2; i >= 0; i--)
            {
                startValues[i] = (byte)((lengths[i + 1] + startValues[i + 1]) >> 1);
            }

            // Теперь восстановим все численные значения для каждого символа
            byte[] values = new byte[ByteCount];
            for (int i = 0; i < ByteCount; i++)
            {
                if (_lengthDictionary[i] == 0)
                {
                    values[i] = 0;
                }
                else
                {
                    values[i] = startValues[_lengthDictionary[i] - 1];
                    startValues[_lengthDictionary[i] - 1] += 1;
                }
            }

            // А теперь пересчитаем пары "длина-значение" в нормальный код символа.
            Dictionary<byte, List<byte>> byteCodeDictionary = new Dictionary<byte, List<byte>>();
            for (int i = 0; i < ByteCount; i++)
            {
                if (_lengthDictionary[i] != 0)
                {
                    byteCodeDictionary.Add((byte)i, BinaryUtils.GenerateCode(_lengthDictionary[i], values[i]));
                }
            }

            // "Переворачиваем" словарь, что бы было можно по коду получить байт.

            foreach (KeyValuePair<byte, List<byte>> pair in byteCodeDictionary)
            {
                _codeDictionary.Add(pair.Value, pair.Key);
            }
        }

        private void ReadDictionary(BinaryReader reader)
        {
            if (new FileInfo(_input).Length == 0)
            {
                _empty = true;
                return;
            }
            int dicCount = 0;
            int index = 0;

            Byte[] readed = reader.ReadBytes(DictionaryReadingCount);
            while (readed.Length == 2 && dicCount < ByteCount)
            {
                _startFromByte += readed.Length;
                dicCount += readed[1];
                for (int i = 0; i < readed[1]; i++)
                {
                    _lengthDictionary[index] = readed[0];
                    index++;
                }
                if (dicCount > ByteCount) throw new WrongArchiveTypeException();
                readed = reader.ReadBytes(DictionaryReadingCount);
            }
            // Так как прочитали лишнего
            if (dicCount < ByteCount) throw new WrongArchiveTypeException();
            if (readed.Length > 0)
            {
                // Но среди лишнего была длина последнего байта
                _lastBitsLength = readed[0];
                _lastByte = readed[1];
            }
        }

        public void Decode(string output)
        {
            using (BinaryReader reader = new BinaryReader(File.Open(_input, FileMode.Open)))
            {
                using (BinaryWriter writer = new BinaryWriter(File.Open(output, FileMode.Create)))
                {
                    ReadDictionary(reader);
                    if (_empty) return;
                    _codeDictionary = new Dictionary<List<byte>, byte>();
                    MakeCodeDictionary();
                    DecodeFile(reader, writer);
                }
            }
        }
        private void DecodeFile(BinaryReader reader, BinaryWriter writer)
        {
            Byte[] readed = reader.ReadBytes(ByteReadingCount);
            BytesBuffer buffer = new BytesBuffer(writer, ByteWritingCount);
            List<byte> readedSequence = new List<byte>();
            while (readed.Length > 0)
            {
                List<byte> lastByteBits = BinaryUtils.GenerateCode(ByteSize, _lastByte);
                TryGetByte(buffer, readedSequence, lastByteBits);

                for (int i = 0; i < readed.Length - 1; i++)
                {
                    List<byte> bits = BinaryUtils.GenerateCode(ByteSize, readed[i]);
                    TryGetByte(buffer, readedSequence, bits);
                }
                _lastByte = readed[readed.Length - 1];
                readed = reader.ReadBytes(ByteReadingCount);
            }

            List<byte> lastBits = BinaryUtils.GenerateCode(ByteSize, _lastByte);
            lastBits.RemoveRange(_lastBitsLength, ByteSize - _lastBitsLength);
            TryGetByte(buffer, readedSequence, lastBits);
            if (readedSequence.Count != 0) throw new BadArchiveException();

            buffer.Close();
        }

        private void TryGetByte(BytesBuffer buffer, List<byte> readedSequence, List<byte> bits)
        {
            foreach (byte nextBit in bits)
            {
                readedSequence.Add(nextBit);
                bool finded = false;
                foreach (KeyValuePair<List<byte>, byte> pair in _codeDictionary)
                {
                    if (BinaryUtils.CodesEquals(readedSequence, pair.Key))
                    {
                        buffer.Add(pair.Value);
                        readedSequence.Clear();
                        finded = true;
                        break;
                    }
                }

                if (!finded && readedSequence.Count >= _maxLength)
                {
                    throw new BadArchiveException();
                }
            }
        }

    }
}
