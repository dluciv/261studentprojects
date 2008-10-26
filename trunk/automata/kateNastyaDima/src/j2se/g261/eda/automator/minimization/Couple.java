package j2se.g261.eda.automator.minimization;

/**
*
* @author dmitriy
*/
public class Couple {
	private char first;	
	private char second;
	
	public Couple (char a,char b ){
		this.first = a;
		this.second = b;
	}
	
	public char getFirst(){
		return this.first;
	}
	
	public char getSecond(){
		return this.second;
	}
	
	public void setFirst(char a){
		this.first = a;
	}
	
	public void setSecond(char b){
		this.second = b;
	}

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Couple other = (Couple) obj;
        if (this.first != other.first) {
            return false;
        }
        if (this.second != other.second) {
            return false;
        }
        return true;
    }


        
        
	
}

