/*Anton Karymov,2009,261gr.
  CoolParent implements interface for all people
  CoolParent differ off Parent that they have money
  We can count CoolParent's money
 */
package generic;

public class CoolParent extends Parent {
    CoolParent(String name, String fathername, String surname, Sex sex, int age, Student[] kids) {
        super(name, fathername, surname, sex, age, kids);
    }

//count money for all CoolParent's kid's
    public double countAllMoney(Student[] kids) {
        double sumMoney = 0.0;
        for (int i = 0; i < kids.length; i++) {
            sumMoney += countMoney(kids[i]);
        }
        return sumMoney;
    }

//CoolParents have his kid*kid's averege mark money
    public double countMoney(Student student) {
        int indexConverter = 10;
        return indexConverter * student.getAverageMark();
    }
}
