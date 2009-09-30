namespace DB3
{
    class Helper
    {
        private static Helper _instance;
        /// <summary>
        /// DB file extention
        /// </summary>
        private const string dbExt = "btdb"; 
        /// <summary>
        /// Index(tree) extention
        /// </summary>
        private const string trExt = "trin";
 
        private Helper()
        {
        }
 
        public static Helper Instance()
        {
            if (_instance == null)
            {
                _instance = new Helper();
            }
         
            return _instance;
        }

        public int MaxElement(string[] stringArray) 
        {
            int max = 0;
            foreach (string s in stringArray)
            {
                if (max < s.Length) 
                {
                    max = s.Length;
                }
            }
            return max;
        }

        public string CreateIndexName(string str)
        {
            string fld = str.Substring(0, str.LastIndexOf('\\') + 1);
            string file = str.Substring(fld.Length, str.Length - fld.Length - 4);
            return fld + file + trExt;
        }

        public string RestoreDBPath(string str)
        {
            string fld = str.Substring(0, str.LastIndexOf('\\') + 1);
            string file = str.Substring(fld.Length, str.Length - fld.Length - 4);
            return fld + file + dbExt;
        }
    }
}
