import java.util.List;

/**
  * 
  *  Интерфейс к автомату группы TheFirstGroup
  *
  * @author ksenyiacypan
  */
public interface INFA {
	
	public int getStartState();
	public int getFinalState();
	public List<Integer> getNextState(int State, char ch);
	

}
