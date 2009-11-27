package msavenko.parentsandstudens;

import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import msavenko.parentsandstudens.IHuman.Sex;
import org.junit.Assert;
import org.junit.Test;


public class GeneratorTest {
    
    private boolean IfInMassive(String[] mas, String str){
        boolean ifIn = false;
        
        for (int i=0; i<mas.length; i++){
            if (mas[i]==str) {
                ifIn = true;
            }
        }
        
        return ifIn;        
    }
    
    @Test
    public void GenerateFemaleNameTest() {        
        Assert.assertTrue(IfInMassive(Generator.namesFemale, Generator.generateName(Sex.female)));
    }
    
    @Test
    public void GenerateMaleNameTest() {
        Assert.assertTrue(IfInMassive(Generator.namesMale, Generator.generateName(Sex.male)));
    }
    
    @Test
    public void GenerateFemaleSurnameTest() {
        String surname = "Соловьев";
        Assert.assertEquals((surname + "а"),Generator.generateChildsSurname(Sex.female, surname));
    }
    
    @Test
    public void GenerateMaleSurnameTest() {
        String surname = "Соловьев";
        Assert.assertEquals(surname,Generator.generateChildsSurname(Sex.male, surname));
    }
    
    @Test
    public void GenerateMalePatronymicTest() {
        String name = "Петр";
        Assert.assertEquals(name + "ович", Generator.generatePatronymic(name, Sex.male));
    }
    
    @Test
    public void GenerateFemalePatronymicTest() {
        String name = "Петр";
        Assert.assertEquals(name + "овна", Generator.generatePatronymic(name, Sex.female));
    }
    
    /*
    private List<IHuman> CreateList(){ 
        
        class Botan extends Student {            
            private int[] marks = new int[3];
            
            public Botan(String Name, String Surname, String Patronymic, Sex Sex, int Age, String Faculty) {
                super(Name,Surname,Patronymic,Sex,Age,Faculty);
                marks[0]=5;
                marks[1]=4;
                marks[2]=3;
            }
            
            @Override
            public int getMarkForExam(int index) { return marks[index]; }
        }
        
        LinkedList<IHuman> FatherChildrenList = new LinkedList<IHuman>();
        Student[] children = new Student[2];
        
        children[0] = new Botan("","","",Sex.male,18,"");
        children[1] = new Botan("","","",Sex.male,19,"");
        Father father1 = new Father("","","",Sex.male,40,children);
        Father father2 = new Father("","","",Sex.male,42,children);
        Father father3 = new CoolFather("","","",Sex.male,52,children);
        Father father4 = new CoolFather("","","",Sex.male,32,children);
        
        FatherChildrenList.add(father1);
        FatherChildrenList.add(father2);
        FatherChildrenList.add(father3);
        FatherChildrenList.add(father4);
        
        for (int j = 0; j < father1.getStudentCount(); j++) {
            FatherChildrenList.add(father1.getStudent(j));
        }
        
        for (int j = 0; j < father2.getStudentCount(); j++) {
            FatherChildrenList.add(father2.getStudent(j));
        }
        
        for (int j = 0; j < father3.getStudentCount(); j++) {
            FatherChildrenList.add(father3.getStudent(j));
        }
        
        for (int j = 0; j < father4.getStudentCount(); j++) {
            FatherChildrenList.add(father4.getStudent(j));
        }
        
        return FatherChildrenList;
    }
    
    @Test
    public void CalcMoneyTest() {
        List<IHuman> FatherChildrenList = CreateList();
        
        Assert.assertEquals(80, Generator.calcMoney(FatherChildrenList));
    }
    
    @Test
    public void CalcMarkTest() {
        List<IHuman> FatherChildrenList = CreateList();
        
        Assert.assertEquals(4, Generator.calcMark(FatherChildrenList));
    }*/
    
}
