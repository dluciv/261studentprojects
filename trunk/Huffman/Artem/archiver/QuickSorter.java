/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;

import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class QuickSorter {
	public ArrayList<ItemWeight> m;

	public QuickSorter(ArrayList<ItemWeight> iws){
		m = iws;
	}
	public void setR(ArrayList<ItemWeight> iws){
		m = iws;
	}
	private int partition (ArrayList<ItemWeight> iws, int a, int b)
	{
	  int i = a;
	  for (int j = a; j <= b; j++)		// просматриваем с a по b
	   {

		 if (iws.get(j).oldWeight <= iws.get(b).oldWeight)               // если элемент m[j] не превосходит m[b],
		  {
			ItemWeight t = iws.get(i);                    // меняем местами m[j] и m[a], m[a+1], m[a+2] и так далее...
			iws.set(i, iws.get(j));
			iws.set(j, t);
			i++;                         // таким образом последний обмен: m[b] и m[i], после чего i++
		  }
	   }
	  return i-1;                        // в индексе i хранится <новая позиция элемента m[b]> + 1
	}

	private void quicksort (ArrayList<ItemWeight> iws, int a, int b) // a - начало подмножества, b - конец
	{                                     // для первого вызова: a = 0, b = <элементов в массиве> - 1
		 if (a >= b) return;
		 int c = partition (iws, a, b);
		 quicksort (iws, a, c-1);
		 quicksort (iws, c+1, b);
	}
	public ArrayList<ItemWeight> sort (){
		quicksort(m, 0, m.size()-1);
		return m;
	}

}
