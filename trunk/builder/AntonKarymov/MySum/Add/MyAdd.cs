using System;
using Print;

namespace Add
{
    class MyAdd
    {
        public static int sum(int number1,int number2) {
            return number1 + number2;
        }
        static void Main(string[] args)
        {
            Print.print.printAdd(sum(Convert.ToInt32(args[0]),Convert.ToInt32(args[1])));
        }

    }
}
