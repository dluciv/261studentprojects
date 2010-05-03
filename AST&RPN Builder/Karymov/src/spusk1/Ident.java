
package spusk1;

public class Ident extends Expression{
    public String name;

    public Ident(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
