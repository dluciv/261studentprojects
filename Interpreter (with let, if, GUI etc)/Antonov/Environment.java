
package LexerAndParser;

import java.util.HashMap;

public class Environment {

    private HashMap<Identificator, Value> environment = new HashMap<Identificator, Value>();

    public Value addToEnvironment(Identificator Identificator,Value value){
        return environment.put(Identificator, value);
    }
    public void removeEnvironment(){
        environment.clear();
    }

    public Value getValue(Identificator Identificator){
        for (Identificator i:environment.keySet())
            if (i.getName().equals(Identificator.getName())) return environment.get(i);
        return null;
    }

    public boolean ifIdentificatorExist(Identificator Identificator){
    	 for (Identificator i:environment.keySet())
            if (i.getName().equals(Identificator.getName())) {
                return true;
            }
         return false;
    }

    public void removeIdentificator(Identificator ident){
    	for (Identificator i:environment.keySet())
            if (i.getName().equals(ident.getName())) {
                environment.remove(i);
            }
    }

    
}
