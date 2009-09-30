using System;
using DB3.Interfaces;

namespace DB3.Tree
{
    [Serializable]
    class BTree<TIndx,TKey,TData>: ITree 
        where TIndx: IndxData<TKey,TData>
        where TKey: Key
        where TData: DBData
    {
        public Node<TKey, TData> Root { get; set;}
        private int _capacity;

        public BTree(int capacity)
        {
            _capacity = capacity;
            Root = new Node<TKey, TData>(capacity);
        }

        public void Add(TIndx indxData)
        {
            TKey key = indxData.ExtractKey();
            TData data = indxData.ExtractData();
            Root.Add(key, data);
            if (Root.Root != null)
            {
                Root = Root.Root;
            }
        }
        public TKey KeyAfter(TKey current)
        {
            Node<TKey, TData> container = (Node<TKey, TData>) current.Container;
            if (current == null)
                return null;

            int index = container.IndexOfKey(current);
            if (index != -1 && index + 1 < container.KeysCount())
            {
                return container.GetKey(index + 1);
            }
            else
            {

                if (index + 1 >= container.KeysCount())
                {
                    if (container.Link == null)
                    {
                        return null;
                    }
                    else
                    {
                        if (container.Link is Node<TKey, TData>)
                        {
                            return container.Link.FirstKey();
                        }
                    }
                }
            }
            return null;
        }
        public TKey Find(TKey startCondition)
        {
            return Find(Root, startCondition);
        }
        private TKey Find(Node<TKey, TData> node, TKey startCondition)
        {
            if (node == null) 
                return null;
            if (node.HaveChildrenNodes())
            {
                TKey suitableKey = node.GetSuitableKey(startCondition);
                if (suitableKey == null)
                {
                    return Find(node.Link, startCondition);
                }
                else
                {
                    return Find((Node<TKey, TData>) suitableKey.Link, startCondition);
                }
            }
            else
            {
                return node.GetSuitableKey(startCondition);
            }
        }
    }
}