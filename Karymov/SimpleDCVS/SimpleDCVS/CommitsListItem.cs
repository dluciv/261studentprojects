using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mime;
using System.Text;
using System.Windows.Forms;

namespace SimpleDCVS
{
    public class CommitsListItem : ListViewItem
    {
        public CommitsListItem(string text, string id)
        {
            Text = text;
            CommitId = id;
        }
        public string CommitId { get; set; }
    }
}
