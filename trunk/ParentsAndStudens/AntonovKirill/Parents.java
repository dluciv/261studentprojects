//(c) Antonov Kirill 2009
// Parents

package parentsstudents;

public class Parents /*extends humanSpecifications*/ {
    public Student[] students;

    Parents(String surname, String name, String FatherName, Sex sex, int age,
            Student[] students){
        //super(surname, name, FatherName, sex, age);
        this.students = students;
    }

    /*
    //окончание фамилий генерируется в зависимости от пола
    String surnameEnding(Sex sex){
        if(sex == Sex.female){
            return "ович";
        } else{
            return "овна";
        }
    }

    //окончание имени
    String nameEnding(Sex sex){
        if(sex == Sex.male){
            return "";
        }else{
            return "а";
        }
    }
     */
}
