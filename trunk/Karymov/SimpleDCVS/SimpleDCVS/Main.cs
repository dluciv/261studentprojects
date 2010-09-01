using System;
using System.Windows.Forms;
using Core;

namespace SimpleDCVS
{
    
    public partial class SimpleDcvs : Form
    {
        private readonly Cvs _cvs = new Cvs();
        private bool _allowCheckout = true;

        public SimpleDcvs()
        {
            InitializeComponent();
            Reload();
     }

        private void button2_Click(object sender, EventArgs e)
        {
            new RepositoryWizzard(this, WizzardMode.Open);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new RepositoryWizzard(this, WizzardMode.Create);
        }

        public void Reload()
        {
            _allowCheckout = false;
            _cvs.OpenLast();
            if (_cvs.Opened())
            {
                comboBox1.Items.Clear();
                comboBox2.Items.Clear();
                foreach (var b in _cvs.GetBranches())
                {

                    if (b.Name == _cvs.GetCurrentBranch().Name)
                    {
                        comboBox1.Items.Add(b.Name);
                        comboBox1.SelectedItem = comboBox1.Items[comboBox1.Items.Count - 1];
                    }
                    else
                        comboBox1.Items.Add(b.Name);
                }

                foreach (var b in _cvs.GetRemoteBranches())
                {

                    if (b.Name == _cvs.GetCurrentBranch().Name)
                    {
                        comboBox2.Items.Add(b.Name);
                        comboBox2.SelectedItem = comboBox2.Items[comboBox2.Items.Count - 1];
                    }
                    else
                        comboBox2.Items.Add(b.Name);
                }
   
                
                listView1.Items.Clear();
                listView1.Items.Add(new ListViewItem(_cvs.GetCurrentSnaphot().ToString()));
                foreach (var c in _cvs.GetCommits())
                {
                    listView1.Items.Add(
                        new CommitsListItem((String.IsNullOrEmpty(c.Message) ? c.Date + "  -  " + c.Id : c.Message + " - " + c.Date), c.Id));
                }

            }
            _allowCheckout = true;
        }

        private void listView1_Click(object sender, EventArgs e)
        {
            listView1.Items[0].Text =  _cvs.GetCurrentSnaphot().ToString();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            new CommitGUI(this);
        }

        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (listView1.SelectedItems.Count > 0)
            {
                CommitInfo c;

                if (listView1.SelectedItems[0] is CommitsListItem)
                {
                    var id = ((CommitsListItem) listView1.SelectedItems[0]).CommitId;
                    c = _cvs.GetCommitInfo(id);
                    textBox1.Text = c.Id;
                    textBox2.Text = c.Date.ToString();
                    textBox3.Text = (new SnapshotInfo(c)).Details();
                    textBox4.Text = c.Message;
                }
                else
                {
                    c = new CommitInfo(_cvs.GetCurrentSnaphot(),"");
                    textBox3.Text = (new SnapshotInfo(c)).Details();
                }
               
            }
            else
            {
                textBox1.Text = "";
                textBox2.Text = "";
                textBox3.Text = "";
                textBox4.Text = "";
            }
        }

        private void button7_Click(object sender, EventArgs e)
        {
            new BranchWizzard(this);
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (_allowCheckout == true)
            {
                var previosBranch = _cvs.GetCurrentBranch().Name;
                var branchName = comboBox1.SelectedItem.ToString();
                if (_cvs.Checkout(comboBox1.SelectedItem.ToString()))
                    MessageBox.Show("Checkout "+previosBranch+" -> " + branchName);
                else
                {
                    MessageBox.Show("Error");
                }

                Reload();

            }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            new CloneGUI(this);
        }
    }
}