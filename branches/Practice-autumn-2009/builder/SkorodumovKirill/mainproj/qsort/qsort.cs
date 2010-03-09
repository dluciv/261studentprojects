using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace qsort
{
    public class ArrayClass
    {
        int[] array;
        int numItems;

        public ArrayClass(int size)
        {
            array = new int[size];
            numItems = 0;
        }

        public void Add(int i)
        {
            array[numItems++] = i;
        }

        private void QuickSort(int beg, int end)
        {
            int p = array[beg];
            int i = beg;
            int j = end + 1;

            if (end == beg) return;

            while (i <= j)
            {
                do
                {
                    i++;
                } while (array[i] <= p);
                do
                {
                } while (array[j] > p);

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

            QuickSort(beg, j);
            QuickSort(i, end);
        }

        public void QSort()
        {
            QuickSort(0, numItems - 1);
        }

        public void Dump()
        {
            foreach (Object o in array)
                System.Console.Write(o.ToString() + " ");
            System.Console.Write("");
        }
    }
}
