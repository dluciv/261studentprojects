using System;
using DB3.Entities;

namespace DB3.DBGenerator
{
    class RandomNameGenerator
    {
        private static readonly string[] maleNames = { "Александр", "Евгений", "Никита", "Константин", 
                                                         "Аркадий", "Виктор", "Сергей", "Владимир", 
                                                         "Дмитрий", "Федор", "Валерий", "Николай", 
                                                         "Лев", "Юрий", "Михаил", "Роман" };

        private static readonly string[] femaleNames = {"Анастасия", "Светлана", "Ольга", "Варвара",
                                                        "Мария", "Евгения", "Татьяна", "Екатерина", 
                                                        "Вера", "Надежда", "Кристина", "Анна", 
                                                        "Алена", "Елена", "Алла","Ксения" };


        public static string Generate(Sex sex)
        {
            string result = "";
            Random r = new Random();
            switch (sex)
            {
                case Sex.FEMALE:
                    result = femaleNames[(int)(r.NextDouble() * (femaleNames.Length - 1))];
                    break;
                case Sex.MALE:
                    result = maleNames[(int)(r.NextDouble() * (maleNames.Length - 1))];
                    break;
                case Sex.UNKNOWN:
                    result = maleNames[(int)(r.NextDouble() * (maleNames.Length - 1))];
                    break;
            }
            return result;
        }
    }
}
