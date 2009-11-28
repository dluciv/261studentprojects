/*
 * Interface for all humans.
 * Korshakov Stepan - 261 Group - (c) 2009
 */

package hotheart.parentsandstudens;

/**
  * @author Korshakov Stepan
 */
public interface IHuman {

    String getName();
    String getSurname();
    String getPatronymic();

    Sex getSex();

    int getAge();
}
