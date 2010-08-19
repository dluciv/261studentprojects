/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    class StringComparer : IEqualityComparer<String>
    {
        public bool Equals(String x, String y)
        {
            return String.Equals(x, y, StringComparison.CurrentCulture);
        }
        //The One-Time-Hash algorithm
        public int GetHashCode(String s)
        {
            int h = 0;
 
            for (int i = 0; i < s.Length; i++ ) 
            {
                h += s[i];
                h += ( h << 10 );
                h ^= ( h >> 6 );
            }
 
            h += ( h << 3 );
            h ^= ( h >> 11 );
            h += ( h << 15 );
 
            return h;
        }
    }
}

//int h = 2166136261;
//int i;
//for (i = 0; i < s.Length; i++)
//{
//    h = (h * 16777619) ^ s[i];
//}
//return h;