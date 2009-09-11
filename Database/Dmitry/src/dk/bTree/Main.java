package dk.bTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import dk.generator.AdressBookGenerator;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("input file size");
		String fileSize = input.readLine();
		int size = getInt(fileSize);
		if (size >= 0) {
			AdressBookGenerator a = new AdressBookGenerator(size);
			a.generateRandomBook();
			File f = new File(AdressBookGenerator.bookFileName);
			BTree tree = new BTree(f);
			tree.buildTree();
			System.out.println("show tree y/n?");
			String showTree = input.readLine();
			if (showTree.compareTo("y") == 0) {
				tree.showTree();
			}
			while (true) {
				System.out.println("input first key:");
				String firstKey = input.readLine();
				System.out.println("input second key:");
				String secondKey = input.readLine();
				System.out.println("input number of records");
				String numberRecords = input.readLine();
				int number = getInt(numberRecords);
				tree.showRecords(addBlanks(firstKey), addBlanks(secondKey),
						number);
				System.out.println("exit y/n?");
				String exit = input.readLine();
				if (exit.compareTo("y") == 0) {
					break;
				}
			}
		} else {
			System.out.println("input error");
		}
	}

	private static int getInt(String s) {
		int d = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= 57 && s.charAt(i) >= 48) {
				d *= 10;
				d += (s.charAt(i) - 48);
			} else {
				return -1;
			}
		}
		return d;
	}

	private static String addBlanks(String s) {
		if (s.length() > AdressBookGenerator.FIELD_LENGTH - 1) {
			s = s.substring(0, AdressBookGenerator.FIELD_LENGTH - 1);
		} else {
			for (int i = s.length(); i < AdressBookGenerator.FIELD_LENGTH - 1; i++) {
				s += " ";
			}
		}
		return s;
	}

}
