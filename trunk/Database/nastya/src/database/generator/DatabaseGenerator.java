package database.generator;

import dbentities.Sex;

import java.io.*;
import java.util.Random;

import database.DatabaseConstants;

/**
 * Предоставляет методы для генерации тестовой базы данных
 *
 * @author nastya
 * Date: 20.08.2009
 * Time: 21:43:38
 */
public class DatabaseGenerator {

    private static final int DEFAULT_CARDS_COUNT = 10000000;
    private int nameLength = DatabaseConstants.DEFAULT_NAME_LENGTH;
    private int nameLastLength = DatabaseConstants.DEFAULT_LAST_NAME_LENGTH;
    private int nameMiddleLength = DatabaseConstants.DEFAULT_MIDDLE_NAME_LENGTH;
    private int addressLength = DatabaseConstants.DEFAULT_ADDRESS_LENGTH;
    private int phoneLength = DatabaseConstants.DEFAULT_PHONE_LENGTH;


    public DatabaseGenerator() {
    }


    /**
     * Генерирует базу определенной длины(в карточках) и записывает ее в файл
     *
     * @param fileName файл, где будет храниться база
     * @param cardsCount количество карт для генерации
     * @throws GeneratorException какая-либо ошибка во время генерации
     */
    public void generate(String fileName, int cardsCount) throws GeneratorException {
        if (cardsCount <= 0) cardsCount = DEFAULT_CARDS_COUNT;
        DataOutputStream writer = null;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(fileName);
            writer = new DataOutputStream(stream);
            //Записываем длины всех полей в первые 20 байтов файла
            writeServiceInformation(writer);
            OutputStreamWriter mainWriter = new OutputStreamWriter(stream);
            // Генерируем необходимое количество карточек
            for (int i = 0; i < cardsCount; i++) {
                Random r = new Random();
                Sex sex = generateSex(r);
                writeName(mainWriter, sex);
                writeMiddleName(mainWriter, sex);
                writeLastName(mainWriter, sex);
                writeSex(mainWriter, sex);
                writePhone(mainWriter);
                writeAddress(mainWriter);
            }
            mainWriter.flush();
        } catch (IOException e) {
            throw new GeneratorException(e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                throw new GeneratorException(e);
            } catch (NullPointerException e) {
                throw new GeneratorException(e);
            }
        }
    }

    /**
     * Записывает в файл инфрмацию о длинах всех полей
     * @param writer куда пишем
     * @throws IOException ошибка записи
     */
    private void writeServiceInformation(DataOutputStream writer) throws IOException {
        writer.writeInt(nameLength);
        writer.writeInt(nameMiddleLength);
        writer.writeInt(nameLastLength);
        writer.writeInt(phoneLength);
        writer.writeInt(addressLength);
        writer.flush();
    }


    private void writeAddress(OutputStreamWriter writer) throws IOException {
        String address = RandomAddressGenerator.generate();
        address = equalize(address, addressLength);
        writer.write(address);
    }

    private void writePhone(OutputStreamWriter writer) throws IOException {
        Random r = new Random();
        String phone = "";
        for (int i = 0; i < phoneLength; i++) {
            phone += r.nextInt(10);
        }
        writer.write(phone);
    }

    private void writeSex(OutputStreamWriter writer, Sex sex) throws IOException {
        writer.write(DatabaseConstants.getIdBySex(sex));
    }

    private void writeLastName(OutputStreamWriter writer, Sex sex) throws IOException {
        String name = RandomLastNameGenerator.generate(sex);
        name = equalize(name, nameLastLength);
        writer.write(name);
    }

    private void writeMiddleName(OutputStreamWriter writer, Sex sex) throws IOException {
        String name = RandomMiddleNameGenerator.generate(sex);
        name = equalize(name, nameMiddleLength);
        writer.write(name);
    }

    private void writeName(OutputStreamWriter writer, Sex sex) throws IOException {
        String name = RandomNameGenerator.generate(sex);
        name = equalize(name, nameLength);
        writer.write(name);
    }

    /**
     * Дополняет строку пробелами до необходимой длины поля. Ну или обрезает, но это
     * не в нашем случае
     * @param string строка
     * @param length длина поля. До нее дополняется рпобелами строка
     * @return строку с необходимой длиной. Полноценное поле
     */
    private String equalize(String string, int length) {
        if (string.length() > length) {
            string = string.substring(0, length);
        } else if (string.length() < length) {
            string = addSpaces(string, length - string.length());
        }
        return string;
    }

    /**
     * Добавляет в конец строки определенное количество пробелов
     * @param string строку, к которой необходимо приписать пробелы
     * @param spaceCount колчиество пробелов
     * @return строка, дополненная пробелами
     */
    private String addSpaces(String string, int spaceCount) {
        for (int i = 0; i < spaceCount; i++) {
            string += DatabaseConstants.SPACE;
        }
        return string;
    }

    private Sex generateSex(Random r) {
        int result = r.nextInt(3);
        switch (result) {
            case 0:
                return Sex.MALE;
            case 1:
                return Sex.FEMALE;
            case 2:
                return Sex.UNKNOWN;
            default:
                return Sex.UNKNOWN;
        }
    }


    
}
