/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;

namespace CommonUtils.Util
{
    /// <summary>
    /// Пара, на которой введено отношение порядка по первому элементу
    /// </summary>
    /// <typeparam name="TFirst"></typeparam>
    /// <typeparam name="TSecond"></typeparam>
    public class Pair<TFirst, TSecond>: IComparable
        where TFirst : IComparable
    {
        public TFirst First
        {
            get; set;
        }

        public TSecond Second
        {
            get; set;
        }
        public Pair(TFirst first, TSecond second)
        {
            First = first;
            Second = second;
        }

        public int CompareTo(object obj)
        {
            if (obj == null || !(obj is Pair<TFirst, TSecond>))
            {
                return 1;
            }
            return First.CompareTo((obj as Pair<TFirst, TSecond>).First);
        }
    }
}