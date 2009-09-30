using System;
using DB3.Tree;

namespace DB3.Interfaces
{
    [Serializable]
    abstract class Key: ICloneable,IComparable
    {
        public ITree Link { get; set; }
        public ITree Container { get; set;}

        public Key(ITree link)
        {
            Link = link;
        }

        public abstract object Clone();
        public abstract int CompareTo(object obj);
    }
}
