/*
 * befin <sequense>  end
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.ast;

public class Begin extends Expression {

    private Sequence seq;

    public Begin(Sequence new_seq) {
        seq = new_seq;
    }

    public Sequence getSequence() {
        return seq;
    }
}
