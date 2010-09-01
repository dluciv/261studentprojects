using System;
using System.Windows.Forms;
using Core;

namespace SimpleDCVS
{
    public partial class CommitGUI : Form
    {
        private readonly Cvs _cvs = new Cvs();
        private readonly SimpleDcvs _parent;

        public CommitGUI(SimpleDcvs parent)
        {
            InitializeComponent();
            _cvs.OpenLast();
            _parent = parent;
            textBox2.Text = _cvs.GetCurrentSnaphot().Details();
            Show();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            _cvs.Commit(textBox1.Text);
            _parent.Reload();
            Close();
        }
    }
}
