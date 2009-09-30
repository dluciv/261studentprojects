/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System.Collections.Generic;

namespace CommonUtils.Util
{
    /// <summary>
    /// Класс реализующий алгоритм сжатия RLE, использующийся для сжимания словоря
    /// </summary>
    public class RLE
    {
        private const int ByteCount = 256;

        /// <summary>
        /// Сжатие входного списка алгоритмом RLE
        /// </summary>
        /// <param name="list"></param>
        /// <returns></returns>
        public static List<byte> Compress(byte[] list)
        {
            List<Pair<byte, byte>> result = new List<Pair<byte, byte>>();
            int count = 1;
            byte last = list[0];
            for (int i = 1; i < ByteCount; i++)
            {
                if (list[i] == last)
                {
                    count++;
                }
                else
                {
                    result.Add(new Pair<byte, byte>(last, (byte)count));
                    count = 1;
                    last = list[i];
                }
            }
            result.Add(new Pair<byte, byte>(last, (byte)count));

            List<byte> compressed = new List<byte>();
            foreach (Pair<byte, byte> pair in result)
            {
                compressed.Add(pair.First);
                compressed.Add(pair.Second);
            }
            return compressed;
        }
    }
}