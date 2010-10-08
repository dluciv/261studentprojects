/*
 * 
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.types;

public class BasicType extends Type {

    private TBasicType type;

    public BasicType(TBasicType type) {
        this.type = type;
    }

    public TBasicType GetType() {
        return type;
    }
}
