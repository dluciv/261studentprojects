package database.generator;

import java.util.Random;
import utils.Util;

/**
 * Предоставляет методы для генерации случайного адреса
 *
 * @author nastya
 * Date: 20.08.2009
 * Time: 21:04:35
 *
 */
public class RandomAddressGenerator {
    private static String[] streets = {"Средний проспект", "Ул. Трех Чертей", "Ул. Красных Фортов",
            "Ул. 50 лет Октября", "Ул. Ленинградская", "Ул. Ленинская", "Ул. Космонавтов",
            "Ул. Малая Земля", "Ул. Молодежная", "Ул. Вязов", "Ул. Воинов-Интернационалистов",
            "Ул. Зеленая", "Ул. Истребительная", "Ул. Антона Чехова", "ул. имени Боттичелли",
            "Ул. Льва Толстого", "Ул. Сталина", "Ул. Ленина", "Ул. Андрея Леонидов", "ул. Бассейная",
            "Ул. Пабло Пикассо", "Ул. Андрея Рублева", "Ул. Александра Невского", "Ул. Мишки Косолапого",
            "Невский Проспект", "Чичеринская ул.", "Ул. Ботаническая", "Ул. Шахматова",
            "Большой проспект", "Малый проспект", "1-я линия В.О.", "2-я линия В.О.", "3-я линия В.О.",
            "4-я линия В.О.", "5-я линия В.О.", "6-я линия В.О.", "7-я линия В.О.", "8-я линия В.О.",
            "9-я линия В.О.", "10-я линия В.О.", "11-я линия В.О.", "12-я линия В.О.",
            "13-я линия В.О.", "14-я линия В.О.", "15-я линия В.О.", "16-я линия В.О.",
            "17-я линия В.О.", "18-я линия В.О.", "19-я линия В.О.", "20-я линия В.О."};

    public static final int MAX_HOUSE = 200;
    public static final int MAX_ROOM = 100;
    public static final String HOUSE_STRING = ", д. ";
    public static final String ROOM_STRING = " кв. ";


    /**
     * Генерирует случайный адрес
     * @return строку-адрес
     */
    public static String generate() {
        Random r = new Random();
        return streets[((int) (r.nextFloat() * (streets.length - 1)))] + HOUSE_STRING +
                r.nextInt(MAX_HOUSE) + ROOM_STRING + r.nextInt(MAX_ROOM);
    }

    /**
     * Максимальная длина адреса среди всех имеющихся. Используется для генерации базы
     * @return максимальная длина поля адреса
     */
    public static int maxLength(){
        int length = Util.maxElement(streets);
        length += HOUSE_STRING.length() + ROOM_STRING.length() + String.valueOf(MAX_HOUSE).length() + String.valueOf(MAX_ROOM).length();
        return length;
    }

}
