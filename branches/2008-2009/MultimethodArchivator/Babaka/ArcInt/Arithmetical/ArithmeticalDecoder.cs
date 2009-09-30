/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.IO;
using CommonUtils.Coder;
using CommonUtils.Util;

namespace Arithmetical
{
    /// <summary>
    /// Класс реализующий алгоритм арифметического декодирования
    /// </summary>
    public class ArithmeticalDecoder: IDecoder
    {
        public static int BitsCount = 32;
        public static int ByteCount = byte.MaxValue + 1;
        public static uint TopValue = (uint)((1L << BitsCount) - 1L);
        public static uint FirstQtr = TopValue / 4u + 1u;
        public static uint Half = 2u * FirstQtr;
        public static uint ThirdQtr = 3u * FirstQtr;
        public static int ByteReadingCount = 4;
        public static int ByteWritingCount = 4;

        private string _input;
        private uint[] _dictionary;
        private uint _low;
        private uint _top;
        private uint _currentValue;

        public ArithmeticalDecoder(string input)
        {
            _input = input;
            _dictionary = new uint[ByteCount];
        }
        /// <summary>
        /// Декодируем файл
        /// </summary>
        /// <param name="output">Имя декодированного файла</param>
        public void Decode(string output)
        {
            _low = 0;
            _top = TopValue;
            _currentValue = 0;
            using (BinaryReader reader = new BinaryReader(File.Open(_input, FileMode.Open)))
            {
                ReadDictionary(reader);
                using (BinaryWriter writer = new BinaryWriter(File.Open(output, FileMode.Create)))
                {
                    BytesBuffer writingBuffer = new BytesBuffer(writer, ByteWritingCount);
                    BitReader bitReader = new BitReader(reader, ByteReadingCount);
                    ReadInitialValue(bitReader);

                    int decodedCount = 0;

                    while (decodedCount < _dictionary[ByteCount - 1])
                    {
                        DecodeSymbol(bitReader, writingBuffer);
                        decodedCount++;
                    }
                    writingBuffer.Close();
                }
            }
        }

        private void DecodeSymbol(BitReader reader, BytesBuffer buffer)
        {
            long range = _top - _low + 1L;
            // Модифицируем интервал
            uint cum = (uint)(((_currentValue - _low + 1L) * _dictionary[ByteCount - 1] - 1L) / range);
            byte symbol = FindSymbol(cum);
            buffer.Add(symbol);
            _top = _low + (uint)((range * _dictionary[symbol]) / _dictionary[ByteCount - 1]) - 1;
            _low = _low + (symbol == 0 ? 0u : (uint)((range * _dictionary[symbol - 1]) / _dictionary[ByteCount - 1]));
            // Скидываем "совпадающие" у рамок интервала биты в буфер
            Dump(reader);
        }
        /// <summary>
        ///  see http://algolist.manual.ru/compress/standard/arithm.php
        /// </summary>
        /// <param name="reader"></param>
        private void Dump(BitReader reader)
        {
            while (true)
            {
                if (_top < Half)
                {

                }
                else if (_low >= Half)
                {
                    _currentValue -= Half;
                    _low -= Half;
                    _top -= Half;
                }
                else if (_low >= FirstQtr && _top < ThirdQtr)
                {
                    _currentValue -= FirstQtr;
                    _low -= FirstQtr;
                    _top -= FirstQtr;
                }
                else
                {
                    break;
                }
                _low *= 2;
                _top = 2 * _top + 1;
                _currentValue = 2 * _currentValue + reader.Next();
            }
        }

        private byte FindSymbol(uint cum)
        {
            byte index = 0;
            while (_dictionary[index] <= cum)
            {
                index++;
            }
            return index;
        }

        private void ReadInitialValue(BitReader reader)
        {
            int index = 0;
            while (index < BitsCount)
            {
                _currentValue = (_currentValue << 1) + reader.Next();
                index++;
            }
        }

        private void ReadDictionary(BinaryReader reader)
        {
            try
            {
                int count = 0;
                while (count < ByteCount)
                {
                    _dictionary[count] = reader.ReadUInt32();
                    count++;
                }
            }
            catch (EndOfStreamException e)
            {
                throw new Exception("Unknown file format", e);
            }
        }
    }
}
