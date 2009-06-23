package dk.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Vector;

import dk.bTree.BTree;
import dk.generator.AdressBookGenerator;

public class IndexReader {

	private long tmpPoint;
	private int numberNodeOnLevel;
	private int childNumber;
	private HashMap<Integer, Vector<Integer>> indexLinks;
	private HashMap<Integer, Integer> indexShifts;

	public IndexReader() {
		indexLinks = new HashMap<Integer, Vector<Integer>>();
		indexShifts = new HashMap<Integer, Integer>();
	}

	public void readIndexLinks() throws IOException {
		int level = 1;
		File f = new File(BTree.indexLinksFileName);
		BufferedReader br = new BufferedReader(new FileReader(f));
		Vector<Integer> links = new Vector<Integer>();

		while (true) {
			String s = br.readLine();
			if (s.compareTo(BTree.EOFString) == 0) {
				break;
			} else {
				indexLinks.put(level, getIndexLinks(s));
				System.out.println(s);
				level++;
			}
		}
		br.close();
		System.out.println(indexLinks);
	}

	private Vector<Integer> getIndexLinks(String s) {
		Vector<Integer> indexLinks = new Vector<Integer>();
		char c = 'a';
		int i = 0;
		while (c != BTree.separateStrings) {
			int d = 0;
			while (true) {
				c = s.charAt(i);
				if (c == BTree.separateSymbols) {
					break;
				}
				d *= 10;
				d += (c - 48);
				i++;
			}
			indexLinks.add(d);
			i++;
			c = s.charAt(i);
		}
		return indexLinks;
	}

	public void getIndexShifts() throws IOException {
		File f = new File(BTree.indexFileName);
		RandomAccessFile r = new RandomAccessFile(f, "rw");
		int counter = 1;
		int pos = 0;
		indexShifts.put(counter, pos);
		System.out.println("length " + f.length());
		while (pos != f.length()) {
			counter++;
			System.out.println("pos " + pos);
			pos += r.readLine().length() + 2;
			pos += r.readLine().length() + 2;
			indexShifts.put(counter, pos);
		}
	}

	private Vector<String> getKeys(String s) {
		Vector<String> keys = new Vector<String>();
		for (int i = 0; i < s.length() / AdressBookGenerator.FIELD_LENGTH; i++) {
			keys.add(s.substring(i * AdressBookGenerator.FIELD_LENGTH, (i + 1)
					* AdressBookGenerator.FIELD_LENGTH));
		}
		return keys;
	}

	/*
	 * public void showRecords(String key1, String key2) throws IOException { if
	 * (key1.compareTo(key2) > 0) { System.out.println("wrong range!"); } else {
	 * File f = new File(BTree.indexFileName); RandomAccessFile ra = new
	 * RandomAccessFile(f, "rw"); Vector<Integer> front = new Vector<Integer>();
	 * Vector<Integer> neededChildren = new Vector<Integer>(); Vector<Integer>
	 * neededKeys = new Vector<Integer>(); front.add(0); while (front.size() !=
	 * 0) { int level = 1; for (int i = 0; i < front.size(); i++) { int pos =
	 * front.get(i); ra.seek(pos); String s = ra.readLine(); // Vector<String>
	 * keys = getKeys(s); for (int j = 0; j < s.length() /
	 * AdressBookGenerator.FIELD_LENGTH; j++) { if (s.substring(j *
	 * AdressBookGenerator.FIELD_LENGTH, (j + 1) *
	 * AdressBookGenerator.FIELD_LENGTH)) {
	 * 
	 * } } } }
	 * 
	 * } }
	 */

}
