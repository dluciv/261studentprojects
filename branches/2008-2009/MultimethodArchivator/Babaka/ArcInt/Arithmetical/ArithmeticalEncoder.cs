/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System.IO;
using CommonUtils.Util;
using CommonUtils.Coder;

namespace Arithmetical
{
    /// <summary>
    /// Класс реализующий алгоритм арифметического кодирования
    /// </summary>
    public class ArithmeticalEncoder : IEncoder
    {
        private string _input;

        private const int BitsCount = 32;
        private const uint TopValue = (uint)((1L << BitsCount) - 1L);
        private const uint FirstQtr = TopValue / 4u + 1u;
        private const uint Half = 2u * FirstQtr;
        private const uint ThirdQtr = 3u * FirstQtr;

        private const int ByteReadingCount = 4;
        private const int ByteWritingCount = 4;
        private const int ByteCount = byte.MaxValue + 1;

        private uint _low;
        private uint _top;
        private uint[] _dictionary;
        private uint _bitsToFollow;
        /// <summary>
        /// 
        /// </summary>
        /// <param name="input">Имя файла, который необходимо закодировать</param>
        public ArithmeticalEncoder(string input)
        {
            if (!File.Exists(input))
            {
                throw new FileNotFoundException();
            }
            _dictionary = new uint[ByteCount];
            ReadDictionary(input);
            _input = input;
        }
        /// <summary>
        /// Кодирует файл
        /// </summary>
        /// <param name="output">Имя закодированного файла</param>
        public void Encode(string output)
        {
            _low = 0u;
            _top = TopValue;
            _bitsToFollow = 0u;

            using(BinaryReader reader = new BinaryReader(File.Open(_input, FileMode.Open)))
            {    
                using (BinaryWriter writer = new BinaryWriter(File.Open(output, FileMode.Create)))
                {
                    WriteDictionary(writer);
                    using (BitListBuffer buffer = new BitListBuffer(writer, ByteWritingCount))
                    {
                        byte[] readed = reader.ReadBytes(ByteReadingCount);
                        while (readed.Length > 0)
                        {
                            foreach (byte b in readed)
                            {
                                EncodeSymbol(b, buffer);
                            }
                            readed = reader.ReadBytes(ByteReadingCount);
                        }
                        EndEncoding(buffer);    
                    }
                }
            }
        }

        private void EndEncoding(BitListBuffer buffer)
        {
            _bitsToFollow++;
            if (_low < FirstQtr)
            {
                InsertBit(0, buffer);
            }
            else
            {
                InsertBit(1, buffer);
            }
        }

        private void WriteDictionary(BinaryWriter writer)
        {
            foreach(uint nxt in _dictionary)
            {
                writer.Write(nxt);
            }
        }

        private void EncodeSymbol(byte symbol, BitListBuffer buffer)
        {
            long range = _top - _low + 1L;
            _top = _low + (uint)(range * _dictionary[symbol] / _dictionary[ByteCount - 1]) - 1u;
            _low = _low + (symbol == 0 ? 0u : (uint)(range * _dictionary[symbol - 1] / _dictionary[ByteCount - 1]));
            // Скидываем "совпадающие" у рамок интервала биты в буфер
            Dump(buffer);
        }

        /// <summary>
        ///  see http://algolist.manual.ru/compress/standard/arithm.php
        /// </summary>
        /// <param name="buffer"></param>
        private void Dump(BitListBuffer buffer)
        {
            
            while(true)
            {
                if (_top < Half)
                {
                    InsertBit(0, buffer);
                }
                else if (_low >= Half)
                {
                    InsertBit(1, buffer);
                    _low -= Half;
                    _top -= Half;
                }
                else if (_low >= FirstQtr && _top < ThirdQtr)
                {
                    _bitsToFollow++;
                    _low -= FirstQtr;
                    _top -= FirstQtr;
                }
                else
                {
                    break;
                }
                _low *= 2;
                _top = 2 * _top + 1;
            }
        }

        private void InsertBit(byte bit, BitListBuffer buffer)
        {
            buffer.Add(bit);
            byte followed = bit == (byte)0 ? (byte)1 : (byte)0;
            while (_bitsToFollow > 0)
            {
                buffer.Add(followed);
                _bitsToFollow--;
            }
        }

        private void ReadDictionary(string input)
        {
            using (BinaryReader reader = new BinaryReader(File.Open(input, FileMode.Open)))
            {
                byte[] readed = reader.ReadBytes(ByteReadingCount);
                while (readed.Length > 0)
                {
                    foreach (byte b in readed)
                    {
                        _dictionary[b] += 1u;
                    }
                    readed = reader.ReadBytes(ByteReadingCount);
                }
            }       
            Morth(_dictionary);
        }

        private void Morth(uint[] dic)
        {
            for (byte b = 0; b < ByteCount - 1; b++)
            {
                dic[b + 1] = dic[b + 1] + dic[b];
            }
        }
    }
}
