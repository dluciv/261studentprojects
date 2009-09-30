using System;
using System.Data;
using System.Windows.Forms;
using DB3;

namespace GUI
{
    class DBConnector
    {
        private static DBConnector _instance;

        delegate object[] AsyncMethodCaller(string[] args);

        private DBConnector()
        {
        }

        public static DBConnector Instance()
        {
            if (_instance == null)
            {
                _instance = new DBConnector();
            }

            return _instance;
        }

        private object[] AsyncCall(string[] callArgs, AsyncMethodCaller caller)
        {
            IAsyncResult result = caller.BeginInvoke(callArgs, null, null);

            return caller.EndInvoke(result);
        }

        public void Disconnect()
        {
            AsyncCall(null, Disconnect);
        }

        public int Connect(string dbName, int capacity)
        {
            return (int)AsyncCall(new string[] {dbName, capacity.ToString()}, Connect)[0];
        }

        public DataTable Request(string[] args)
        {
            return (DataTable) AsyncCall(args, RequestSender)[1];
        }

        private object[] RequestSender(string[] args)
        {
            DataTable table = DBFacade.Instance().Request(args);
            return new object[]{0, table};
        }

        public void CreateDB(string dbName, int cardsCount)
        {
            AsyncCall(new string[] { dbName, cardsCount.ToString() }, CallDBGenarator);
        }

        private object[] Disconnect(string[] args)
        {
            DBFacade.Instance().Disconnect();
            return null;
        }

        private object[] Connect(string[] args)
        {
            string dbPath = args[0];
            int capacity = Convert.ToInt32(args[1]);
            int result = DBFacade.Instance().Connect(dbPath, capacity);
            if (result == 0)
            {
                MessageBox.Show("Connection establih", "Done", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else
            {
                MessageBox.Show("Connetion failed", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            return new object[] {result};
        }

        private object[] CallDBGenarator(string[] args)
        {
            string dbName = args[0];
            int cardsCount = Convert.ToInt32(args[1]);
            DBFacade.Instance().Generate(dbName,cardsCount);

            MessageBox.Show("DB creation complete", "Done", MessageBoxButtons.OK, MessageBoxIcon.Information);

            return null;
        }
    }
}
