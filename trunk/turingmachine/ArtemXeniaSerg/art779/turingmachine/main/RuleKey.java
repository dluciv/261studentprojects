package art779.turingmachine.main;

public class RuleKey {
	
	private String state;
	private String sym;
	
	RuleKey(String v1,String v2){
		this.state = v1;
		this.sym = v2;
	}
	
	public String getState(){
		return this.state;
	}
	
	public String getSym(){
		return this.sym;
	}
	
	public String toString()
	{
		String str = "("+ state +")"+ sym ;
		return str;
	}
}
