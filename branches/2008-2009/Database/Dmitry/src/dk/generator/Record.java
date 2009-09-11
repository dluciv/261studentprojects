package dk.generator;

public class Record {

	private String name;
	private String surname;
	private String tel;

	public Record(String n, String s, String t) {
		name = n;
		surname = s;
		tel = t;
	}

	@Override
	public String toString() {
		return name + "|" + surname + "|" + tel;
	}
}
