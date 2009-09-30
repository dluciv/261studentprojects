using System;
using System.Collections;
using DB3.Entities;
using DB3.Index;
using DB3.Interfaces;
using DB3.Tree;
using DB3.Util;

namespace DB3.Iterator
{
    internal class DatabaseIterator : IEnumerable
    {
        private DatabaseKey _currentKey;

        private Condition _stopCondition;
        private BTree<Card, DatabaseKey, AddressData> _tree;

        private int _position;

        public DatabaseIterator(Condition startCondition, Condition stopCondition, BTree<Card, DatabaseKey, AddressData> tree)
        {
            _stopCondition = stopCondition;
            _tree = tree;
            _currentKey = tree.Find(new DatabaseKey(startCondition));
        }

        public bool HasNext()
        {
            return _currentKey != null;
        }

        public SortedList<AddressData> Next()
        {
            Key oldKey = _currentKey;
            if (oldKey == null || !(oldKey.Link is Leaf<AddressData>))
            {
                throw new IteratorException();
            }
            //Берем следующий ключ в дереве (в уровне)
            if (_currentKey != null)
            {
                _currentKey = _tree.KeyAfter(_currentKey);
                // если условие останова достигнуто, то итератор больше не возвращает ключей
                if (_currentKey != null && _currentKey.CompareTo(new DatabaseKey(_stopCondition)) > 0)
                {
                    _currentKey = null;
                }
            }

            return ((Leaf<AddressData>)oldKey.Link).GetAllData();
        }

        public IEnumerator GetEnumerator()
        {
            throw new NotImplementedException();
        }
    }
}
