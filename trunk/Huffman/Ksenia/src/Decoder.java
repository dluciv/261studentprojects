import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Decoder implements Runnable {
	private Scanner in;

	private void secondMain() {
		int n = in.nextInt();
		String symbol[] = new String[n];
		String code[] = new String[n];
		for (int i = 0; i < n; i++) {
			code[i] = in.next();
			symbol[i] = in.next();
		}
		String str = in.next();
		for (int point = 0; point < str.length();) {
			for (int i = 0; i < n; i++) {
				if (str.substring(point, point + code[i].length()).equals(
						code[i])) {
					System.out.print(symbol[i]);
					point += code[i].length();
					break;
				}
			}
		}

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
		(new Thread(new Decoder())).start();
	}
}
