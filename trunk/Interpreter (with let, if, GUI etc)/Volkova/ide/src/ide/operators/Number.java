/*
 *
 *
 * (c) Volkova Ekatetina
 */
package ide.operators;

import ide.operations.Expression;

public class Number implements Expression {

    int number;

    public Number(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
