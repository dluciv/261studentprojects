/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System.Collections.Generic;
using System.Linq;
using System.IO;

namespace CommonUtils.Util
{
    /// <summary>
    /// Класс, позволяющий читать из файла побитно
    /// </summary>
    public class BitReader
    {
        private BinaryReader _reader;
        private int _lengthReading;
        private List<byte> _readed;
        private bool _ended;
        private const int ByteSize = 8;

        public BitReader(BinaryReader reader, int lengthReading)
        {
            _reader = reader;
            _lengthReading = lengthReading;
            _readed = new List<byte>();
        }
        /// <summary>
        /// Выдаёт следующий бит
        /// </summary>
        /// <returns></returns>
        public byte Next()
        {
            byte result = 0;
            if (_readed.Count > 0)
            {
                result = _readed.ElementAt(0);
                _readed.RemoveAt(0);
            }
            else if (_readed.Count == 0 && !_ended)
            {
                byte[] readedBytes = _reader.ReadBytes(_lengthReading);
                if (readedBytes.Length == 0)
                {
                    _ended = true;
                    result = 0;
                }
                else
                {
                    _readed = ToBits(readedBytes);
                    result = _readed.ElementAt(0);
                    _readed.RemoveAt(0);
                }
            }
            return result;
        }
        /// <summary>
        /// Конвертируем массив байтов в биты
        /// </summary>
        /// <param name="list"></param>
        /// <returns></returns>
        private List<byte> ToBits(byte[] list)
        {
            List<byte> result = new List<byte>();
            foreach (byte b in list)
            {
                result.InsertRange(result.Count, ToBits(b));
            }
            return result;
        }
        /// <summary>
        /// Конвертируем один байт в биты
        /// </summary>
        /// <param name="bt"></param>
        /// <returns></returns>
        private List<byte> ToBits(byte bt)
        {
            List<byte> list = new List<byte>();

            for (int i = 0; i < ByteSize; i++)
            {
                list.Insert(0, (byte)(bt % 2));
                bt = (byte)(bt >> 1);
            }
            return list;
        }
    }
}