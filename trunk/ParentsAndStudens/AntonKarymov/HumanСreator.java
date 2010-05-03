/*Anton Karymov,2009,261gr.
  create Students,Botans,Parents and CoolParents
 */
package generic;

public class HumanСreator {
    public static IHuman createStudent(String fatherName,String fatherSurname) {
        Sex sex = GeneratorHumanInfo.generateSex();
        String name = GeneratorHumanInfo.generateName(sex);
        String fathername = GeneratorHumanInfo.generatePatronymic(sex, fatherName);
        String surname = GeneratorHumanInfo.generateSurname(sex,fatherSurname);
        int age = GeneratorHumanInfo.generateStudentAge();
        String faculty = GeneratorHumanInfo.generateFaculty();

        if (MyRandom.getRandom().nextBoolean()) {
            return new Student(name, fathername, surname, sex, age, faculty);
        } else {
            return new Botan(name, fathername, surname, sex, age, faculty);
        }
    }

    public static IHuman createParent() {
        String name = GeneratorHumanInfo.generateName(Sex.male);
        String fathername = GeneratorHumanInfo.generatePatronymic(Sex.male, GeneratorHumanInfo.generateName(Sex.male));
        String surname = GeneratorHumanInfo.generateFatherSurname();
        Sex sex = Sex.male;
        int age = GeneratorHumanInfo.generateParentAge();
        int kidsNumber = GeneratorHumanInfo.generateKidsNumber();
        Student[] kids = new Student[kidsNumber];

        for (int i = 0; i < kids.length; i++) {
            kids[i] =  (Student) createStudent(name,fathername);
        }

        if (MyRandom.getRandom().nextBoolean()) {
            return new Parent(name, fathername, surname, sex, age, kids);
        } else {
            return new CoolParent(name, fathername, surname, sex, age, kids);
        }
    }
}




