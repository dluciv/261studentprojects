using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

///
///Savenko Maria (c)2009
///

namespace TextRedactorBySM {
    public interface IControlState {
        void LoadState();
    }

    public interface IWidget {
        void LoadState();
    }

    [Serializable]
    public class FormState: IControlState {
        private Rectangle bounds;
        private Size size;

        public void LoadState() {
            Form.ActiveForm.Size = size;
            Form.ActiveForm.Bounds = bounds;
        }

        public FormState(Form frm) {
            size = frm.Size;
            bounds = frm.Bounds;
        }
    }

    [Serializable]
    public class RichTextFState: IControlState {
        private string text;
        private int currPosition;
        private string name;

        public void LoadState() {
            ((RichTextBox)TextRedactorBySM.MainForm.ActiveForm.Controls.Find(name, false)[0]).Text = text;
            ((RichTextBox)TextRedactorBySM.MainForm.ActiveForm.Controls.Find(name,false)[0]).SelectionStart = currPosition;
            //form.richTextBox.Text = text;
            //form.richTextBox.SelectionStart = currPosition;
        }

        public RichTextFState(RichTextBox rtb) {
            text = rtb.Text;
            currPosition = rtb.SelectionStart;
            name = rtb.Name;
        }
    }

    public class Widget: IWidget {
        public Widget(IControlState[] state) {
            this.state = state;
        }

        private IControlState[] state;

        public void LoadState() {
            foreach (IControlState i in state)
                i.LoadState();
        }

        public IControlState[] State {
            get { return state; }
            set { state = value; }
        }
    }
}
