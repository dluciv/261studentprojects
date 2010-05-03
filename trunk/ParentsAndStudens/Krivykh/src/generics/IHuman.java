//(c) Кривых Алексей 2009г.
//Отцы и дети
package generics;

public interface IHuman {

    public String getFirstName();

    public String getSecondName();

    public String getPatronymic();

    public EnumSex getSex();

    public int getAge();

    @Override
    public String toString();
}
