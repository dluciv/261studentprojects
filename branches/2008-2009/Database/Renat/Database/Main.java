package Database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws Exception {
		SimpleTimer timer;
		FilmsTable table = new FilmsTable();
		
		// создаем файл
		timer = new SimpleTimer();
		Generator.main(new String[] {});
		System.out.println(table.count()+" rows inserted in "+timer.getRemainder()+" sec");
		
		int count = table.count();
		System.out.println(count+" rows in table");
		
		// обращаемся к записям в таблице начиная от последней заканчивая первой
		timer = new SimpleTimer();
		for (int i=count-1; i>=0; i--) {
			Film f = table.select(i);
		}
		System.out.println("Accessed in "+timer.getRemainder()+" sec (reverse order)");
		
		// обращаемся в случайном порядке
		timer = new SimpleTimer();
		Random rand = new Random();
		for (int i=0; i<count; i++) {
			Film f = table.select(Math.abs(rand.nextInt()) % count);
		}
		System.out.println("Accessed in "+timer.getRemainder()+" sec (random order)");
		
		// обращаемся к записям в таблице начиная от первой заканчивая последней
		timer = new SimpleTimer();
		for (int i=0; i<count; i++) {
			Film f = table.select(i);
		}
		System.out.println("Accessed in "+timer.getRemainder()+" sec (direct order)");
		
		// создаем простой индекс ("порядок")
		NameOrder order = new NameOrder();
		/*timer = new SimpleTimer();
		order.create();
		System.out.println(count+" rows sorted in "+timer.getRemainder()+" sec");*/
		
		System.out.println("First: "+order.select(0));
		System.out.println("Last: "+order.select(count-1));
	}
}