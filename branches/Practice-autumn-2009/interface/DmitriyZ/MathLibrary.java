// Dmitriy Zabranskiy g261 (c)2009
// Interface
package math.library;

public interface MathLibrary<T> {

    T add(T num1, T num2);

    T sub(T num1, T num2);

    T next(T num);
}