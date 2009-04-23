package Database;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Comparator;

public abstract class AbstractTable<R extends iRecord> implements CommonTableExceptions {
	// предыдущий выбранный индекс
	private int _idx = -1;
	
	// inputstream для select()
	private FileInputStream _inp = null;
	
	// возвращает название файла, где хранится таблица
	abstract String getFilename();
	
	// возвращает длину одной сериализованной записи
	abstract int getRecordLength();
	
	// возаращает экземпляр класса R,
	// полученного путем десериализации массива байтов serialized
	abstract R recordFactoryFromSerialized(byte[] serialized);
	
	// выборка записи по положению в файле
	public R select(int num) throws SelectException {
		String filename = getFilename();
		int record_length = getRecordLength();
		
		try {
			if (_idx == -1 || _idx >= num) {
				if (_inp != null) {
					_inp.close();
				}
				_inp = new FileInputStream(filename);
				_inp.skip(num * record_length);
			} else /*if (_idx != -1 && _idx <= num)*/ {
				_inp.skip((num - _idx - 1) * record_length);
			}
			byte[] serialized = new byte[record_length];
			_idx = num;
			_inp.read(serialized);
			return recordFactoryFromSerialized(serialized);
		} catch (IOException e) {
			throw new SelectException();
		}
	}
	
	// вставка записи
	public void insert(R r) throws InsertException {
		try {
			FileOutputStream out = new FileOutputStream(getFilename(), true);
			out.write(r.serialize());
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new InsertException();
		}
	}
	
	// возвращает количество записей в таблице
	public int count() {
		File f = new File(getFilename());
		return (int) f.length() / getRecordLength();
	}
}