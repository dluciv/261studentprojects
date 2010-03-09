//Lebedev Dmitry g261 2009 (c)
package fatherandsons;

public class CoolParent extends Parent {

    CoolParent() {
        super();
    }

    @Override
    public void printAll() {
        super.printAll();
        System.out.printf("%s %s %s", "Состояние :", getMoney(), "рублей.");
        System.out.println();
    }

    public int getMoney() {
        int result = 0;
        for (Student kid : getKids()) {
            result += 10000 * kid.averageMark();
        }
        return result;
    }
}
