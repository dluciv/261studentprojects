/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fathersandchildren;

import java.util.List;

public class CollectionCalcs {
    public static double calcAllMoney (List<IHuman> humanList) {
        double sum = 0;

        for (IHuman h : humanList) {
            if (h instanceof CoolParent) {
                sum += ((CoolParent) h).moneyQnt;
            }
        }

        return sum;
    }

    public static double calcAverageGradesOfAllBotans (List<IHuman> humanList) {
        double averageGradesSum = 0;
        int botansQnt = 0;

        for (IHuman h : humanList) {
            if (h instanceof Botan) {
                botansQnt++;
                averageGradesSum += ((Botan) h).averageGrades;
            }
        }

        if (botansQnt == 0) {
            return 0;
        }

        return averageGradesSum / botansQnt;
    }
}
