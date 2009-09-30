using System;
using DB3.Entities;
using DB3.Interfaces;
using DB3.Tree;

namespace DB3.Index
{
    [Serializable]
    class DatabaseKey : Key
    {
        public Condition condition
        {
            get; private set;
        }

        public DatabaseKey(Condition condition, ITree link)
            : base(link)
        {
            this.condition = condition;
        }
        public DatabaseKey(Condition condition)
            : base(null)
        {
            this.condition = condition;
        }

        public override object Clone()
        {
            return new DatabaseKey(condition.Clone() as Condition, Link);
        }
        public override int CompareTo(object o)
        {
            if (o == null)
                return 1;
            if (o.GetType() != GetType())
                return 1;
            DatabaseKey dbk = (DatabaseKey)o;

            return condition == null ? -1 : condition.CompareTo(dbk.condition);
        }

        //public override bool Equals(object o)
        //{
        //    return CompareTo(o) == 0;
        //}

        //public static bool operator ==(DatabaseKey fst, DatabaseKey snd)
        //{
        //    try
        //    {
        //        return fst.Equals(snd);
        //    }
        //    catch (NullReferenceException)
        //    {
        //        try
        //        {
        //            if (snd.CompareTo(null) != 0)
        //            {
        //                return false;
        //            }
        //        }
        //        catch (Exception)
        //        {
        //            return true; 
        //        }
        //        return true; 
        //    }           
        //}

        //public static bool operator !=(DatabaseKey fst, DatabaseKey snd)
        //{
        //    return !(fst == snd);
        //}

        //public static bool operator >(DatabaseKey fst, DatabaseKey snd)
        //{
        //    return fst.condition > snd.condition;
        //}

        //public static bool operator <(DatabaseKey fst, DatabaseKey snd)
        //{
        //    return fst.condition < snd.condition;
        //}

        //public override string ToString()
        //{
        //    return "DatabaseKey(" + condition + ")";
        //}

        public bool Equals(DatabaseKey other)
        {
            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;
            return base.Equals(other) && Equals(other.condition, condition);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode() + condition.GetHashCode();
        }
    }
}
