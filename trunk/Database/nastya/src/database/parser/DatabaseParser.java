package database.parser;

import database.DatabaseConstants;
import dbentities.Card;
import dbentities.Sex;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import utils.Messages;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 21.08.2009
 * Time: 1:22:00
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseParser {
    private int nameLength = DatabaseConstants.DEFAULT_NAME_LENGTH;
    private int nameLastLength = DatabaseConstants.DEFAULT_LAST_NAME_LENGTH;
    private int nameMiddleLength = DatabaseConstants.DEFAULT_MIDDLE_NAME_LENGTH;
    private int addressLength = DatabaseConstants.DEFAULT_ADDRESS_LENGTH;
    private int phoneLength = DatabaseConstants.DEFAULT_PHONE_LENGTH;
    private int sexLength = DatabaseConstants.DEFAULT_SEX_LENGTH;

    private long time = 0l;

    public DatabaseParser() {

    }

    public Vector<Card> parse(String filename) throws ParserException {
        Vector<Card> data = new Vector<Card>();
        time = 0l;
        BufferedReader reader = null;
        try {
            while (true) {
                reader = new BufferedReader(new FileReader(filename));
                try {
                    while (true) {
                        long startTime = System.nanoTime();
                        Card card = readNextCard(reader);
                        time += System.nanoTime() - startTime;
                        data.add(card);
                    }
                } catch (EOFException ex) {
                    return data;
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new ParserException(e);
                    }
                }
            }
//            return data;
        } catch (FileNotFoundException e) {
            throw new ParserException(e);
        } catch (IOException e) {
            throw new ParserException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new ParserException(e);
            } catch (NullPointerException e) {
                throw new ParserException(e);
            }
        }
    }

    private Card readNextCard(BufferedReader reader) throws IOException, ParserException, EOFException {
        String name = readData(reader, nameLength, true);
        String middleName = readData(reader, nameMiddleLength, false);
        String lastName = readData(reader, nameLastLength, false);
        String sexID = readData(reader, sexLength, false);
        String phone = readData(reader, phoneLength, false);
        String address = readData(reader, addressLength, false);
        Sex sex = DatabaseConstants.getSexById(sexID);
        return new Card(name, lastName, middleName, sex, phone, address);
    }

    private String readData(BufferedReader reader, int length, boolean eofAccepted) throws IOException, ParserException, EOFException {
        char buffer[] = new char[length];
        int code = reader.read(buffer, 0, length);
        if (code == -1) {
            if (eofAccepted) {
                throw new EOFException();
            } else {
                throw new ParserException(Messages.UNEXPECTED_END_OF_FILE);
            }
        } else if (code != length) {
            throw new ParserException(Messages.ERROR_READING_DATA);
        }
        return String.valueOf(buffer).trim();
    }

    public long getTime() {
        return time;
    }
}
