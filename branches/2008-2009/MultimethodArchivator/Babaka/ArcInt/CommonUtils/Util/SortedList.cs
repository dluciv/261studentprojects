/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace CommonUtils.Util
{
    public class SortedList<T>: IList<T>
        where T : IComparable
    {
        private List<T> _list;

        public SortedList()
        {
            _list = new List<T>();
        }

        public void Add(T item)
        {
            bool inserted = false;
            for (int i = 0; i < _list.Count; i++)
            {
                if (item.CompareTo(_list.ElementAt(i)) <= 0)
                {
                    _list.Insert(i, item);
                    inserted = true;
                    break;
                }
            }
            if (!inserted)
            {
                _list.Add(item);
            }
        }

        public int Count()
        {
            return _list.Count;
        }

        public IEnumerator<T> GetEnumerator()
        {
            return _list.GetEnumerator();
        }

        override public String ToString()
        {
            String res = "[";
            foreach (T el in _list)
            {
                res += el + ", ";
            }
            return res + "]";
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        public void Clear()
        {
            _list.Clear();
        }

        public bool Contains(T item)
        {
            return _list.Contains(item);
        }

        public void CopyTo(T[] array, int arrayIndex)
        {
            _list.CopyTo(array, arrayIndex);
        }

        public bool Remove(T item)
        {
            return _list.Remove(item);
        }

        int ICollection<T>.Count
        {
            get { return _list.Count; }
        }

        public bool IsReadOnly
        {
            get { return false;}
        }

        public int IndexOf(T item)
        {
            return _list.IndexOf(item);
        }

        public void Insert(int index, T item)
        {
            _list.Insert(index, item);
        }

        public void RemoveAt(int index)
        {
            _list.RemoveAt(index);
        }

        public T this[int index]
        {
            get { return _list[index]; }
            set { _list[index] = value; }
        }
    }
}