package Database;

import java.util.Comparator;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;

public abstract class AbstractOrder<R extends iRecord, T extends AbstractTable<R>, C extends Comparator<R>> implements CommonTableExceptions {
	private int[] _index;
	private boolean _indexloaded;
	
	public AbstractOrder() {
		_index = new int[getTable().count()];
		_indexloaded = false;
	}
	
	// возвращает имя файла, где содержится индекс
	abstract String getFilename();
	
	// возвращает экземпляр класса T
	abstract T getTable();
	
	// возвращает компаратор для сравнения экземпляров класса R
	abstract C getComparator();
	
	// записывает в файл индекс
	public void create() throws IOException, SelectException, IndexException {
		FileOutputStream out = new FileOutputStream(getFilename());
		
		T table = getTable();
		C comparator = getComparator();
		
		int count = table.count();
		
		//System.out.println("Count: "+count);
		
		boolean[] sorted = new boolean[count];
		for (int i=0; i<count; i++) {
			sorted[i] = false;
		}
		
		int cursor = 0;
		R max = null;
		int idx = -1;
		do {
			max = null;
			idx = -1;
			for (int i=0; i<count; i++) {
				R cur = table.select(i);
				if (!sorted[i] && (max == null || comparator.compare(max, cur) >= 0)) {
					max = cur;
					idx = i;
				}
			}
			//System.out.println("Index: "+idx);
			if (idx > -1) {
				_index[cursor] = idx;
				cursor++;
				sorted[idx] = true;
				out.write(intToByteArray(idx));
			}
		} while (idx > -1);
		
		if (cursor != count) {
			throw new IndexException();
		}
		
		_indexloaded = true;
		
		out.flush();
		out.close();
	}
	
	// преобразовывает индекс на число, соответствующее положению в файле
	public int getFilePos(int idx) throws IOException, IndexException {
		if (!_indexloaded) {
			load();
		}
		return _index[idx];
	}
	
	// выбирает один элемент из файла с использованием индекса
	public R select(int idx) throws SelectException, IndexException {
		try {
			if (!_indexloaded) {
				load();
			}
			return getTable().select(_index[idx]);
		} catch (IOException e) {
			throw new SelectException();
		}
	}
	
	// загружает индекс из файла в массив
	public void load() throws IOException, IndexException {
		String filename = getFilename();
		int count = getTable().count();
		
		File f = new File(filename);
		if (f.length() != count * 4) {
			throw new IndexException();
		}
		FileInputStream inp = new FileInputStream(filename);
		byte[] b = new byte[4];
		for (int i=0; i<count; i++) {
			inp.read(b);
			_index[i] = byteArrayToInt(b);
		}
		_indexloaded = true;
	}
	
	// упорядочивание 
	// используется при построении вершины в b-дереве
	// (при добавлении определенного ключа в вершину нужно упорядочить эти ключи)
// 	public int[]  () {
// 	}
	
	public static final byte[] intToByteArray(int value) {
		return new byte[] {
			(byte)(value >>> 24),
			(byte)(value >>> 16),
			(byte)(value >>> 8),
			(byte)value
		};
	}
	
	public static final int byteArrayToInt(byte [] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8) + (b[3] & 0xFF);
	}
	
/*	// поиск минимального индекса большего или равного этой записи
	abstract int findMinIndexGreaterOrEqualThan(String simpleorder_filename, R record, Comparator<R> comp) throws SelectException;
	
	// поиск максимального индекса меньшего или равного этой записи
	abstract int findMaxIndexLessOrEqualThan(String simpleorder_filename, R record, Comparator<R> comp) throws SelectException;*/
	
/*	public int findMinIndexGreaterOrEqualThan(String simpleorder_filename, Film record, Comparator<Film> comp) throws SelectException {
		return findApproximateIntervalForRecord(simpleorder_filename, record, comp)[1];
	}
	
	public int findMaxIndexLessOrEqualThan(String simpleorder_filename, Film record, Comparator<Film> comp) throws SelectException {
		return findApproximateIntervalForRecord(simpleorder_filename, record, comp)[0];
	}
	
	private int[] findApproximateIntervalForRecord(String simpleorder_filename, Film record, Comparator<Film> comp) throws SelectException {
		int count = count();
		int begin = 0;
		int end = count;
		while (end - begin > 1) {
			int i = (int) ((begin + end) / 2);
			Film f = select(i, simpleorder_filename);
			int c = comp.compare(record, f);
			if (c == 0) {
				return new int[] {i, i};
			} else if (c > 0) {
				begin = i;
			} else {
				end = i;
			}
		}
		return new int[] {begin, end};
	}*/
}