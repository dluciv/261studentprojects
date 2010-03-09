//Lebedev Dmitry g261 2009 (c)
package fatherandsons;

public class Botan extends Student {

    @Override
    public void setMarks(int[] marks) {
        super.setMarks(marks);
    }

    //Generating radnom mark from 3 to 5
    public static int mark() {
        int mark = 0;
        while (mark < 3) {
            mark = Generator.rnd.nextInt(6);
        }
        return mark;
    }

    public static int[] generateMarks() {
        int[] marks = {mark(), mark(), mark(), mark(), mark()};
        return marks;
    }

    Botan() {
        super();
        this.setMarks(generateMarks());
    }

    public static void printMarks(Botan botan) {
        for (int mark : botan.getMarks()) {
            System.out.println(mark);
        }
    }
}

