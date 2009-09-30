using System;
using System.Data;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using DB.Index;
using DB3.DBGenerator;
using DB3.Parser;
using DB3.Entities;
using DB3.Index;
using DB3.Iterator;
using DB3.Tree;
using DB3.Util;

namespace DB3
{
    public class DBFacade
    {
        private static DBFacade _instance;

        private const string StartCreate = "Start generate DB";
        private const string EndCreate = "Generate finished";
        private const string StartBuildInx = "Start build index";
        private const string BuildFail = "Index build failed";
        private const string EndBuildInx = "Build finished";
        private const string StartFind = "Find keys";
        private const string EndFind = "Keys found";

        private const string NameColumn = "Name";
        private const string LastNameColumn = "LastName";
        private const string MiddleNameColumn = "MiddleNameName";
        private const string PhoneColumn = "Phone";
        private const string AdressColumn = "Address";

        private string _currentConnect;
        private BTree<Card, DatabaseKey, AddressData> _index;
        private DatabaseParser _parser;

        public bool IsConnect
        {
            get { return (_currentConnect.Length != 0); }
        }

        private DBFacade()
        {
        }

        public static DBFacade Instance()
        {
            if (_instance == null)
            {
                _instance = new DBFacade();
            }

            return _instance;
        }

        /// <summary>
        /// Generate DB 
        /// </summary>
        /// <param name="dbName">DB path</param>
        /// <param name="cardsCount">Cards count in DB</param>
        public void Generate(string dbName, int cardsCount)
        {
            
            Logger.Instance().Write(StartCreate);
            DatabaseGenerator generator = new DatabaseGenerator();
            generator.Generate(dbName, cardsCount);
            Logger.Instance().Write(EndCreate);
        }
        /// <summary>
        /// Establish connet between DB and FE(GUI)
        /// </summary>
        /// <param name="dbPath">Path to DB</param>
        /// <param name="capacity">Tree capasity</param>
        /// <returns></returns>
        public int Connect(string dbPath, int capacity)
        {
            try
            {
                Logger.Instance().Write(StartBuildInx);
                BTree<Card, DatabaseKey, AddressData> tree = IndexBuilder.GenerateIndex(dbPath, capacity);
                BinaryFormatter formatter = new BinaryFormatter();
                _currentConnect = Helper.Instance().CreateIndexName(dbPath);
                using(FileStream fs = File.Open(_currentConnect, FileMode.Create))
                {
                    formatter.Serialize(fs, tree);
                }
                Logger.Instance().Write(EndBuildInx);

                return 0;
            }
            catch (Exception e)
            {
                Logger.Instance().Write(BuildFail + e.Message);
                return 1;
            }        
        }
        /// <summary>
        /// Disconnect
        /// </summary>
        public void Disconnect()
        {
            File.Delete(_currentConnect);
            _currentConnect = "";
            _index = null;
            _parser = null;
        }

        public DataTable Request(string[] args)
        {
            Condition startCondition = new Condition(args[0],args[1],args[2]);
            Condition endCondition = new Condition(args[3],args[4], args[5]);
           
            if (_index == null)
            { 
                BinaryFormatter deserializer = new BinaryFormatter();
                using (FileStream fs = File.Open(_currentConnect, FileMode.Open))
                {
                    _index = deserializer.Deserialize(fs) as BTree<Card, DatabaseKey, AddressData>;
                }   
            }

            DatabaseIterator iterator = new DatabaseIterator(startCondition, endCondition, _index);

            DataTable table = InitTable();

            if (_parser == null)
            {
                _parser = new DatabaseParser(Helper.Instance().RestoreDBPath(_currentConnect));
            }

            while (iterator.HasNext())
            {
                Logger.Instance().Write(StartFind);
                SortedList<AddressData> list = iterator.Next();
                Logger.Instance().Write(EndFind);
                foreach (var data in list)
                {
                    Card card = _parser.ParseSingle(data.Address);
                    table.Rows.Add(BuildRow(card));
                }
            }
            return table;
        }

        private object[] BuildRow(Card card)
        {
            object[] items = new object[5];
            items[0] = card.Name;
            items[1] = card.MiddleName;
            items[2] = card.LastName;
            items[3] = card.Phone;
            items[4] = card.Address;
            return items;
        }

        private DataTable InitTable()
        {
            DataTable dt = new DataTable();

            dt.Columns.Add(NameColumn, NameColumn.GetType());
            dt.Columns.Add(MiddleNameColumn, MiddleNameColumn.GetType());
            dt.Columns.Add(LastNameColumn, LastNameColumn.GetType());
            dt.Columns.Add(PhoneColumn, PhoneColumn.GetType());
            dt.Columns.Add(AdressColumn, AdressColumn.GetType());

            return dt;
        }
    }
}