using System;
using System.IO;

namespace DB3
{
    class Logger: IDisposable
    {
        private static Logger _instance;

        private const string FileName = "DBLog.txt";

        private BinaryWriter writer;

        private delegate void AsyncWrite(string arg);

        private Logger()
        {
            if (File.Exists(FileName))
            {
                writer = new BinaryWriter(File.Open(FileName, FileMode.Append));
            }
            else
            {
                writer = new BinaryWriter(File.Open(FileName,FileMode.OpenOrCreate));
            }
        }

        public static Logger Instance()
        {
            if (_instance == null)
            {
                _instance = new Logger();
            }

            return _instance;
        }

        private string Now()
        {
            return DateTime.Now.ToString();
        }

        public void Write(string arg)
        {
            AsyncWrite logger = new AsyncWrite(WriteLog);

            IAsyncResult result = logger.BeginInvoke(arg, null, null);

            logger.EndInvoke(result);
        }

        private void WriteLog(string arg)
        {
            writer.Write(Now() + ": " + arg + Environment.NewLine);
        }

        public void Dispose()
        {
            writer.Close();
        }
    }
}
