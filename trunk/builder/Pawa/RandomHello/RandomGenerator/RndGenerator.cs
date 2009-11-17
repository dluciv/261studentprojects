using System;
using System.Collections.Generic;
using System.Text;

namespace RandomGenerator
{
    public class RndGenerator
    {
        int random = 0;
        private Random rnd = new Random();
        public int GetRandom()
        {
            random = rnd.Next(2);
            return random;
        }
    }
}
