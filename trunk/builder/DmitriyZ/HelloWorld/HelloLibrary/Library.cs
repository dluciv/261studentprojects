using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HelloLibrary
{
    public class Library
    {
    public static void HelloAll(string sl)
    {
        if (sl == "Hello") 
        {
            Console.Out.WriteLine("Hello, my Soviet friend!");
        }
        else if (sl == "")
        {
            Console.Out.WriteLine("Not good!");
        }
        else
        {
            Console.Out.WriteLine("Hello World!!!");
        }
    }
    }
}
