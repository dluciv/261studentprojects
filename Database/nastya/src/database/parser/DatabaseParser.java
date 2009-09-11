package database.parser;

import database.DatabaseConstants;
import dbentities.Card;
import dbentities.Sex;

import java.util.Vector;
import java.io.*;
import java.nio.ByteBuffer;

import utils.Messages;

/**
 * Разборщик базы. Позволяет читать записи из базы
 * @author nastya
 * Date: 21.08.2009
 * Time: 1:22:00
 *
 */
public class DatabaseParser {
    private int nameLength;
    private int nameLastLength;
    private int nameMiddleLength;
    private int addressLength;
    private int phoneLength;
    private int sexLength = DatabaseConstants.DEFAULT_SEX_LENGTH;

    private long time = 0l;
    private Vector<Long> timeStat;
    private static final int INT_LENGTH = 4;
    private String filename;
    private static final int INT_DATA_FIELD_COUNT = 5;

    /**
     * Открывает файл и считывает сервисную информацию из него, необходимую для
     * дальнейшего чтения из файла записей
     * @param filename файл базы
     * @throws ParserException ошибка чтения сервисной информации
     */
    public DatabaseParser(String filename) throws ParserException {
        this.filename = filename;
        prepare();
    }

    /**
     * Считывает сервисную информацию о длине полей из файла
     * @throws ParserException структура файла нарушена (выбран неверный файл)
     */
    private void prepare() throws ParserException {
        DataInputStream dataReader = null;
        InputStream stream = null;
        try {
            stream = new FileInputStream(filename);
            dataReader = new DataInputStream(stream);
            parseServiceInformation(dataReader);
            dataReader.close();
        } catch (FileNotFoundException e) {
            throw new ParserException(e);
        } catch (IOException e) {
            throw new ParserException(e);
        } finally {
            try {
                dataReader.close();
            } catch (NullPointerException ex) {
                throw new ParserException(ex);
            } catch (IOException e) {
                throw new ParserException(e);
            }
        }
    }

    /**
     * Считывает определенное количество записей начиная с какой-то определенной позиции в файле
     * @param from позиция в файле, с которой небходимо начать чтение
     * @param cardCount количество карточек, которые необходимо прочитать; <code>-1</code>,
     * если необходио прочитать все карточки до конца
     * @return список прочитанных карт
     * @throws ParserException ошибка чтения из файла
     */
    public Vector<Card> parse(long from, int cardCount)  throws ParserException{
        timeStat = new Vector<Long>();
        int readedCount = 0;
        Vector<Card> data = new Vector<Card>();
        time = 0l;
        FileInputStream stream;
        InputStreamReader bufferedReader = null;
        try {
            long filePosition = from;
            stream = new FileInputStream(filename);
            stream.skip(filePosition);
            bufferedReader = new InputStreamReader(stream);
            while (cardCount == -1 || readedCount < cardCount) {
                long startTime = System.nanoTime();
                Card card = readNextCard(bufferedReader);
                card.setFilePosition(filePosition);
                time += System.nanoTime() - startTime;
                timeStat.add(System.nanoTime() - startTime);
                data.add(card);
                readedCount++;
                filePosition += card.getByteLength();
            }
        } catch (EOFException ex) {
            return data;
        } catch (FileNotFoundException e) {
            throw new ParserException(e);
        } catch (IOException e) {
            throw new ParserException(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new ParserException(e);
            }
        }
        return data;
    }

    /**
     * Считывает все записи из файла
     * @return список карточек базы данных
     * @throws ParserException ошибка чтения и распознавания файла
     */
    public Vector<Card> parse() throws ParserException {
        return parse(INT_DATA_FIELD_COUNT * INT_LENGTH, -1);
    }

    /**
     * Читает одну запись, начиная с определенного байта
     *
     * @param from байт, с которого необходимо читать
     * @return прочитанная карточка
     * @throws ParserException ошибка чтения из базы
     */
    public Card parseSingle(long from) throws ParserException {
        Vector<Card> result = parse(from, 1);
        return (result == null || result.isEmpty()) ? null : result.firstElement();
    }

    /**
     * Считывает сервисную информацию из файла
     *
     * @param reader стрим т файла
     * @throws IOException ошибка чтения
     */
    private void parseServiceInformation(DataInputStream reader) throws IOException {

        nameLength = reader.readInt();
        nameMiddleLength = reader.readInt();
        nameLastLength = reader.readInt();
        phoneLength = reader.readInt();
        addressLength = reader.readInt();
    }


    /**
     * Читает "следующую" карточку. То есть начиная с текущей позиции потока
     * @param reader откуда читаем
     * @return прочитанная карта
     * @throws IOException ошибка работы с ФС
     * @throws ParserException ошибка разбора
     * @throws EOFException конец файла (не ошибка)
     */
    private Card readNextCard(InputStreamReader reader) throws IOException, ParserException, EOFException {
        String name = readData(reader, nameLength, true);
        String middleName = readData(reader, nameMiddleLength, false);
        String lastName = readData(reader, nameLastLength, false);
        String sexID = readData(reader, sexLength, false);
        String phone = readData(reader, phoneLength, false);
        String address = readData(reader, addressLength, false);
        Sex sex = DatabaseConstants.getSexById(sexID);
        Card card = new Card(name.trim(), lastName.trim(), middleName.trim(), sex, phone.trim(), address.trim());
        card.setByteLength(name.getBytes().length + middleName.getBytes().length+
                lastName.getBytes().length+ sexID.getBytes().length+
                phone.getBytes().length+ address.getBytes().length);
        return card;
    }

    /**
     * Читает очередное поле
     *
     * @param reader откуда читать
     * @param length длина поля
     * @param eofAccepted может ли оказаться при чтении этого поля, что достигнут конец файла
     * @return прочитанное поле полностью как есть в базе
     * @throws IOException ошибка работы с ФС
     * @throws ParserException ошибка разбора
     * @throws EOFException конец файла (не ошибка)
     */
    private String readData(InputStreamReader reader, int length, boolean eofAccepted) throws IOException, ParserException, EOFException {
        if (!reader.ready() && eofAccepted) throw new EOFException();
        char[] buffer = new char[length];

        int code = reader.read(buffer);
        if (code == -1) {
            if (eofAccepted) {
                throw new EOFException();
            } else {
                throw new ParserException(Messages.UNEXPECTED_END_OF_FILE);
            }
        } else if (code != length) {

            throw new ParserException(Messages.ERROR_READING_DATA);
        }
        return new String(buffer);
    }

    /**
     * Возвращает последнее общее время чтения записей
     * @return время чтения записей
     */
    public long getTime() {
        return time;
    }

    /**
     * Возвращает статистику чтения (сколько читалась каждая из записей), относящуююся
     * к последнему сеансу чтения (вызову одного метода для чтения карточек
     * @return список длин времен чтения каждой из карточек
     */
    public Vector<Long> getTimeStat() {
        return timeStat;
    }
}
