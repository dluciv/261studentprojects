/*
 * "Fathers and Children"
 * some generics example
 * (c) Yaskov Sergey, 261; 2009
 *
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
