using System;
using System.Collections.Generic;
using System.Text;

namespace RandomGenerator
{
    class PrintRandom
    {
        static RndGenerator rnd = new RndGenerator();
        public static void Main()
        {
            System.Console.WriteLine(rnd.GetRandom());
        }

    }
}