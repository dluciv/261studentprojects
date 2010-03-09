/*Anton Karymov,2009,261gr.
  Main
 */
package generic;

public class Main {
    public static void main(String[] args) {
        HumanList list = new HumanList();
        list.createHumanList();
        list.printHuman(list.createHumanList());
        list.printCoolParentMoney(list.countCoolParentMoney());
        list.printBotanAveregeMarks(list.countAveregeBotanMarks());
    }
}
