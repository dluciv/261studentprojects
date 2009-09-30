using System;
using DB3.Interfaces;

namespace DB3.Index
{
    [Serializable]
    public class AddressData : DBData
    {
        public int Address { get; private set; }

        public AddressData(int address)
        {
            Address = address;
        }

        /// <summary>
        /// Not need here
        /// </summary>
        /// <param name="obj"></param>
        /// <returns>NotImplementedException</returns>
        public int CompareTo(object obj)
        {
            throw new NotImplementedException();
        }
    }
}
