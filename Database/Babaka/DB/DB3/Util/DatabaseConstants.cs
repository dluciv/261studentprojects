using System;
using DB3.Entities;

namespace DB3.Util
{
    class DatabaseConstants
    {
        public static int DefaultPhoneLength = 10;
        private const string DefaultDbSexMaleValue = "0";
        private const string DefaultDbSexFemaleValue = "1";
        private const string DefaultDbSexUnknownValue = "2";

        public static string GetIdBySex(Sex sex)
        {
            switch (sex) 
            {
                case Sex.FEMALE:
                    return DefaultDbSexFemaleValue;
                case Sex.MALE:
                    return DefaultDbSexMaleValue;
            }
            return DefaultDbSexUnknownValue;
        }
        public static Sex GetSexById(string id)
        {
            if (id == null) 
                return Sex.UNKNOWN;
            if (id.Equals(DefaultDbSexFemaleValue))
            {
                return Sex.FEMALE;
            }
            else
            {
                if (id.Equals(DefaultDbSexMaleValue))
                {
                    return Sex.MALE;
                }
                else
                {
                    return Sex.UNKNOWN;
                }
            }
        }
    }
}