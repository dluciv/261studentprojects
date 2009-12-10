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
        EditorEngine model = null;

        public MainWindow()
        {
            InitializeComponent();
        }
        private void New_Click(object sender, RoutedEventArgs e)
        {
            model = new EditorEngine();
            this.MainTextBox.Text = model.Text;
            this.MainTextBox.CaretIndex = model.State.CursorPos;
            this.MainTextBox.ScrollToHorizontalOffset(model.State.HorizontalScroll);
            this.MainTextBox.ScrollToVerticalOffset(model.State.VerticalScroll);
        }
        private void Save_Click(object sender, RoutedEventArgs e)
        {
            if (model.IsNew)
            {
                Microsoft.Win32.SaveFileDialog dialog = new Microsoft.Win32.SaveFileDialog();
                dialog.FileName = model.State.FileName;

                bool? res = dialog.ShowDialog();
                if (res.Value == true)
                {
                    model.State.FileName = dialog.FileName;
                }
                else
                    return;
            }

            model.Text = MainTextBox.Text;
            model.State.CursorPos = MainTextBox.CaretIndex;
            model.State.HorizontalScroll = MainTextBox.HorizontalOffset;
            model.State.VerticalScroll = MainTextBox.VerticalOffset;
            model.SaveFile();
            model.SaveState();
            this.MainTextBox.Focus();
        }

        private void Open_Click(object sender, RoutedEventArgs e)
        {
            Microsoft.Win32.OpenFileDialog dialog = new Microsoft.Win32.OpenFileDialog();
            bool? res = dialog.ShowDialog();
            if (res.Value == true)
            {
                model = EditorEngine.LoadFile(dialog.FileName);
                this.MainTextBox.Text = model.Text;
            }
            this.MainTextBox.Focus();
        }

        private void Exit_Click(object sender, RoutedEventArgs e)
        {
            if (!model.IsModified)
            {
                model.State.CursorPos = MainTextBox.CaretIndex;
                model.State.HorizontalScroll = MainTextBox.HorizontalOffset;
                model.State.VerticalScroll = MainTextBox.VerticalOffset;
                model.SaveState();
            }

            App.Current.Shutdown();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            model = EditorEngine.LoadState();
            this.MainTextBox.Text = model.Text;
            this.MainTextBox.CaretIndex = model.State.CursorPos;
            this.MainTextBox.ScrollToHorizontalOffset(model.State.HorizontalScroll);
            this.MainTextBox.ScrollToVerticalOffset(model.State.VerticalScroll);
            this.MainTextBox.Focus();
        }
    }
}
