using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Core;

namespace SimpleDCVS
{
    public partial class CloneGUI : Form
    {
        private SimpleDcvs _parent;
        public CloneGUI(SimpleDcvs parent)
        {
            _parent = parent;
            InitializeComponent();
            Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                textBox1.Text = folderBrowserDialog1.SelectedPath;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                textBox2.Text = folderBrowserDialog1.SelectedPath;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var cvs = new Cvs();
            cvs.Clone(textBox1.Text,textBox2.Text);
            Close();
        }
    }
}
