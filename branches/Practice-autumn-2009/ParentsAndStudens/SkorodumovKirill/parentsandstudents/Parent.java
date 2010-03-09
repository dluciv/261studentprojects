//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//class Parent

package parentsandstudents;

public class Parent extends BasicHuman {
	Student[] children;
	
	public Parent(String name, String surname, String patronymic, Sex sex, Integer age,
			Student[] children) {
		super(name,surname,patronymic,sex,age);
		this.children = children;
		
	}
	
	public Student[] getChildren()
	{
		return children;
	}
	
	public Integer getChildrenNum()
	{
		return children.length;
	}

}
