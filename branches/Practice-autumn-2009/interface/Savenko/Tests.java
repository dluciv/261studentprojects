package msavenko;

import junit.framework.Assert;

import org.junit.Test;

public class Tests {
	
	@Test(expected = NullPointerException.class)
	public void AskForHelpExceptionExpected(){
		IAskAnswer askForHelp = new AskForHelp();		
		askForHelp.replyUser("n");		
	}
	
	@Test
	public void AskForHelpExceptionNotExpected(){
		IAskAnswer askForHelp = new AskForHelp();		
		askForHelp.replyUser("Y");
	}

}
