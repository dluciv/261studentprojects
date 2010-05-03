using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.IO;
using System.Windows.Forms;
using System.Drawing.Drawing2D;
using System.Xml.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

///
///Savenko Maria (c)2009
///

namespace TextRedactorBySM
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private static BinaryFormatter binFormat = new BinaryFormatter();

        private void AboutProg_Click(object sender, EventArgs e)
        {
            About abt = new About();
            abt.ShowDialog();
        }

        private void SaveAs_Click(object sender, EventArgs e)
        {
            if (saveFileDialog.ShowDialog() != DialogResult.OK)
                return;
            SaveToFile(saveFileDialog.FileName + ".sav");
        }

        private void SaveToFile(string FileName)
        {
            IControlState[] state_list = new IControlState[2];

            state_list[0] = new FormState(this);
            state_list[1] = new RichTextFState(richTextBox);

            FileStream fs = new FileStream(FileName, FileMode.Create, FileAccess.Write, FileShare.None);
            binFormat.Serialize(fs, state_list);
            fs.Close();
        }

        private void OpenAs_Click(object sender, EventArgs e)
        {
            if (openFileDialog.ShowDialog() != DialogResult.OK)
                return;
            FileStream fs = File.OpenRead(openFileDialog.FileName);
            IWidget widget = new Widget((IControlState[])binFormat.Deserialize(fs));

            widget.LoadState();

            fs.Close();
        }
    }

}