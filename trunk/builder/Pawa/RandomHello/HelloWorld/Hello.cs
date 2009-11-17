using RandomGenerator;

namespace HelloWorld
{
    class Hello
    {
        static RndGenerator rnd = new RndGenerator();
        public static void Main()
        {
            if (rnd.GetRandom() == 1)
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
