package converter;

public class Currency {
	
	private String type;
	private double amount;
	
	public Currency (String type, double amount){
		this.type = type;
		this.amount = amount;
	}
	
	public double getAmount(){
		return amount;
	}
	public String getType(){
		return type;
	}
}
