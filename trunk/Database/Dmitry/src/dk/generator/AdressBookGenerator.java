package dk.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class AdressBookGenerator {

	public static String bookFileName = "book.txt";
	public static int FIELD_LENGTH = 10;
	public static int RECORD_LENGTH = FIELD_LENGTH * 3 + 2;
	private static int RANGE = 10000;
	public static int BOOK_SIZE;
	private Vector<String> nameList;
	private Vector<String> surnameList;

	public AdressBookGenerator(int bookSize) {
		BOOK_SIZE = bookSize;
		nameList = new Vector<String>();
		surnameList = new Vector<String>();
		generateNameList();
		generateSurnameList();
	}

	private void generateNameList() {
		nameList.add("A");
		nameList.add("B");
		nameList.add("C");
		nameList.add("D");
		nameList.add("E");
		nameList.add("F");
	}

	private void generateSurnameList() {
		surnameList.add("X");
		surnameList.add("Y");
		surnameList.add("W");
		surnameList.add("N");
		surnameList.add("P");
		surnameList.add("Q");
	}

	public Record generateRecord() {
		Random random = new Random();

		int n = random.nextInt(6);
		int s = random.nextInt(6);

		String name = nameList.get(n) + random.nextInt(RANGE);
		String surname = surnameList.get(s) + random.nextInt(RANGE);
		String tel = "" + random.nextInt(9999999);

		name += generateBlanks(name.length());
		surname += generateBlanks(surname.length());
		tel += generateBlanks(tel.length());

		Record record = new Record(name, surname, tel);
		return record;
	}

	private String generateBlanks(int length) {
		String blanks = "";
		for (int i = length; i < FIELD_LENGTH; i++) {
			blanks += " ";
		}
		return blanks;
	}

	public void generateRandomBook() throws IOException {
		File f = new File(bookFileName);
		BufferedWriter bf = new BufferedWriter(new FileWriter(f));
		for (int i = 0; i < BOOK_SIZE; i++) {
			bf.write(generateRecord().toString());
			if (i != BOOK_SIZE - 1) {
				bf.newLine();
			}
		}
		bf.close();
	}
}
