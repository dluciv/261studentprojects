import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Coder implements Runnable {
	private Scanner in;

	private void secondMain() {
		HashMap<Character, String> TableCode = new HashMap<Character, String>();
		String str = in.next();
		HashMap<Character, Integer> Fin = new HashMap<Character, Integer>();

		for (char i : str.toCharArray()) {

			if (!Fin.containsKey(i)) {
				Fin.put(i, 1);
			} else {
				int a = 1 + Fin.get(i);
				Fin.put(i, a);
			}

		}
		PriorityQueue<Node> que = new PriorityQueue<Node>();
		for (Map.Entry<Character, Integer> x : Fin.entrySet()) {
			Node y = new Node(x.getValue(), x.getKey());
			que.add(y);
		}
		while (que.size() > 1) {
			Node i = que.poll();
			Node k = que.poll();
			Node y = new Node(i, k);
			que.add(y);
		}
	}
	public  void TableCode(Node i, String stroca){
		
		TableCode(i.left, stroca+1);
	}

	@Override
	public void run() {
		try {
			in = new Scanner(new FileReader("input.txt"));
			secondMain();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		(new Thread(new Coder())).start();
	}
}
