using System;
using System.Data;
using System.Windows.Forms;

namespace GUI
{
    public partial class RequestForm : Form
    {
        private Form _parent;

        private delegate void Enabler();
        private delegate void ResultReturner(object result);

        public RequestForm(Form parent)
        {
            InitializeComponent();
            _parent = parent;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Invoke(new Enabler(ParentEnabler));
            this.Close();
        }

        private void ParentEnabler()
        {
            _parent.Enabled = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Visible = false;
            string[] args = new string[]{textBox1.Text, textBox2.Text, textBox3.Text, textBox4.Text,textBox5.Text,textBox6.Text};
            DataTable table = DBConnector.Instance().Request(args);
            _parent.Invoke(new ResultReturner(((MainForm) _parent).SetDataSource),table);
            button2_Click(null,null);
        }
    }
}
