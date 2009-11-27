/*
 * Interface for Human being, which have name, age and of particular sex
 * Savenko Maria (c)2009
 */

package msavenko.parentsandstudens;

public interface IHuman {
    public enum Sex {male, female};
    
    public String getName();
    
    public String getSurname();
    
    public String getPatronymic();
    
    public Sex getSex();
    
    public int getAge();
}
