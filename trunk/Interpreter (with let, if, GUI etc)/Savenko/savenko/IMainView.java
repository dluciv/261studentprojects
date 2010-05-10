package savenko;

import savenko.ast.Position;

public interface IMainView {
	
	public void printError(String error, Position position);
	
	public String getProgramText();
	
	public void setProgramText(String program);
	
	public void printResult(String result);
	
	public void resetConsole();
	
	public void colorKeyword(int start_ind, int end_index);

        public void highlightText(int start_ind, int end_index);

        public void setProgressBarText(String progress);

        public void resetProgressBar();
}
