package savenko;

import savenko.ast.Position;

public interface IMainView {
	
	public void printError(String error, Position position);
	
	public String getProgramText();
	
	public void setProgramText(String program);
	
	public void printResult(String result);

}
