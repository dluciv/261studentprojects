//by Skorodumov Kirill gr.: 261
//parentsandstudents task

package parentsandstudents;

import java.util.LinkedList;

public class Calculator {
	public static Integer calcMoney(LinkedList<IHuman> humans)
	{
		Integer sum = 0;
		for (IHuman human : humans)
		{
			if (human instanceof CoolParent)
			{
				sum += ((CoolParent) human).getMoney();
			}
		}
		
		return sum;
	}
	
	public static Integer calcMark(LinkedList<IHuman> humans)
	{
        Integer res = 0;
        Integer count = 0;
        for (IHuman human : humans) {
            if (human instanceof Botan) {
                Botan b = ((Botan) human);
                Integer exNum = b.getExamNum();

                count += exNum;
                for (int j = 0; j < exNum; j++) {
                    res += b.getMark(j);
                }
            }
        }

        return res / count;
	}
}
