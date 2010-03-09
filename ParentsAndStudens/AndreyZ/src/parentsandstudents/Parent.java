/*
 * Class Parent
 * Zubrilin Andrey,261 gr (c) 2009
 */

package parentsandstudents;

public class Parent implements IHuman {

    protected String surname;
    protected String name;
    protected String patron;
    protected boolean sex;
    protected int age;
    public Student[] crowd;

    public Parent(String surname,String name,String patron,boolean sex,int age,Student[] crowd){
        this.crowd = crowd;
        this.name = name;
        this.surname = surname;
        this.patron = patron;
        this.sex = sex;
        this.age = age;
    }
   
    public String Name() {
        return name;
    }

    public String Surname() {
        return surname;
    }

    public String Patron() {
        return patron;
    }

    public boolean Sex() {
        return sex;
    }

    public int Age() {
        return age;
    }
    public Student myChild(int id){
        return crowd[id];
    }
    public HumanType hType(){
        return HumanType.Parent;
    }

    public String getInfo(){
        String info ;
        info = "Отец              : "+surname + " " +
                    name + " " + patron + "; " + "Возраст : " + age;
        return info;
    }
}