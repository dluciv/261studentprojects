using System;
using DB3.Entities;

namespace DB3.DBGenerator
{
    class RandomMiddleNameGenerator
    {
        private static readonly string[] maleMiddleNames = { "Анатольевич", "Вячиславович", "Викторович", 
                                                               "Максимович", "Сергеевич", "Игоревич",
                                                               "Кириллович", "Петрович", "Романович",
                                                               "Аркадьевич", "Сидорович", "Константинович",
                                                               "Иванович", "Степанович", "Львович"
                                                           };
        private static readonly string[] femaleMiddleNames = { "Анатольевна", "Вячиславовна", "Викторовна", 
                                                               "Максимовна", "Сергеевна", "Игоревна",
                                                               "Кирилловна", "Петровна", "Романовна",
                                                               "Аркадьевна", "Сидоровна", "Константиновна",
                                                               "Ивановна", "Степановна", "Львовна"
                                                           };

        public static string Generate(Sex sex)
        {
            string result = "";
            Random r = new Random();
            switch (sex)
            {
                case Sex.FEMALE:
                    result = femaleMiddleNames[(int)(r.NextDouble() * (femaleMiddleNames.Length - 1))];
                    break;
                case Sex.MALE:
                    result = maleMiddleNames[(int)(r.NextDouble() * (maleMiddleNames.Length - 1))];
                    break;
                case Sex.UNKNOWN:
                    result = maleMiddleNames[(int)(r.NextDouble() * (maleMiddleNames.Length - 1))];
                    break;
            }

            return result;
        }
    }
}
