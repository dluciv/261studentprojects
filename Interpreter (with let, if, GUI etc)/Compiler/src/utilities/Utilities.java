package utilities;

import java.util.LinkedList;
import lebedev.Position;

public class Utilities {
	private static int errorQnt = 0;
	private static LinkedList<ErrorLogCell> errorLog = new LinkedList<ErrorLogCell>();

	public static void fixError(String message, Position position) {
		errorQnt++;
		errorLog.add(new ErrorLogCell(message, position));
	}
}
