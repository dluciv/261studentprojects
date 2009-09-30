using System.Collections.Generic;
using DB3.Entities;
using DB3.Index;
using DB3.Parser;
using DB3.Tree;

namespace DB.Index
{
    class IndexBuilder
    {
        public static BTree<Card,DatabaseKey, AddressData> GenerateIndex(string filename, int capacity)
        {
            DatabaseParser parser = new DatabaseParser(filename);
            BTree<Card, DatabaseKey, AddressData> tree = new BTree<Card, DatabaseKey, AddressData>(capacity);
            List<Card> data = parser.Parse();
            foreach (Card card in data)
            {
                tree.Add(card);
            }
            return tree;
        }
    }
}
