package database.generator;

import dbentities.Sex;
import utils.Util;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 20.08.2009
 * Time: 20:36:52
 * To change this template use File | Settings | File Templates.
 */
public class RandomLastNameGenerator {
    private static String[] maleLastNames = {"Селяков", "Пуговкин", "Тыковкин", "Варенье", "Кириллов",
            "Иванов", "Петров", "Яблученко", "Пугачев", "Яронян", "Степанов", "Миронов",
            "Райкин", "Костин", "Чехов", "Горький", "Сладкий", "Кислый", "Толстой", "Динуров",
            "Шарапов", "Карпиков", "Карпов", "Карасев", "Карасиков", "Двернов", "Косяков",
            "Заборов", "Товкайло", "Плотников", "Столяров", "Кирюшкин", "Кириленский", "Попов",
            "Дураков", "Тесла", "Чебышевский","Чебышев", "Фихтенгольц", "Армани", "Тростняков", "Лисняк",
            "Ферсман", "Фидельман", "Киров", "Фукин", "Самолетов", "Взлетайло", "Вертолетов",
            "Порося", "Подкорытов", "Подподкорытов", "Надкорытов", "Тухлин", "Губин", "Мишкин",
            "Билан", "Поросенков", "Свинтусов", "Йохохо", "Виноградов", "Демидович", "Фриш"};
    private static String[] femaleLastNames = {"Селякова", "Пуговкина", "Тыковкина", "Варенье", "Кириллова",
            "Иванова", "Петрова", "Яблученко", "Пугачева", "Яронян", "Степанова", "Миронова",
            "Райкина", "Костина", "Чехова", "Горькая", "Сладкая", "Кислая", "Толстая", "Динурова",
            "Шарапова", "Карпикова", "Карпова", "Карасева", "Карасикова", "Двернова", "Косякова",
            "Заборова", "Товкайло", "Плотникова", "Столярова", "Кирюшкина", "Кириленская", "Попова",
            "Дуракова", "Тесла", "Чебышевская","Чебышев", "Фихтенгольц", "Армани", "Тростнякова", "Лисняк",
            "Ферсман", "Фидельман", "Кирова", "Фукина", "Самолетова", "Взлетайло", "Вертолетова",
            "Порося", "Подкорытова", "Подподкорытова", "Надкорытова", "Тухлина", "Губина", "Мишкина",
            "Билан", "Поросенкова", "Свинтусова", "Йохохо", "Винуградова", "Демидович", "Фриш"};


    public static String generate(Sex sex) {
        String result = "";
        Random r = new Random();
        switch (sex) {
            case FEMALE:
                result = femaleLastNames[(int)(r.nextFloat() * (femaleLastNames.length - 1))];
                break;
            case MALE:
                result = maleLastNames[(int)(r.nextFloat() * (maleLastNames.length - 1))];
                break;
            case UNKNOWN:
                result = maleLastNames[(int)(r.nextFloat() * (maleLastNames.length - 1))];
                break;
        }
        return result;
    }

    public static int maxLength(){
        int maleLength = Util.maxElement(maleLastNames);
        int femaleLength = Util.maxElement(femaleLastNames);
        if(maleLength > femaleLength) return maleLength;
        return femaleLength;
    }
}
