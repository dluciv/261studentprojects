import java.io.IOException;

public class Main {	
	public static void main(String[] args) throws IOException {
		String infileName = "D:/test/111.txt";
		String outfileName = "D:/test/111.arc";
		Tree result = new Tree();		
		InputFile fistr = new InputFile(infileName);
		OutputFile fostr = new OutputFile(outfileName);
		Coder newcoder = new Coder();
		
		newcoder.getProbability(fistr);		
		result = newcoder.huffTree();
		newcoder.fillCodeTable(result,"");		
		newcoder.writeCodeToFile(infileName, fostr);		
		
	}	
}


