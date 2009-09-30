using System;
using DB3.Index;
using DB3.Interfaces;

namespace DB3.Entities
{
    class Card: IndxData<DatabaseKey,AddressData>
    {
        public string Name { get; set; }
        public string LastName { get; set; }
        public string MiddleName { get; set; }
        public Sex sex { get; set; }
        public string Phone { get; set; }
        public string Address { get; set; }
        public int FilePosition { get; set; }
        public int ByteLenght { get; set; }

        public Card(String name, String lastName, String middleName, Sex sex,
            String phone, String address)
        {
            Name = name;
            LastName = lastName;
            MiddleName = middleName;
            this.sex = sex;
            Phone = phone;
            Address = address;
        }

        public DatabaseKey ExtractKey()
        {
            return new DatabaseKey(new Condition(Name, MiddleName, LastName), null);
        }
        public AddressData ExtractData()
        {
            return new AddressData(FilePosition);
        }

        public int CompareTo(object obj)
        {
            return (new Condition(Name, MiddleName, LastName)).CompareTo(0);
        }
    }
}
