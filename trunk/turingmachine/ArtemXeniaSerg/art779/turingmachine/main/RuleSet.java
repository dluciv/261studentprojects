package art779.turingmachine.main;

public class RuleSet {

	/**
	 * @param args
	 */
	private String v1;
	private String v2;
	private String v3;
	RuleSet(String v1,String v2){
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = null;
	}
	
	RuleSet(String v1,String v2,String v3){
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	public String getState(){
		return this.v1;
	}
	
	public String getSym(){
		return getOperation();
	}
	
	public String getOperation(){
		return this.v2;
	}

	public String getValue(){
		return this.v3;
	}
	
	public String toString()
	{
		String str = "("+ v1 +","+ v2 ;
		if(null!=v3)str += " " +v3;
		str += ")";
		return str;
	}

}
