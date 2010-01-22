package fathers_sons;

public class CoolParent extends Parent {
	
	public CoolParent(String name, String surname, String patronymic, Sex sex, int age, Student[] children){
		super(name,surname, patronymic, sex, age, children);
	}
	
	public int getMoney(){
		int sum = 0;
        int count = 0;
        for (int i = 0; i < getNumberOfChildren(); i++) {
            int numberOfExams = getChild(i).getNumberOfExams();
            for (int j = 0; j < numberOfExams; j++) {
                count++;
                sum += children[i].getMark(j);
            }
        }

        sum /= count;

        return sum * 10;
	}

}