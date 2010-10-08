/*
 * 
 * Antonov Kirill(c), 2010
 */

package name.kirill.ml.types;


public class CombinedType extends Type{
    private BasicType left;
    private BasicType right;

    public CombinedType(BasicType left, BasicType right){
        this.left = left;
        this.right = right;
    }

    public BasicType GetLeft(){
        return left;
    }

    public BasicType GetRight(){
        return right;
    }
}
