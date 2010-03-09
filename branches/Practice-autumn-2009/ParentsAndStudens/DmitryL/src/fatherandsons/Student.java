//Lebedev Dmitry g261 2009 (c)
package fatherandsons;

public class Student implements IHuman {

    private Parent father;
    private String name;
    private String surname;
    private String patronymic;
    private Sex sex;
    private int age;
    private String faculty;
    private int[] marks = {3, 3, 3, 3, 3};

    Student() {
        this.setSex();
        this.setName(this.sex);
        this.setAge(Generator.ageCategory1[(int) (5 * Math.random())]);
        this.setFaculty();
        this.setMarks(marks);
    }

    public static String patronymicEnd(Sex sex) {
        if (sex == Sex.male) {
            return "ович";
        } else {
            return "овна";
        }
    }

    public static String surnameEnd(Sex sex) {
        if (sex == Sex.male) {
            return "";
        } else {
            return "а";
        }
    }

    public void setFather(Parent father) {
        this.father = father;
        this.patronymic = father.getName() + patronymicEnd(sex);
        this.surname = father.getSurname() + surnameEnd(sex);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(Sex sex) {
        if (sex == Sex.male) {
            this.setName(Generator.maleNames[(int) (5 * Math.random())]);
        } else {
            this.setName(Generator.femaleNames[(int) (5 * Math.random())]);
        }
    }

    public void setPatronymic(String patronymic) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSurname(String surname) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setSex() {
        this.sex = Sex.values()[(int) (Math.random() * 2)];
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setFaculty() {
        this.faculty = Generator.faculty[(int) (5 * Math.random())];
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex.toString();
    }

    public String getFaculty() {
        return faculty;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getAge() {
        return age;
    }

    public double averageMark() {
        double result = 0;
        for (int mark : marks) {
            result += mark;
        }
        return result / marks.length;
    }
//printing paremetres of student
    public void printAll() {
        System.out.printf("     %s %s %s %s %s", this.name, this.patronymic,
                this.surname, this.sex, this.age);
        System.out.println();
    }
}
   