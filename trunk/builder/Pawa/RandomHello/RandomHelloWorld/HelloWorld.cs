using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace RandomHelloWorld
{
    public class HelloWorld
    {
        RandomGenerator generator = new RandomGenerator();
        public void RandomPrint()
        {
            if (generator.getRandom().Next(2) == 1)
            {
                System.Console.WriteLine("Hello, World!");
            }
            else
            {
                System.Console.WriteLine("Bye, World!");
            }
        }
    }
}
