package generic;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<IHuman> ourHumanList = HumanList.createHumanList();
        HumanList.printHuman(ourHumanList);
        HumanList.printCoolParentMoney(HumanList.countParentMoney());
        HumanList.printBotanAveregeMarks(HumanList.countAveregeBotanMarks());
        

    }
}
