/*
 * prints money amount and general mean mark
 * Savenko Maria (c)2009
 */
package msavenko.parentsandstudens;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<IHuman> list = Generator.generateCollection();

        System.out.println("���������� ������:");

        for (IHuman i : list) {
            System.out.println(i.toString());
        }

        System.out.print("����������� ����� � �����:");
        System.out.println(Generator.calcMoney(list));

        System.out.print("������� ���� �������:");
        System.out.println(Generator.calcMark(list));

    }
}
