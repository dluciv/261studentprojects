using System;
using System.Collections;
using System.Collections.Generic;

namespace DB3.Util
{
    [Serializable]
    public class SortedList<T> : IList<T> where T : IComparable  
    {
        private List<T> _list;

        public bool Remove(T item)
        {
            return _list.Remove(item);
        }

        public int Count
        { 
            get { return _list.Count; }
        }
        /// <summary>
        /// not implement
        /// </summary>
        bool ICollection<T>.IsReadOnly
        {
            get { return false; }
        }

        public SortedList()
        {
            _list = new List<T>();
        }

        public SortedList(IEnumerable<T> collection)
        {
            _list = new List<T>(collection);
            _list.Sort();
        }

        public void Add(T item)
        {
            if (Count!=0)
            {
                for (int i = 0; i < _list.Count;++i )
                {
                    if (_list[i].CompareTo(item) >= 0)
                    {
                        _list.Insert(i,item);
                        return;
                    }
                }
                _list.Insert(0,item);
            }
            else
            {
                _list.Add(item);
            }
        }

        public void Clear()
        {
            _list.Clear();
        }

        public IEnumerator<T> GetEnumerator()
        {
            return _list.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        public bool Contains(T item)
        {
            return _list.Contains(item);
        }

        public void CopyTo(T[] array, int arrayIndex)
        {
            _list.CopyTo(array, arrayIndex);
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

        public void RemoveRange(int index, int count)
        {
            _list.RemoveRange(index, count);
        }

        public SortedList<T> GetFirstHalf()
        {
            SortedList<T> tmp = new SortedList<T>(_list.GetRange(0, Count/2));
            _list.RemoveRange(0,Count/2);
            return tmp;
        }
    }
}
