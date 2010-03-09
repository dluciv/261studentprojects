/* @author Eugene Todoruk
 * group 261
 */

package parentsAndChildren;

import java.util.Arrays;

public class Student extends Human {

    protected static final int MARKS_COUNT = 5;
    private static final int MARK = 3;
    private String faculty;
    protected int[] marks = new int[MARKS_COUNT];

    public Student(String firstName, String partonymic, String lastName,
            Sex sex, int age, String faculty) {
        super(firstName, partonymic, lastName, sex, age);

        setFaculty(faculty);

        Arrays.fill(marks, MARK);
    }

    public double getMeanRating() {
        double rating = 0;

        for (int mark : marks) {
            rating += mark;
        }

        return rating / MARKS_COUNT;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());

        stringBuilder.append("\nСтатус: Студент");
        stringBuilder.append("\nФакультет: ").append(faculty);
        stringBuilder.append("\nОценки:");
        for (int mark : marks) {
            stringBuilder.append(" ").append(mark);
        }

        return stringBuilder.toString();
    }
}