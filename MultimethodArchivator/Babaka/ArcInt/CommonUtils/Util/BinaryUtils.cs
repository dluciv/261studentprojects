/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System.Collections.Generic;
using System.Linq;

namespace CommonUtils.Util
{
    /// <summary>
    /// Класс для работы с внутренним представлением битов
    /// </summary>
    public class BinaryUtils
    {
        public static List<byte> GenerateCode(byte level, int index)
        {
            List<byte> bin = ToBinary(index);
            byte zerosCount = (byte)(level - bin.Count);
            for (int i = 0; i < zerosCount; i++)
            {
                bin.Insert(0, 0);

            }
            return bin;
        }

        public static List<byte> ToBinary(int value)
        {
            List<byte> res = new List<byte>();

            while (value != 0)
            {
                res.Insert(0, (byte)(value % 2));
                value = value >> 1;
            }

            return res;
        }

        public static byte ConstructByte(IEnumerable<byte> seq)
        {
            byte res = 0;
            foreach (int i in seq)
            {
                res = (byte)((res << 1) + i);
            }
            return res;
        }

        public static bool CodesEquals(List<byte> l1, List<byte> l2)
        {
            if (l1.Count != l2.Count) return false;
            for (int i = 0; i < l1.Count; i++)
            {
                if (!l1.ElementAt(i).Equals(l2.ElementAt(i))) return false;
            }
            return true;
        }
    }
}