package Database;

import java.util.Comparator;

public class Film implements iRecord {
	private static final byte NULL_SYMBOL = (byte)'_';
	private String _name; // 90
	private String _year; // 10
	
	private static NameComparator _nameComparator = new NameComparator();
	private static YearComparator _yearComparator = new YearComparator();
	
	public static final int RECORD_LENGTH = 100;
	
	public Film() {
	}
	
	public Film(byte[] serialized) {
		unserialize(serialized);
	}
	
	public Film(String name, String year) {
		_name = name;
		_year = year;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setYear(String year) {
		_year = year;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getYear() {
		return _year;
	}
	
	public String toString() {
		return _name+"|"+_year;
	}
	
	public int getRecordLength() {
		return RECORD_LENGTH;
	}
	
	public byte[] serialize() {
		byte[] out = new byte[RECORD_LENGTH];
		for (int i=0; i<RECORD_LENGTH; i++) {
			out[i] = NULL_SYMBOL;
		}
		
		int cursor = 0;
		
		// в getBytes() заметил странную особенность
		// например, если была строка длиной 15 символов набранная в русской кодировке
		// то размер массива байтов, возвращаемых методом getBytes(), может быть на 1 элемент больше
		System.arraycopy(_name.getBytes(), 0, out, 0, Math.min(_name.length(), 90));
		System.arraycopy(_year.getBytes(), 0, out, 90, Math.min(_year.length(), 10));
		
		return out;
	}
	
	public void unserialize(byte[] data) {
		_name = fromByteArrayToString(data, 0, 90);
		_year = fromByteArrayToString(data, 90, 100);
	}
	
	private static String fromByteArrayToString(byte[] seri, int leftbound, int rightbound) {
		while (rightbound > leftbound && seri[rightbound-1] == NULL_SYMBOL) {
			rightbound--;
		}
		
		byte[] out = new byte[rightbound-leftbound];
		System.arraycopy(seri, leftbound, out, 0, rightbound-leftbound);
		
		return new String(out);
	}
	
	public static NameComparator getNameComparator() {
		return _nameComparator;
	}
	
	public static YearComparator getYearComparator() {
		return _yearComparator;
	}
	
	public static class NameComparator implements Comparator<Film> {
		public int compare(Film a, Film b) {
			return a.getName().compareTo(b.getName());
		}
	}
	
	public static class YearComparator implements Comparator<Film> {
		public int compare(Film a, Film b) {
			return (new Integer(a.getYear().trim())).compareTo(new Integer(b.getYear().trim()));
		}
	}
}