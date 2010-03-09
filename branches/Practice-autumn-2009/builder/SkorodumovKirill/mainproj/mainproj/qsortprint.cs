using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using qsort;

namespace qsortprint
{
        class MainClass
        {
            static int Main(string[] args)
            {
                if (args.Length == 0)
                {
                    Console.WriteLine("You must enter an integer!");
                    return 0;
                }
                int size = Convert.ToInt32(args[0]);
                ArrayClass array = new ArrayClass(size);
                Random r = new Random();
                for (int i = 0; i < size; i++)
                    array.Add(r.Next(size));
                array.Dump();
                Console.WriteLine();
                array.QSort();
                array.Dump();
                //Console.ReadKey();
                return 0;
            }
        }
    }
