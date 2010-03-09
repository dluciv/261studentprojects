using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace Ex3Editor.Model
{
    class EditorEngine
    {
        public EditorState State { get; set; }
        public bool IsModified { private set; get; }

        public String _text;
        public String Text 
        {
            get
            {
                return _text; 
            }
            set
            {
                IsModified = false;
                _text = value;
            }
        }
        
        public static EditorEngine LoadState()
        {
            EditorEngine res = null;
            try
            {
                var state = Properties.Settings.Default.LastState;
                if (state.FileChangeTime != new FileInfo(state.FileName).LastWriteTime)
                    res = new EditorEngine(state.FileName);
                else
                    res = new EditorEngine(state);
            }
            catch
            {
                res = new EditorEngine();
                res.State.WindowHeight = Properties.Settings.Default.LastState.WindowHeight;
                res.State.WindowWidth = Properties.Settings.Default.LastState.WindowWidth;
                res.State.WindowX = Properties.Settings.Default.LastState.WindowX;
                res.State.WindowY = Properties.Settings.Default.LastState.WindowY;
            }
            return res;
        }

        public static EditorEngine LoadFile(String fileName)
        {
            EditorEngine res = new EditorEngine(fileName);
            return res;
        }

        public EditorEngine()
        {
            State = new EditorState() { CursorPos = 0, FileChangeTime = DateTime.Now, FileName = "NewFile.txt" };
            Text = "";
            State.IsNew = true;
            IsModified = false;
        }

        private EditorEngine(EditorState state)
        {
            State = new EditorState();
            State = state;
            Text = File.ReadAllText(state.FileName);
            State.IsNew = false;
            IsModified = false;
        }

        private EditorEngine(String fileName)
        {
            State = new EditorState();
            State.FileName = fileName;
            State.CursorPos = 0;
            State.FileChangeTime = new FileInfo(State.FileName).LastWriteTime;
            Text = File.ReadAllText(fileName);
            State.IsNew = false;
            IsModified = false;
        }

        public void SaveFile()
        {
            File.WriteAllText(State.FileName, Text);
            State.FileChangeTime = new FileInfo(State.FileName).LastWriteTime;
            State.IsNew = false;
            IsModified = false;
            SaveState();
        }

        public void SaveState()
        {
            Properties.Settings.Default.LastState = State;
            Properties.Settings.Default.Save();
        }
    }
}