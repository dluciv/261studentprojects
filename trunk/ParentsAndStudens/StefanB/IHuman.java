package fathers_sons;

public interface IHuman {
	
	public enum Sex{
		male, female
	}
	
	public String getName();
	public String getSurname();
	public String getPatronymic();
	
	public Sex getSex();
	
	public int getAge();
	
	public void printIdentity();

}
