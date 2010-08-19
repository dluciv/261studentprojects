/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public class Pair<L, R>
    {
        private L left;
        private R right;

        public L getLeft()
        {
            return left;
        }
        public R getRight()
        {
            return right;
        }
        public void setRight(R newRight)
        {
            this.right = newRight;
        }
        public void setLeft(L newLeft)
        {
            this.left = newLeft;
        }
        public Pair(L left, R right)
        {
            this.left = left;
            this.right = right;
        }
    }
}
