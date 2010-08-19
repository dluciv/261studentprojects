/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    public interface IArithmeticNode : ITreeNode
    {
        void setNegative();
    }
}
