package database.generator;

import dbentities.Sex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import database.DatabaseConstants;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 20.08.2009
 * Time: 21:43:38
 * To change this template use File | Settings | File Templates.
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


    public void generate(String fileName, int cardsCount) throws GeneratorException {
        if (cardsCount <= 0) cardsCount = DEFAULT_CARDS_COUNT;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writeServiceInformation(writer);
            for (int i = 0; i < cardsCount; i++) {
                Random r = new Random();
                Sex sex = generateSex(r);
                writeName(writer, sex);
                writeMiddleName(writer, sex);
                writeLastName(writer, sex);
                writeSex(writer, sex);
                writePhone(writer);
                writeAddress(writer);
            }
            writer.flush();
        } catch (IOException e) {
            throw new GeneratorException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new GeneratorException(e);
            } catch (NullPointerException e) {
                throw new GeneratorException(e);
            }
        }
    }

    private void writeServiceInformation(BufferedWriter writer) throws IOException {
        //TODO !!!! даункаст, однако
        writer.write((char)nameLength);
        writer.write((char)nameMiddleLength);
        writer.write((char)nameLastLength);
        writer.write((char)phoneLength);
        writer.write((char)addressLength);
    }


    private void writeAddress(BufferedWriter writer) throws IOException {
        String address = RandomAddressGenerator.generate();
        address = equalize(address, addressLength);
        writer.write(address);
    }

    private void writePhone(BufferedWriter writer) throws IOException {
        Random r = new Random();
        String phone = "";
        for (int i = 0; i < phoneLength; i++) {
            phone += r.nextInt(10);
        }
        writer.write(phone);
    }

    private void writeSex(BufferedWriter writer, Sex sex) throws IOException {
        writer.write(DatabaseConstants.getIdBySex(sex));
    }

    private void writeLastName(BufferedWriter writer, Sex sex) throws IOException {
        String name = RandomLastNameGenerator.generate(sex);
        name = equalize(name, nameLastLength);
        writer.write(name);
    }

    private void writeMiddleName(BufferedWriter writer, Sex sex) throws IOException {
        String name = RandomMiddleNameGenerator.generate(sex);
        name = equalize(name, nameMiddleLength);
        writer.write(name);
    }

    private void writeName(BufferedWriter writer, Sex sex) throws IOException {
        String name = RandomNameGenerator.generate(sex);
        name = equalize(name, nameLength);
        writer.write(name);
    }

    private String equalize(String string, int length) {
        if (string.length() > length) {
            string = string.substring(0, length);
        } else if (string.length() < length) {
            string = addSpaces(string, length - string.length());
        }
        return string;
    }

    private String addSpaces(String string, int length) {
        for (int i = 0; i < length; i++) {
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


    public int getNameLength() {
        return nameLength;
    }

    public void setNameLength(int nameLength) {
        this.nameLength = nameLength;
    }

    public int getNameLastLength() {
        return nameLastLength;
    }

    public void setNameLastLength(int nameLastLength) {
        this.nameLastLength = nameLastLength;
    }

    public int getNameMiddleLength() {
        return nameMiddleLength;
    }

    public void setNameMiddleLength(int nameMiddleLength) {
        this.nameMiddleLength = nameMiddleLength;
    }

    public int getAddressLength() {
        return addressLength;
    }

    public void setAddressLength(int addressLength) {
        this.addressLength = addressLength;
    }
}
