/*Anton Karymov,2009,261gr.
  Form LinkedList of human
 */
package generic;
import java.util.LinkedList;

public class HumanList {
    private int minimumParentNumber = 1;
    private int maximumParentNumber = 5;
    private LinkedList<IHuman> humanList = new LinkedList<IHuman>();

    public LinkedList<IHuman> createHumanList() {
        int parentNumber = minimumParentNumber + MyRandom.getRandom().nextInt(maximumParentNumber);
        for (int i = 0; i < parentNumber; i++) {
            Parent parent = (Parent) HumanСreator.createParent();
            humanList.add(parent);
            for (int j = 0; j < parent.getKidsNumber(); j++) {
                Student student = (Student) HumanСreator.createStudent(parent.getName(),parent.getSurname());
                humanList.add(student);
            }
        }
        return humanList;
    }

//print Human linkedList on the screen
    public void printHuman(LinkedList<IHuman> humanList) {
        for (IHuman human : humanList) {
            if (human instanceof Parent) {
                Parent.printParent((Parent) human);
            } else {
                Student.printStudent((Student) human);
            }
        }
    }

//print on the screen CoolParent money
    public void printCoolParentMoney(double sumCoolParentMoney) {
        System.out.printf("%s %s %s", "На данный момент у крутых отцов:", sumCoolParentMoney, "у.е");
        System.out.println();
    }

//print on the screen Botan averege marks
    public void printBotanAveregeMarks(double BotanAveregeMarks) {
        System.out.printf("%s %s", "Средняя оценка по ботаникам:", BotanAveregeMarks);
        System.out.println();
    }

//count CoolParent money in the human linked list
    public double countCoolParentMoney() {
        double sumCoolParentMoney = 0;

        for (IHuman human : humanList) {
            if (human instanceof CoolParent) {
                sumCoolParentMoney += ((CoolParent) human).countAllMoney(((CoolParent) human).getKids());
            }
        }
        return sumCoolParentMoney;
    }

//count BotanAveregeMarks in the human linked list
    public double countAveregeBotanMarks() {
        double sumBotanMarks = 0.0;
        int count = 0;
        for (IHuman human : humanList) {
            if (human instanceof Botan) {
                count += 1;
                sumBotanMarks += ((Botan) human).getAverageMark();
            }
        }
        return sumBotanMarks / count;
    }
}
