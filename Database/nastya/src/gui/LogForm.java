package gui;

import utils.Util;

import javax.swing.*;
import java.util.Vector;

/**
 * Здесь ведется лог времени исполнения запросов к базе данных 
 *
 * @author nastya
 *         Date: 30.08.2009
 *         Time: 4:20:59
 */
public class LogForm {
    private JTextArea text;
    private JPanel panel;

    public void log(long recordCount, long time, Vector<Long> timeStat) {
        if (recordCount == 0) {
            text.append(Util.logDate() + "Записей в базе не найдено.\n");
        } else if (recordCount == 1) {
            text.append(Util.logDate() + "Прочитана одна запись за время " + Util.reprsentTime(time) + "\n");
        } else {
            text.append(Util.logDate() + "Прочитано записей: " + recordCount + "; " +
                    "Общее время чтения: " + Util.reprsentTime(time) + "; " +
                    "Среднее время чтения одной записи: " + Util.reprsentTime(time / recordCount) + "");
            if (timeStat != null) {
                text.append(" {" + Util.reprsentTime(timeStat.get(1)));
                for (int i = 1; i < timeStat.size(); i++) {
                    text.append(", " + Util.reprsentTime(timeStat.get(i)));
                }
                text.append("}");
            }
            text.append("\n");

        }
        text.repaint();
    }

    public void log(long time) {
        text.append(Util.logDate() + "Время подсчета количества записей: " + Util.reprsentTime(time) + "\n");
        text.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }
}
