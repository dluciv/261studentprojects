package huffman;
import java.io.IOException;

public class Main {
	static String infileName = "D:/test/333.txt";
	static String arcfileName = "D:/test/333.arc";
	static String decodedfileName = "D:/test/decoded.txt";

    public static void main(String[] args) throws IOException {
        Coder coder = new Coder(infileName,arcfileName);
		Decoder decoder = new Decoder(arcfileName,decodedfileName);

	}
}
