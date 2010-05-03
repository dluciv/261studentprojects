/*
 * there're some human characteristics: name, surename, patronymic, sex and age;
 */

package fathersandchildren;

public interface IHuman {
    String getSurname();
    String getName();
    String getPatronymic();
    
    Sex getSex();

    int getAge();
}
