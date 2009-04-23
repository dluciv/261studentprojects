package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Comparator;

public class FilmsTable extends AbstractTable<Film> {
	private static final String FILENAME = "films.table";
	
	private static final int RECORD_LENGTH = (new Film()).getRecordLength();
	
	public FilmsTable() {
	}
	
	public String getFilename() {
		return FILENAME;
	}
	
	public int getRecordLength() {
		return RECORD_LENGTH;
	}
	
	public Film recordFactoryFromSerialized(byte[] serialized) {
		return new Film(serialized);
	}
}