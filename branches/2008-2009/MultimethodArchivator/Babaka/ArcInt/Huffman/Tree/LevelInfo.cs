/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System;
using System.Collections.Generic;
using System.Linq;
using CommonUtils.Util;

namespace Huffman.Tree
{
    /// <summary>
    /// Хранит информацию об уровне дерева: количество узлов в нем и список символов
    /// узлы необходимы для того, что бы рассчитать значение кода сивола
    /// </summary>
    public class LevelInfo
    {
        private byte _nodeCount;
        private SortedList<byte> _bytes;

        public LevelInfo()
        {
            _nodeCount = 0;
            _bytes = new SortedList<byte>();
        }

        public void AddNode()
        {
            _nodeCount++;
        }

        public void AddByte(byte b)
        {
            _bytes.Add(b);
            //if (!_bytes.Contains(b))
            //{
            //    bool inserted = false;
            //    for (int i = 0; i < _bytes.Count(); i++)
            //    {
            //        if (_bytes.ElementAt(i) > b)
            //        {
            //            _bytes.Insert(i, b);
            //            inserted = true;
            //            break;
            //        }
            //    }
            //    if (!inserted)
            //    {
            //        _bytes.Add(b);
            //    }
            //}
        }

        public SortedList<byte> Bytes()
        {
            return _bytes;
        }

        public int Index(byte symbol)
        {
            return _bytes.IndexOf(symbol) + _nodeCount;
        }

        public override string ToString()
        {
            String res = "nodes: " + _nodeCount + " symbols: [";
            foreach (byte el in _bytes)
            {
                res += el + ", ";
            }
            return res + "]";

        }
    }
}
