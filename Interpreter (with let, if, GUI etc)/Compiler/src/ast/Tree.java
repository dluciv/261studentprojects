//Lebedev Dmitry 2010 (c)
package ast;

import lebedev.Position;

//import lebedev.Position;

public abstract class Tree {
    private Position position;
    
    public Position getPosition(){
    	return position;
    }
    
    public void setPositon(Position position){
    	this.position = position;
    }
 }
