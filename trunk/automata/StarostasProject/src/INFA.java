import java.util.List;

/**
  * 
  *  ��������� � �������� ������ TheFirstGroup
  *
  * @author ksenyiacypan
  */
public interface INFA {
	
	public int getStartState();
	public int getFinalState();
	public List<Integer> getNextState(int State, char ch);
	

}
