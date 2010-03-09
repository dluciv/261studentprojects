/*
 * (c) Stefan Bojarovski 2009
 * */
package fathers_sons;

public interface IHuman {
	
	public String getName();
	public String getSurname();
	public String getPatronymic();
	
	public Sex getSex();
	
	public int getAge();
	
	public void printIdentity();

}
