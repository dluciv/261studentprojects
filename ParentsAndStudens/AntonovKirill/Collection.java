/*(c) Antonov Kirill 2009
 * 
 */

package parentsstudents;

import java.util.List;

public class Collection {
     public static double calcAllMoney (List<IHuman> humanList) {
         if (humanList == null) {
            throw new IllegalArgumentException("Null Pointer");
         }

         double sum = 0;

         for (IHuman human : humanList) {
             if (human instanceof CoolParent) {
                 sum += ((CoolParent) human).money;
            }
        }

        return sum;
    }

    public static double calcAverageGradesOfAllBotans (List<IHuman> humanList) {
        if (humanList == null) {
            throw new IllegalArgumentException("Null Pointer");
        }

        double averageGradesSum = 0;
        int botans_Counter = 0;

        for (IHuman human : humanList) {
            if (human instanceof Botan) {
                botans_Counter++;
                averageGradesSum += ((Botan) human).averageGrades;
            }
        }

        if (botans_Counter == 0) {
            return 0;
        }

        return averageGradesSum / botans_Counter;
    }

}
