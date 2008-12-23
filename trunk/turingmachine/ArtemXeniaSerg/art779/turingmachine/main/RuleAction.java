/*
 * @author art779j
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

public class RuleAction {
	
	private String state;
	private Action action;
	private String param = null;
	
	RuleAction(String v1,Action v2){
		this.state = v1;
		this.action = v2;
	}
	
	RuleAction(String v1,Action v2,String v3){
		this.state = v1;
		this.action = v2;
		if(v3!="")this.param = v3;
	}
	
	public String getState(){
		return this.state;
	}
	
	public Action getAction(){
		return this.action;
	}

	public String getParam(){
		return this.param;
	}
	
	public String toString()
	{
		String str = "("+ state +")"+ action ;
		if(null!=param)str += "\"" + param + "\"";
		return str;
	}
}
