using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace RandomHelloWorld
{
    class RandomGenerator
    {
        private Random rnd = new Random();

        public Random getRandom()
        {
            return rnd;
        }
    }
}
