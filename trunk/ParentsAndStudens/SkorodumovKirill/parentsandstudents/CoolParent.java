//by Skorodumov Kirill gr.: 261
//parentsandstudents task
//class CoolParent

package parentsandstudents;

public class CoolParent extends Parent {
	private Integer money;
	
	public CoolParent(String name, String surname, String patronymic, Sex sex, Integer age,
			Student[] children)
	{
		super(name,surname,patronymic,sex,age,children);	
		money = countMoney();
	}
	
	private Integer countMoney()
	{
		Integer sum = 0;
        Integer count = 0;
        Student[] children = getChildren();
        for (Integer i = 0; i < getChildrenNum(); i++) {
            Integer marksCount = children[i].getExamNum();
            for (int j = 0; j < marksCount; j++) {
                count++;
                sum += children[i].getMark(j);
            }
        }

        sum /= count;

        return sum * 10;
	}
	
	public Integer getMoney()
	{
		return money;
	}
}
