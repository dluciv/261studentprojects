package savenko;

public class Number implements Expression{
	
	int number;
	
	public Number(int value){
		number = value;
	}
	
	public int Value(){
		return number;
	}
	
	public int calculate() {
        return number;
    }

}
