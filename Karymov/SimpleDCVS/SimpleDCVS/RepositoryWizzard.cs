using System;
using System.IO;
using System.Windows.Forms;
using Core;

namespace SimpleDCVS
{
    public enum WizzardMode : byte
    {
        Open,
        Create
    }

    public partial class RepositoryWizzard : Form
    {
        private readonly WizzardMode _mode;
        private readonly SimpleDcvs _parent;

        public RepositoryWizzard(SimpleDcvs parent, WizzardMode mode)
        {
            _parent = parent;
            _mode = mode;
            InitializeComponent();
            Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                textBox1.Text = folderBrowserDialog1.SelectedPath;
            }
        }

        private void NewRepositoryWizzard_Load(object sender, EventArgs e)
        {
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Repository r = null;
            if (Directory.Exists(textBox1.Text))
                switch (_mode)
                {
                    case WizzardMode.Open:
                        {
                            r = RepositoryManager.OpenRepository(new DirectoryInfo(textBox1.Text));
                        }
                        break;
                    case WizzardMode.Create:
                        {
                            r = RepositoryManager.CreateNewRepository(new DirectoryInfo(textBox1.Text));
                        }
                        break;
                }
            if (r != null)
            {
                _parent.Reload();
                Close();
            }
            else
                MessageBox.Show(@"Error");
        }
    }
}