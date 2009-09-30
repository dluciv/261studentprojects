/// © Ѕабак ёрий, 261 гр., ћатћех, —ѕб√”
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace CommonUtils.Util
{
    /// <summary>
    ///  ласс позвол€ющий писать список байтов кусками, а не по одному
    /// </summary>
    public class WritingUtilities
    {
        private const int WritingBuffer = 4;

        public static void WriteList(BinaryWriter writer, List<byte> list)
        {
            while (list.Count >= WritingBuffer)
            {
                writer.Write(list.Take(WritingBuffer).ToArray());
                list.RemoveRange(0, WritingBuffer);
            }

            if (list.Count > 0)
            {
                writer.Write(list.ToArray());
            }
        }
    }
}