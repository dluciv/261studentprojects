//Lebedev Dmitry 2010 (c)
package AST;

import javax.sound.midi.Sequence;

public class Begin extends Exception {

    private Sequence seq;

    public Begin(Sequence seq) {
        this.seq = seq;
    }
    
}
