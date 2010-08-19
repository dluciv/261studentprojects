/*
 * (c) Stefan Bojarovski 2010
 * */
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace ASTBuilder
{
    class MainClass
    {
        public static void Main()
        {
            FileStream fout, fin;
            fout = new FileStream("output.txt", FileMode.Truncate);
            fin = new FileStream("input.txt", FileMode.Open);
            StreamWriter fstr_out = new StreamWriter(fout);
            StreamReader fstr_in = new StreamReader(fin);

            Interpreter someInterpreter = new Interpreter("", fstr_out);
            String s = fstr_in.ReadLine();

            while (s != null)
            {
                someInterpreter.appendInputString(s);
                s = fstr_in.ReadLine();
            }

            someInterpreter.getAllTokens();
            //someInterpreter.printTokenList();

            someInterpreter.getLookAhead();
            ParseTree tree = new ParseTree(null, null);
            try
            {
                 tree = someInterpreter.getParseTree();
                 //tree.printTree(tree.getRoot(), fstr_out);
            }
            catch (DivideByZeroException)
            {
                fstr_out.WriteLine("Program terminated");
            }

            someInterpreter.runParseTree(tree.getRoot());

            
            fstr_out.Close();
            fstr_in.Close();
        }
    }
}
