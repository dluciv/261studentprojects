package j2se.g261.eda.automator.representations.minimisation;


/**
* class for couple of integer
* @author dmitriy
*/
public class Couple {
	private int first;	
	private int second;
	
	public Couple (int a,int b ){
		this.first = a;
		this.second = b;
	}
	
	public int getFirst(){
		return this.first;
	}
	
	public int getSecond(){
		return this.second;
	}
	
	public void setFirst(int a){
		this.first = a;
	}
	
	public void setSecond(int b){
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

