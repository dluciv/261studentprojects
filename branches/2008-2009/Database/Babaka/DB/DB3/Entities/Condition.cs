using System;

namespace DB3.Entities
{
    [Serializable]
    public class Condition: ICloneable, IComparable
    {
        public string Name { get; set; }
        public string LastName { get; set; }
        public string MiddleName { get; set; }

        private static readonly Condition Infinity = new Condition("", "", "");

        public Condition(string name, string middleName, string lastName)
        {
            LastName = lastName;
            Name = name;
            MiddleName = middleName;
        }

        public int CompareTo(Object o) 
        {
            if(o == null) 
                return 1;
            if(! (o is Condition)) 
                return 1;
            if(this.Equals(Infinity)) 
                return -1;

            Condition c= (Condition) o;

            if(c.Equals(Infinity)) 
                return -1;

            int ln = LastName == null ? -1 : LastName.CompareTo(c.LastName);
            int n = Name == null ? -1 : Name.CompareTo(c.Name);
            int mn = MiddleName == null ? -1 : MiddleName.CompareTo(c.MiddleName);

            if(ln != 0) 
                return ln;
            if(n != 0) 
                return n;
            return mn;
        }

        //public static bool operator ==(Condition fst, Condition snd)
        //{
        //    return fst.Equals(snd);
        //}

        //public static bool operator !=(Condition fst, Condition snd)
        //{
        //    return !(fst == snd);
        //}

        //public static bool operator >(Condition fst, Condition snd)
        //{
        //    return (fst.CompareTo(snd) > 0);
        //}

        //public static bool operator <(Condition fst, Condition snd)
        //{
        //    return (fst.CompareTo(snd) < 0);
        //}

        public object Clone()
        {
            return new Condition(Name, MiddleName, LastName);
        }

        public override bool Equals(Object o) 
        {
            if (this == o) 
                return true;

            if (o == null || GetType() != o.GetType()) return false;

            Condition condition = (Condition) o;

            if (LastName != null ? !LastName.Equals(condition.LastName) : condition.LastName != null) return false;
            if (MiddleName != null ? !MiddleName.Equals(condition.MiddleName) : condition.MiddleName != null) return false;
            if (Name != null ? !Name.Equals(condition.Name) : condition.Name != null) return false;

            return true;
        }

        public override int GetHashCode()
        {
            int result = LastName != null ? LastName.GetHashCode() : 0;
            result = 31 * result + (Name != null ? Name.GetHashCode() : 0);
            result = 31 * result + (MiddleName != null ? MiddleName.GetHashCode() : 0);
            return result;
        }

        //public override string ToString()
        //{
        //    return "Condition(" + LastName + ", " + Name + ", " + MiddleName + ")";
        //}

    }
}
