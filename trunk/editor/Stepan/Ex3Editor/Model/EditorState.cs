using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ex3Editor.Model
{
    [Serializable]
    public class EditorState
    {
        public String FileName { get; set; }
        public DateTime FileChangeTime { get; set; }
        public int CursorPos { get; set; }
        public Double VerticalScroll { get; set; }
        public Double HorizontalScroll { get; set; }
        public Boolean IsNew { get; set; }
    }
}
