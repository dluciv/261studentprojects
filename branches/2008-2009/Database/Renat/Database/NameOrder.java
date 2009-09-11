package Database;

import java.util.Comparator;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * Упорядочивание записей с нужным компаратором
 * Можно будет потом с помощью бинарного (?) поиска искать нужные индексы в неупорядоченной таблице
 * Но вообще этот бинарный поиск нужен только для сравнения по времени с поиском через b-деревья
 * Размер в памяти должен получаться такой, чтобы полностью туда уместиться
 * Без этого никак, потому что при построении более сложного индекса с использованием b-деревьев,
 * нужно будет очень быстро выстраивать в правильный порядок ключи в определенной вершине
 * если этот (SimpleOrder) порядок не держать в памяти, то будет очень много обращений к жесткому диску
 * 
 * @todo реализовать более медленный способ без держания в памяти всего "порядка"
 * @author Renat Akhmedyanov
 */
public class NameOrder extends AbstractOrder<Film, FilmsTable, Film.NameComparator> {
	private static final String FILENAME = "films.name.order";
	
	private static final FilmsTable TABLE = new FilmsTable();
	
	private static final Film.NameComparator COMPARATOR = Film.getNameComparator();
	
	public String getFilename() {
		return FILENAME;
	}
	
	public FilmsTable getTable() {
		return TABLE;
	}
	
	public Film.NameComparator getComparator() {
		return COMPARATOR;
	}
}