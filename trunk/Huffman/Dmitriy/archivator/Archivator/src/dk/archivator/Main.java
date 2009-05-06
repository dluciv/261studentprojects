package dk.archivator;

import java.io.IOException;

/*
 *        http://www.255.ru/index.php?newsid=968 <----- алгоритмы взяты оттуда
 */

public class Main {

	private static String DIR = "";
	private static String ARCHIVATE_IN_FILEPATH = DIR + "";
	private static String ARCHIVATE_OUT_FILEPATH = DIR + "";
	private static String DEARCHIVATE_IN_FILEPATH = DIR + "";
	private static String DEARCHIVATE_OUT_FILEPATH = DIR + "";

	public static void main(String args[]) throws IOException {

		Archivator a = new Archivator(ARCHIVATE_IN_FILEPATH,
				ARCHIVATE_OUT_FILEPATH);
		Dearchivator d = new Dearchivator(DEARCHIVATE_IN_FILEPATH,
				DEARCHIVATE_OUT_FILEPATH);
	}

}
