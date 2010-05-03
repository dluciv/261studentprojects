/*
 * Генератор людей;
 * Zubrilin Andrey,261 gr (c) 2009
 */

package parentsandstudents;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PeopleGeneration {

    //Мужские имена;
    public static final String[] nMale = new String[]{
        "Саламан", "Феогний", "Добромир", "Завулон", "Ратибор"};

    //Женские имена;
    public static final String[] nFemale = new String[]{
        "Епихария", "Агафоклия", "Нунехия", "Трифена", "Шушаника"};

    //Мужские фамилии;
    public static final String[] snMale = new String[]{
        "Похудилов", "Белокопытов", "Вшивцев", "Шило", "Врышдыр",};

    //Факультет;
    public static final String[] fac = new String[]{
        "Мат-Мех", "Психфак", "Филфак"};

    public static final Random magicNum = new Random();

    //Генератор женских Фамилий;
    public static String createFemaleSurname(String snMale){
        if(snMale.endsWith("в"))
            return snMale+"а";
        if(snMale.endsWith("о")|snMale.endsWith("а"))
            return snMale+"";
        return snMale+"ова";
    }

    //Присваеваем имя;
    public static String giveName(boolean sex){
        if(sex)          
            return nMale[magicNum.nextInt(nMale.length)];
        return nFemale[magicNum.nextInt(nFemale.length)];
    }

    //Даем Фамилию;
    public static String takeSurname(boolean sex){
        if(sex)
            return snMale[magicNum.nextInt(snMale.length)];
        return createFemaleSurname(snMale[magicNum.nextInt(snMale.length)]);
    }

    //Даем отчество;
    public static String chooseFather(String mayBeItsMyFather,boolean sex){
        if(sex)
            return mayBeItsMyFather + "ович";
        return mayBeItsMyFather + "овна";
    }

    //Выбираем факультет;
    public static String chooseFac() {
        return fac[magicNum.nextInt(fac.length)];
    }

    //Генерируем список людей;
        public static List<IHuman> generateCollection() {
            LinkedList<IHuman> res = new LinkedList<IHuman>();
            int parents = 10+magicNum.nextInt(10);
            Parent p;
            for (int i = 0; i < parents; i++) {
                p = chooseParent(parents);
                res.add(p);
                for(int j = 0; j < magicNum.nextInt(2) + 1 ;j++)
                    res.add(p.crowd[j]);
            }
            return res;
        }

    //Генерируем отца;
    public static Parent chooseParent(int parentNum){
        int genAge = magicNum.nextInt(40) + 25;
        boolean sex = true;
        String parName = giveName(sex);
        String parSurname = takeSurname(sex);
        String parPatron = chooseFather(giveName(sex), sex);

        int students  = parentNum;

        Student[] crowd = new Student[students];
        for (int i = 0; i < students; i++)
            crowd[i] = createANewGenius(parName, parSurname);

        if (magicNum.nextBoolean())
            return new Parent(parSurname,parName,  parPatron, sex, genAge, crowd);
        return new CoolParent(parSurname,parName, parPatron, sex, genAge, crowd);
    }

    //Генерируем студента;
    public static Student createANewGenius(String genFatherName,String genSurname){
        int genAge = magicNum.nextInt(10) + 17;

        boolean sex = magicNum.nextBoolean();

        String genName = giveName(sex);
        if(!sex)
               genSurname = createFemaleSurname(genSurname);
        String genPatron = chooseFather(genFatherName,sex);

        String whatFac = chooseFac();

        if(magicNum.nextBoolean())
            return new Botan(genSurname,genName,genPatron,sex,genAge,whatFac);
        return new Student(genSurname,genName,genPatron,sex,genAge,whatFac);
      
    }

}
