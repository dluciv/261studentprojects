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

        private Filling textBoxFilling;
        private static BinaryFormatter binFormat = new BinaryFormatter();

        private void AboutProg_Click(object sender, EventArgs e)
        {
            About abt = new About();
            abt.ShowDialog();
        }

        private void SaveAs_Click(object sender, EventArgs e)
        {
            textBoxFilling = new Filling();
            textBoxFilling.Text = richTextBox.Text;
            textBoxFilling.CursorPosition = richTextBox.SelectionStart;

            if (saveFileDialog.ShowDialog() != DialogResult.OK)
                return;
            SaveToFile(saveFileDialog.FileName + ".sav");
        }

        private void SaveToFile(string FileName)
        {
            FileStream fs = new FileStream(FileName, FileMode.Create, FileAccess.Write, FileShare.None);
            binFormat.Serialize(fs, textBoxFilling);
            fs.Close();
        }

        private void OpenAs_Click(object sender, EventArgs e)
        {
            if (openFileDialog.ShowDialog() != DialogResult.OK)
                return;
            FileStream fs = File.OpenRead(openFileDialog.FileName);
            Filling openedFilling = (Filling)binFormat.Deserialize(fs);

            richTextBox.Text = openedFilling.Text;
            richTextBox.SelectionStart = openedFilling.CursorPosition;

            fs.Close();
        }
    }

    [Serializable]
    public class Filling
    {
        private String text;
        private int currPosition;

        public string Text
        {
            get { return this.text; }
            set { this.text = value; }
        }

        public int CursorPosition
        {
            get { return this.currPosition; }
            set { this.currPosition = value; }
        }
    }

}