/*
 * Интерфейс IHuman
 * Zubrilin Andrey,261 gr (c) 2009
 */

package parentsandstudents;

public interface IHuman {

    public String Name();
    public String Surname();
    public String Patron();
    
    public boolean Sex();

    public int Age();
    public HumanType hType();

    public void getInfo();
}
