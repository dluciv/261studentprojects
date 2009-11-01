/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotheart.parentsandstudens;

import java.util.List;

/**
 *
 * @author HotHeart
 */
public class Processor {

    public static int calcMoney(List<IHuman> humans) {
        int res = 0;
        for (IHuman i : humans) {
            if (i instanceof CoolParent) {
                res += ((CoolParent) i).getMoney();
            }
        }

        return res;
    }

    public static int calcMark(List<IHuman> humans) {
        int res = 0;
        int count = 0;
        for (IHuman i : humans) {
            if (i instanceof Botan) {
                Botan b = ((Botan) i);
                int excount = b.getExamsCount();

                count += excount;
                for (int j = 0; j < excount; j++) {
                    res += b.getExamMark(j);
                }
            }
        }

        return res / count;
    }
}
