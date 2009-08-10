package Main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import BTree.BList;
import BTree.BTree;
import BTree.Key;
import BTree.Manager;
import Cards.Card;
import Cards.CardManager;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		if (args.length == 0) {
			
			return;
		}
		if (args[0].equals("gen")) {
			PrintWriter big = new PrintWriter("bigtest.in");
			Generate g = new Generate();
			g.generate(big, 100000);
			big.close();
			return ;
		} else if (args[0].equals("load")) {
			BTree tree = new BTree(256, new CardManager());
			BufferedReader br = new BufferedReader(new FileReader(args[1]));
			while (true) {
				String temp = br.readLine();
				if (temp == null) {
					break;
				}
				StringTokenizer st = new StringTokenizer(temp);
				String name = st.nextToken();
				String adr = st.nextToken();
				int number = Integer.parseInt(st.nextToken());
				Card c = new Card(number, name, adr);
				tree.insert(c);				
			}
			tree.stitch();
			FileOutputStream fos = new FileOutputStream(args[2]);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    tree.writeExternal(oos);
		    oos.flush();
		    oos.close();
		    fos.close();
		} else if (args[0].equals("request")){
			FileInputStream fis = new FileInputStream(args[1]);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BTree tree = new BTree(256, new CardManager());
			tree.readExternal(ois);
			
			ois.close();
			fis.close();
			
			InputStreamReader isr;
			if (args[2].equals("stdin")) {
				System.out.println("Loading complete.");
				isr = new InputStreamReader(System.in); 
			} else {
				isr = new FileReader(args[2]);
			}
			BufferedReader br = new BufferedReader(isr);
			PrintStream out;
			if (args[3].equals("stdout")) {
				out = System.out;
			} else {
				out = new PrintStream(args[3]);
			}			
			for (int x = 1;; x++) {
				String temp = br.readLine();
				if (temp == null) {
					break;
				}
				StringTokenizer st = new StringTokenizer(temp);
				String type = st.nextToken();
				if (type.equals("exit")) {
					break;
				}
				String sA = st.nextToken();
				int A = Integer.parseInt(st.nextToken());
				Key kA = new Key(new Card(A, "", sA), tree);
				
				String sB = st.nextToken();
				int B = Integer.parseInt(st.nextToken());
				Key kB = new Key(new Card(B, "", sB), tree);
				
				if (type.equals("count")) {
					out.println("Request " + x + ": count is " + tree.countBetween(kA, kB));					
				} else if (type.equals("listn")) {
					
					int n = Integer.parseInt(st.nextToken());
					BList b = tree.listBetween(kA, kB, n);
					out.print("Request " + x + ": list is {");
					Key cur = null;
					for (cur = b.getStart(); b.hasNext(); cur = b.next()) {
						out.print(cur + ", ");						
					}
					out.print(cur);
					out.println("}");
				} else if (type.equals("list")) {
					BList b = tree.listBetween(kA, kB);
					out.print("Request " + x + ": list is {");
					Key cur = null;
					for (cur = b.getStart(); b.hasNext(); cur = b.next()) {
						out.print(cur + ", ");						
					}
					out.print(cur);
					out.println("}");					
				} else if (type.equals("first")) {
					Key k = tree.find(kA);
					
					if (tree.compare(k, kB) >= 0) {
						k = null;
					}
					out.println("Request " + x + ": first " + k);
				} 
			}
			out.close();
			br.close();
			
		}
		
		/*
		 *     
	* По имееющимся данным, отношению порядка, и построенному индексу необходимо выполнять следующие запросы:
          o Количество карточек, лежащих в указанном диапазоне
          o Список всех карточек, лежащих в указанном диапазоне
          o Список первых n карточек, лежащих в указанном диапазоне
          o Первая карточека, из указанного диапазона
    * При этом запросы должны выполняться эффективно в следующем смысле
          o Запросы выполняются на "разогретой" БД: программа заргужена, все файлы открыты, предварительно выполнено несколько запросов.
          o Поиск 1 карточки должен занимать время сильно меньше 1 секунды.
          o Выборка списка карточек должна занимать время сравнимое с временем запроса 1 карточки.
          o Взятие следующего элемента из списка карточек должна занимать время не хуже, чем время запроса 1 карточки.
    * Замечание: "список", получающийся в результате запроса, следует реализовать как "итератор" в СУБД: т.е. это структура, в которой хранится элемент, и способ найти следующий элемент.
		 */
		        
	}
}
