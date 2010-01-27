/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

public class Parent extends Human {
	
	Student [] children;
	
	public Parent(String name, String surname, String patronymic, Sex sex, int age, Student[] children){
		super(name,surname, patronymic, sex, age);
		this.children = children;
	}
	
	public int getNumberOfChildren(){
		return children.length;
	}
	
	public Student getChild(int index){
		return children[index];
	}

}
