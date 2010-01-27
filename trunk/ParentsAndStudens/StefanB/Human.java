/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

public class Human implements IHuman {
	
	String name, surname, patronymic;
	Sex sex;
	int age;
	
	public Human(String name,String surname,String patronymic, Sex sex, int age){
		this.sex = sex;
		this.name = name;
		if (sex == Sex.FEMALE) surname += "a";
		this.surname = surname;
		this.patronymic = patronymic;		
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getPatronymic(){
		return patronymic;
	}
	
	public Sex getSex(){
		return sex;
	}
	
	public int getAge(){
		return age;
	}
	
	public void printIdentity(){
		System.out.println(this.getClass().getSimpleName()+ ": " + surname + " " + name + " " + patronymic);
	}

}
