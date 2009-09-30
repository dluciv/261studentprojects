using System;
using DB3.Entities;

namespace DB3.DBGenerator
{
    class RandomLastNameGenerator
    {
        private static readonly string[] maleLastNames = { "Иванов", "Петров", "Сидоров", "Козлов",
                                                             "Яценюк", "Сидько", "Карпов","Кудрин",
                                                             "Путин","Медведев", "Горбачёв", "Лукашенко", 
                                                             "Яковлев", "Лужков", "Жириновский", "Покровский", 
                                                             "Фет","Пушкин", "Набоков", "Уэлш", "Фрай",
                                                         };
        private static readonly string[] femaleLastNames = {"Иванова", "Петрова", "Сидорова", "Козлова",
                                                             "Яценюк", "Сидько", "Карпова","Кудрина",
                                                             "Путин","Медведева", "Горбачёва", "Лукашенко", 
                                                             "Яковлева", "Лужкова", "Жириновская", "Покровская", 
                                                             "Фет","Пушкин", "Набокова", "Уэлш", "Фрай",
                                                         };

        public static string Generate(Sex sex)
        {
            string result = "";
            Random r = new Random();
            switch (sex)
            {
                case Sex.FEMALE:
                    result = femaleLastNames[(int)(r.NextDouble() * (femaleLastNames.Length - 1))];
                    break;
                case Sex.MALE:
                    result = maleLastNames[(int)(r.NextDouble() * (maleLastNames.Length - 1))];
                    break;
                case Sex.UNKNOWN:
                    result = maleLastNames[(int)(r.NextDouble() * (maleLastNames.Length - 1))];
                    break;
            }
            return result;
        }
    }
}
