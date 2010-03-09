//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//class BasicHuman

package parentsandstudents;

public class BasicHuman implements IHuman {
	private String name;
	private String surname;
	private String patronymic;
	private Sex sex;
	private Integer age;
	
	public BasicHuman(String name, String surname, String patronymic, Sex sex, Integer age){
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.sex = sex;
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
	
	public Integer getAge(){
		return age;
	}

}
