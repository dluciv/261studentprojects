using System;
using DB3.Interfaces;
using DB3.Util;

namespace DB3.Tree
{
    [Serializable]
    class Node<TKey,TData>: ITree 
        where TKey: Key 
        where TData:DBData
    {
        private SortedList<TKey> _keys;
        private int _capacity;
        public Node<TKey, TData> Link { get; set; }
        public Node<TKey, TData> LinkFrom { get; set; }
        public Node<TKey, TData> Root { get; set; }

        private Node(int capacity, Node<TKey,TData> root, SortedList<TKey> list)
        {
           _capacity = capacity;
            Root = root;
            _keys = list;
            Link = null;
        }
        public Node(int capacity, Node<TKey, TData> root)
        {
            _capacity = capacity;
            Root = root;
            _keys = new SortedList<TKey>();
            Link = null;
        }
        public Node(int capacity)
        {
            _capacity = capacity;
            Root = null;
            _keys = new SortedList<TKey>();
            Link = null;
        }

        public void Add(TKey key, TData data)
        {
            if (key == null) 
                return;
            if (HaveChildrenNodes())
            {
                bool found = false;
                foreach (TKey curKey in _keys)
                {
                    if (key.CompareTo(curKey) <= 0)
                    {
                        found = true;
                        ((Node<TKey,TData>)curKey.Link).Add(key, data);
                        break;
                    }
                }
                if (!found)
                {
                    if (Link == null)
                    {
                        Link = new Node<TKey, TData>(_capacity);
                    }
                    Link.Add(key, data);
                }
            }
            else
            {
                if (!_keys.Contains(key))
                {
                    Leaf<TData> leaf = new Leaf<TData>();
                    leaf.AddData((data));
                    key.Link = leaf;
                    AddKey(key);
                }
                else
                {
                    ((Leaf<TData>)_keys[_keys.IndexOf(key)].Link).AddData((data));
                }
            }
        }
        public void AddKey(TKey key)
        {
            _keys.Add(key);
            key.Container = this;

            if (_keys.Count > _capacity)
            {
                SortedList<TKey> lst = _keys.GetFirstHalf();
                bool RootWasNull = false;

                if (Root == null)
                {
                    Root = new Node<TKey, TData>(_capacity);
                    RootWasNull = true;
                }

                TKey newKey = lst[lst.Count - 1].Clone() as TKey;
                Node<TKey, TData> newNode = new Node<TKey, TData>(_capacity, Root, lst);
                newKey.Link = newNode;

                foreach (TKey k in lst)
                {
                    k.Container = newNode;
                    if (k.Link is Node<TKey, TData>)
                    {
                        (k.Link as Node<TKey, TData>).Root = newNode;
                    }
                }

                if (!HaveChildrenNodes())
                {
                    if (LinkFrom != null)
                    {
                        LinkFrom = newNode;
                        newNode.LinkFrom = LinkFrom;
                    }
                    newNode.LinkFrom = this;
                    LinkFrom = newNode;
                }

                Root.AddKey(newKey);

                if (RootWasNull)
                    Root.Link = this;
            }
        }

        public bool HaveChildrenNodes()
        {
            foreach (TKey k in _keys)
            {
                if ((k.Link != null) && (k.Link is Node<TKey,TData>))
                {
                    return true;
                }
            }
            return false;
        }
        public int KeysCount()
        {
            return _keys.Count;
        }
        public TKey GetKey(int index)
        {
            return _keys[index];
        }
        public TKey FirstKey()
        {
            return ((_keys == null) || (_keys.Count == 0)) ? null : _keys[0];
        }
        public int IndexOfKey(TKey key)
        {
            return _keys.IndexOf(key);
        }
        public TKey GetSuitableKey(TKey keyToFind)
        {
            if (keyToFind == null)
                return null;
            foreach (TKey key in _keys)
            {
                if (keyToFind.CompareTo(key) < 1)
                    return key;
            }
            return null;
        }
    }
}