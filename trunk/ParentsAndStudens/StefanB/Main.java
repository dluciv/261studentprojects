package fathers_sons;

import java.util.List;

public class Main {
	
    public static void main(String[] args) {
    	
        List<IHuman> list = RandomListGenerator.generateCollection();

        CollectionFunctions.printHumanList(list);
        System.out.println(CollectionFunctions.getAverageGradeInCollection(list));
        System.out.println(CollectionFunctions.getMoneyInCollection(list));
    }
}