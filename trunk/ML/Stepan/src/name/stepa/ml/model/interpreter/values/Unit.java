package name.stepa.ml.model.interpreter.values;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 17:46:16
 * To change this template use File | Settings | File Templates.
 */
public class Unit {

    public static final Unit VALUE = new Unit();

    private Unit() {
    }

    @Override
    public String toString() {
        return "<unit>";
    }
}
