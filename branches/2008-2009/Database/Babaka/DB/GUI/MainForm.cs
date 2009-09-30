using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace GUI
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void selectToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Enabled = false;
            (new RequestForm(this)).Show(this);
            statusStrip1.Items[0].Text = "Send request...";
        }

        public void SetDataSource(object dataSource)
        {
            dataGridView1.DataSource = dataSource as DataTable;
            statusStrip1.Items[0].Text = "Done";
        }

        private void createTestDBToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (saveFileDialog1.ShowDialog(this) == DialogResult.OK)
            {
                statusStrip1.Items[0].Text = "Generation...";
                DBConnector.Instance().CreateDB(saveFileDialog1.FileName, 10000);
                statusStrip1.Items[0].Text = "Done";
            }
        }

        private void helpToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Enabled = false;
            (new AboutBox1(this)).Show(this);
        }

        private void loadDBToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                statusStrip1.Items[0].Text = "Connection...";
                int result = DBConnector.Instance().Connect(openFileDialog1.FileName, 5);
                if (result == 0)
                {
                    loadDBToolStripMenuItem.Enabled = false;
                    toolStripMenuItem2.Enabled = true;
                    selectToolStripMenuItem.Enabled = true;
                    statusStrip1.Items[0].Text = "Connect";
                }
                else
                {
                    statusStrip1.Items[0].Text = "Connectinon failed";
                }
            }
        }

        private void toolStripMenuItem2_Click(object sender, EventArgs e)
        {
            DBConnector.Instance().Disconnect();
            loadDBToolStripMenuItem.Enabled = true;
            toolStripMenuItem2.Enabled = false;
            selectToolStripMenuItem.Enabled = false;
            statusStrip1.Items[0].Text = "Not connect";
        }
    }
}
