using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using HelloLibrary;

namespace ConsoleApplication1
{
    class HelloWorld
    {
        static void Main(string[] args)
        {
            try
            {
                HelloLibrary.Library.HelloAll(args[0]);
            }
            catch
            {
                HelloLibrary.Library.HelloAll("");
            }
        }
    }
}
