package tools;

import java.util.LinkedList;
import lexerandparser.Position;

public class Tool {
    private static int errorQnt = 0;
    private static LinkedList<ErrorLogCell> errorLog = new LinkedList<ErrorLogCell>();
    private static LinkedList<LogChangedListener> listeners = new LinkedList<LogChangedListener>();

    public static void fixError(String message, Position position) {
        //System.out.println("dsfsdfs");
        errorQnt++;
        errorLog.add(new ErrorLogCell(message, position));
        for (LogChangedListener listener : listeners) {
            listener.logChanged(errorLog);
        }
    }

    public static void addLogChangedListener(LogChangedListener logListener) {
        listeners.add(logListener);
    }

    public static int getErrorQnt() {
        //System.out.println("dsfsdfs");
        return errorQnt;
    }

    public static LinkedList<ErrorLogCell> getErrorLog() {
        return errorLog;
    }

    public static void clearErrorLog() {
        errorLog.clear();
        errorQnt = 0;
    }
}
