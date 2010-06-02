package name.stepa.ml.model.interpreter.values;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
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
