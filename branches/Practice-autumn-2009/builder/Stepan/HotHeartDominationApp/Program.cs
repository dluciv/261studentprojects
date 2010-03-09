using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace HotHeartDominationApp
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            ExpansionLibrary.ExpansionAlgorithm.StartExpansion(() => { return new DominationForm(); });
        }
    }
}
