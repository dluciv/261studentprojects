package savenko;

public class Number implements Expression{
	
	int number;
	
	Number(int value){
		number = value;
	}
	
	public int Value(){
		return number;
	}

	@Override
	public void print() {
		System.out.print(number);
	}

}
