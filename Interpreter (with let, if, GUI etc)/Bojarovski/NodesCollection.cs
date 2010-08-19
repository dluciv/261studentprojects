/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    class NodesCollection : IEnumerable
    {
        public IEnumerator GetEnumerator()
        {
            return (IEnumerator)this;
        }
    }
}