/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.Collections.Generic;
using System.IO;

namespace CommonUtils.Util
{   
    /// <summary>
    /// Класс, накапливающий биты в буфер, после чего пишет их в файл.
    /// </summary>
    public class BitListBuffer :IDisposable
    {
        private List<byte> _list;
        private BinaryWriter _writer;
        private int _maxLength;
        private const int ByteLength = 8;
        /// <summary>
        /// 
        /// </summary>
        /// <param name="writer">Писатель дя буфера</param>
        /// <param name="maxLength">Длинна буфера</param>
        public BitListBuffer(BinaryWriter writer, int maxLength)
        {
            _writer = writer;
            _maxLength = maxLength;
            _list = new List<byte>();
        }

        public void Add(List<byte> list)
        {
            _list.AddRange(list);
            if (_list.Count / ByteLength >= _maxLength)
            {
                Write();
            }
        }

        public void Add(byte b)
        {
            _list.Add(b);
            if (_list.Count / ByteLength >= _maxLength)
            {
                Write();
            }
        }

        public void Write()
        {
            byte[] toWrite = new byte[_list.Count / ByteLength];
            int counter = 0;
            while (_list.Count / ByteLength > 0)
            {
                List<byte> part = _list.GetRange(0, ByteLength);
                toWrite[counter] = BinaryUtils.ConstructByte(part);
                _list.RemoveRange(0, ByteLength);
                counter++;
            }
            _writer.Write(toWrite);
        }

        public void Close()
        {
            while (_list.Count % ByteLength != 0)
            {
                _list.Add(0);
            }
            Write();
        }

        //private static byte Construct(List<byte> lst)
        //{
        //    int result = 0;

        //    foreach (byte el in lst)
        //    {
        //        result = (result << 1) + el;
        //    }

        //    return (byte)result;
        //}

        public void Dispose()
        {
            Close();
            _writer.Close();
        }
    }
}