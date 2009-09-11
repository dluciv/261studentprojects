package utils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.awt.*;

import database.parser.ParserException;
import dbentities.Sex;

/**
 * Класс для хранения сервисных методов
 * @author nastya
 * Date: 20.08.2009
 * Time: 22:45:05
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    public static final int MILLI = 1000;
    public static final int MIKRO = 1000000;
    public static final int NANO = 1000000000;
    private static final int HORIZONTAL_OFFSET = 10;
    private static final int VERTICAL_OFFSET = 50;
    private static final String DEFAULT_DATE_FORMAT = "<dd/MM HH:mm:ss>: ";

    /**
     * Возвращает максимальную длину среди элементов в массиве строк
     *
     * @param stringArray массив строк, максимальную длину для которого необходимо
     * определить
     * @return длина самой длинной строки среди указанных
     */
    public static int maxElement(String[] stringArray) {
        int max = 0;
        for (String street : stringArray) {
            if (max < street.length()) {
                max = street.length();
            }
        }
        return max;

    }


    /**
     * Унифицированный способ открытия окна для выбора файлов в проекте
     *
     *
     * @param parent компонент, к которому данный выборщик файлов привязывается
     * @param save если <coe>true</code>, то открываетя диалог на сохранение. Иначе - на открытие
     * @param defaultName имя файла, подставляемое по умолчанию
     * @param filterPattern фильтр отображаемых файлов. Вне зависимости от него, каталоги отображаются
     * @param filterDescription описание фильтра отображаемых файлов, предоставляемое пользователю
     * @return файл, выбранный пользователем, <code>null</code>, если файл выбран не был
     */
    public static File openFileChooser(JComponent parent, boolean save, String defaultName, final String filterPattern, final String filterDescription) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setSelectedFile(new File(defaultName));
        chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                Pattern pattern = Pattern.compile(filterPattern);
                Matcher matcher = pattern.matcher(f.getName());
                return matcher.matches();
            }

            public String getDescription() {
                return filterDescription;
            }
        });
        int result = 0;
        if (save) {
            result = chooser.showSaveDialog(parent);
        } else {
            result = chooser.showOpenDialog(parent);
        }
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * Представляет время в понимаемом человеком формате: ss ms mks ns
     *
     * @param time время в нано-секундах, которое необходимо представить
     * @return то же время с обозначением секнд, микро=, милли- и нано-секунд
     */
    public static String reprsentTime(long time) {
        String result = "";
        long ns = time % MILLI;
        long mks = time % MIKRO / MILLI;
        long ms = time % NANO / MIKRO;
        long s = time / NANO;
        if (s > 0) {
            result = s + "c " + ms + "мс " + mks + "мкс " + ns + "нс";
        } else if (ms > 0) {
            result = ms + "мс " + mks + "мкс " + ns + "нс";
        } else if (mks > 0) {
            result = mks + "мкс " + ns + "нс";
        } else {
            result = ns + "нс";
        }
        return result;
    }

    public static <K> void cutFirstHalf(Vector<K> list) {
        Vector<K> tail = firstHalf(list);
        for (int i = 0; i < tail.size(); i++) {
            list.removeElement(tail.get(i));
        }
    }

    /**
     * "Делит" Vector пополам и возвраает первую часть
     *
     * @param list первая половина элементов в Vector
     * @param <K> шаблон для элементов в Vector
     * @return  перая половина элементов
     */
    public static <K> Vector<K> firstHalf(Vector<K> list) {
        Vector<K> result = new Vector<K>();
        for (int i = 0; i < list.size() / 2; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    /**
     * Сериализует объект в файл
     *
     * @param object сериализуемый объект
     * @param choosedFile файл, выбранный для сериализации
     * @throws IOException когда произошла какая-либо ошибка записи
     */
    public static void serialize(Object object, File choosedFile) throws IOException {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(choosedFile));
        out.writeObject(object);
        out.close();
    }

    /**
     * Определяет причину ошибки разбора базы и сообщает о ней
     *
     * @param exc ошибка разбора базы
     * @param panel компонент, к которому прикрепляются все сообщения
     */
    public static void processParserErrors(ParserException exc, JPanel panel) {
        if (exc.getCause() instanceof FileNotFoundException) {
            JOptionPane.showMessageDialog(panel, Messages.ERROR_NO_FILE_DB, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        } else if (exc.getCause() instanceof IOException) {
            JOptionPane.showMessageDialog(panel, Messages.ERROR_IO_DB, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        } else if (exc.getCause() instanceof NullPointerException) {
            JOptionPane.showMessageDialog(panel, Messages.ERROR_IO_DB, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(panel, exc.getMessage(), Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Приводит текущую дату к определенному выводу для записи в лог
     *
     * @return текущая дата в необходимом формате
     */
    public static String logDate() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    /**
     * Предоставляет способ одинаковым образом отображать пол во всем приложении
     *
     * @param sex пол
     * @return пол в текстовом виде
     */                                        
    public static String representSex(Sex sex) {
        switch (sex) {
            case FEMALE:
                return "Ж";
            case MALE:
                return "М";
            case UNKNOWN:
                return "";
        }
        return "";
    }

    /**
     * Определяет подходящий размер для главного окна, основываясь на параметрах системы
     *
     * @return подходящий размер рабочего окна
     */
    public static Dimension defineSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return new Dimension((int)screenSize.getWidth() - 2 * HORIZONTAL_OFFSET, (int)screenSize.getHeight() - 2 * VERTICAL_OFFSET);
    }

    /**
     * Определяет местоположение левого верхнего угла главного окна так, что бы
     * окно было почередь экрана
     * @param size размер окна
     * @return необходимое местоположение левого верхнего угла
     */
    public static Point defaineLocation(Dimension size){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return new Point((int)screenSize.getWidth()/2 - (int)size.getWidth() / 2, (int)screenSize.getHeight()/2 - (int)size.getHeight() / 2);
    }
}
