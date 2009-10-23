import java.awt.*;
import java.awt.event.*;

public class HelloWorldFrame extends Frame{
    HelloWorldFrame(String s){
       super(s);
    }
    public void paint(Graphics g){
        g.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 30));
        g.drawString("Hello, My Dear World", 40, 100);
    }

    public static void main(String[] args) {
        Frame f = new HelloWorldFrame("Hello World");
        f.setSize(400, 150);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                System.exit(0);
            }
        });
     }
}
