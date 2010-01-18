/*
 * this is the main class of converter;
 * (c) Yaskov Sergey, 2009;
 */

package converter;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }
}
