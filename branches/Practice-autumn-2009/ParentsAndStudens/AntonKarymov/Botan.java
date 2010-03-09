/*Anton Karymov,2009,261gr.
  Botan differ off Student own marks(Botan can have 3,4,5 - random)
 */
package generic;

public class Botan extends Student {
    private int minimalBotanMarks = 3;
    public Botan(String name, String fathername, String surname, Sex sex, int age, String faculty) {
        super(name, fathername, surname, sex, age, faculty);
        
        for (int i = 0; i < marks.length; i++) {
           marks[i] = minimalBotanMarks + MyRandom.getRandom().nextInt(minimalBotanMarks);
        }
    }
}


