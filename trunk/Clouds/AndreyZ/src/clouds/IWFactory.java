/**
 * Factory Interface
 * @author Zubrilin Andrey
 * group 261 (c) 2009
 */
package clouds;

public interface IWFactory {

    IWind wind();

    IDaylight daylight();

    ILuminary luminary();

}
