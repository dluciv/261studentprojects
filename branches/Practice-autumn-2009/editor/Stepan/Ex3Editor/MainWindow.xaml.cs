using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Ex3Editor.Model;

namespace Ex3Editor
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        void LoadStateFromModel()
        {
            this.MainTextBox.Text = model.Text;
            this.MainTextBox.CaretIndex = model.State.CursorPos;
            this.MainTextBox.ScrollToHorizontalOffset(model.State.HorizontalScroll);
            this.MainTextBox.ScrollToVerticalOffset(model.State.VerticalScroll);

            if (model.State.WindowWidth > 0)
                this.Width = model.State.WindowWidth;
            if (model.State.WindowHeight > 0)
                this.Height = model.State.WindowHeight;

            if (model.State.WindowX > 0)
                this.Top = model.State.WindowX;
            if (model.State.WindowY > 0)
                this.Left = model.State.WindowY;
        }
        void SaveStateToModel()
        {
            model.Text = MainTextBox.Text;
            model.State.CursorPos = MainTextBox.CaretIndex;
            model.State.HorizontalScroll = MainTextBox.HorizontalOffset;
            model.State.VerticalScroll = MainTextBox.VerticalOffset;
            model.State.WindowWidth = this.Width;
            model.State.WindowHeight = this.Height;
            model.State.WindowX = this.Top;
            model.State.WindowY = this.Left;
        }

        EditorEngine model = null;

        #region Loading states from model methods

        private void Window_Initialized(object sender, EventArgs e)
        {
            model = EditorEngine.LoadState();
            LoadStateFromModel();
            this.MainTextBox.Focus();
        }

        private void Open_Click(object sender, RoutedEventArgs e)
        {
            Microsoft.Win32.OpenFileDialog dialog = new Microsoft.Win32.OpenFileDialog();
            dialog.Filter = "Text files|*.txt";
            bool? res = dialog.ShowDialog();
            if (res.Value == true)
            {
                model = EditorEngine.LoadFile(dialog.FileName);
                model.SaveState();
                LoadStateFromModel();
            }
            this.MainTextBox.Focus();
        }
        private void New_Click(object sender, RoutedEventArgs e)
        {
            model = new EditorEngine();
            model.SaveState();
            LoadStateFromModel();
        }
        #endregion

        #region Saving states to model methods

        private void Window_Closed(object sender, EventArgs e)
        {
            SaveStateToModel();
            model.SaveState();
        }

        private void Save_Click(object sender, RoutedEventArgs e)
        {
            if (model.State.IsNew)
            {
                Microsoft.Win32.SaveFileDialog dialog = new Microsoft.Win32.SaveFileDialog();
                dialog.Filter = "Text files|*.txt";
                dialog.FileName = model.State.FileName;

                bool? res = dialog.ShowDialog();
                if (res.Value == true)
                {
                    model.State.FileName = dialog.FileName;
                }
                else
                    return;
            }

            SaveStateToModel();
            model.SaveFile();
            model.SaveState();
            this.MainTextBox.Focus();
        }
        #endregion


        private void Exit_Click(object sender, RoutedEventArgs e)
        {
            App.Current.Shutdown();
        }
    }
}
