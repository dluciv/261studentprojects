package karymov;

public final class Memento {

    private static Memento memento = new Memento();

    private Memento() {
    }
    private int height;
    private int width;

    public static Memento getMemento() {
        return memento;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void saveProperties(int height,int width){
        
    }
}
 