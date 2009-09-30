/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System.Collections.Generic;
using System.IO;

namespace CommonUtils.Util
{
    /// <summary>
    /// Буфер записывающий байты в файл, после того как он заполнился
    /// </summary>
    public class BytesBuffer
    {
        private List<byte> _bytes;
        private int _maxLength;
        private BinaryWriter _writer;

        public BytesBuffer(BinaryWriter writer, int maxLength)
        {
            _writer = writer;
            _maxLength = maxLength; _bytes = new List<byte>();
        }

        public void Add(byte b)
        {
            _bytes.Add(b);
            if (_bytes.Count >= _maxLength)
            {
                Write();
            }
        }

        public void Write()
        {
            _writer.Write(_bytes.ToArray());
            _bytes = new List<byte>();
        }

        public void Close()
        {
            Write();
        }
    }
}