package tools;

import java.util.LinkedList;
import lexerandparser.Position;

public class Tool {

    private static int errorQnt = 0;
    private static LinkedList<ErrorLogCell> errorLog = new LinkedList<ErrorLogCell>();

    public static void fixError(String message, Position position) {
        errorQnt++;
        errorLog.add(new ErrorLogCell(message, position));
    }

    public static int getErrorQnt() {
        return errorQnt;
    }

    public static LinkedList<ErrorLogCell> getErrorLog() {
        return errorLog;
    }

    public static void clearErrorLog() {
        errorLog.clear();
    }

    public static void increaseErrorQnt() {
        errorQnt++;
    }

    public static void clearErrorQnt() {
        errorQnt = 0;
    }
}
