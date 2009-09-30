using System;
using DB3.Interfaces;
using DB3.Util;

namespace DB3.Tree
{
    [Serializable]
    class Leaf<TData> : ITree where TData : DBData
    {
        private SortedList<TData> _data;

        public Leaf()
        {
            _data = new SortedList<TData>();
        }
        public void AddData(TData data)
        {
            _data.Add(data);
        }
        public SortedList<TData> GetAllData()
        {
            return _data;
        }
    }
}
