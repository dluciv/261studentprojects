using System;

namespace DB3.DBGenerator
{
    class RandomAddressGenerator
    {
        private static readonly string[] streets = { "Проспект просвежения", "Гражданский проспект", 
                                                       "3 улица строителей", "2 улица строителей", 
                                                       "1 улица строителей", "Невский проспект", 
                                                       "Ботаническая улица", "Ленинский проспект", 
                                                       "Ул. Коммивояжеров", "Моя крутая улица",
                                                   "Ул. красивых молдавский партизан", "Ул. Ушинского",
                                                   "Пр. маршала Жукова", "Измайловский проспект"};

        public static int MaxHouse = 200;
        public static int MaxRoom = 100;
        public static string HouseString = ", д. ";
        public static string RoomString = " кв. ";

        public static string Generate() 
        {
            Random r = new Random();
            return streets[((int) (r.NextDouble() * (streets.Length - 1)))] + HouseString +
                    r.Next(MaxHouse) + RoomString + r.Next(MaxRoom);
        }

    }
}
