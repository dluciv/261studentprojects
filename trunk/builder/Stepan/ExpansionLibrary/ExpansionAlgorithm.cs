using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace ExpansionLibrary
{
    public class ExpansionAlgorithm
    {
        public static void StartExpansion( Func<Form> weaponCreator)
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            Random rnd = new Random();

            Form weapon = weaponCreator();
            int posX_max = Screen.PrimaryScreen.WorkingArea.Width - weapon.Width;
            int posY_max = Screen.PrimaryScreen.WorkingArea.Height - weapon.Height;
            weapon.Location = new Point(rnd.Next(posX_max), rnd.Next(posY_max));

            weapon.StartPosition = FormStartPosition.Manual;

            for (int i = 0; i < 1000; i++)
            {
                Form frm = weaponCreator();
                posX_max = Screen.PrimaryScreen.WorkingArea.Width - frm.Width;
                posY_max = Screen.PrimaryScreen.WorkingArea.Height - frm.Height;
                frm.Location = new Point(rnd.Next(posX_max), rnd.Next(posY_max));
                frm.StartPosition = FormStartPosition.Manual;
                frm.Show();
            }

            Application.Run(weapon);
        }
    }
}
