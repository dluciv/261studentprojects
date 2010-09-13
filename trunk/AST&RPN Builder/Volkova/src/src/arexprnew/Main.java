/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

//import java.util.Scanner;
import arexprnew.Equal;
import java.util.Scanner;

//import java.awt.*;
//import java.awt.event.*;
//import java.applet.*;
/**
 *
 * @author kate
 */
public class Main {

    public static void main(String[] args) throws ParserException, InterException {

        String input = "print(1+3)";
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(new Parser(new Lexer(input)).getSq());

    }
}
    /*
    public static void main(String[] args) {




    }
    //      System.out.println("Hello");

    
    /*  public static void main(String[] args) throws Exception {

    Scanner input = new Scanner(System.in);

    System.out.println("Print a for quit");

    String exp = input.nextLine();

    while (!"a".equals(exp)) {
    Interpreter interpreter = new Interpreter();
    try {
    System.out.println(interpreter.interpret(new Parser(new Lexer(exp)).getSq()));
    //        System.out.println("result = " + interpreter.interpret(new Parser(new Lexer(exp)).getSq()));
    } catch (ParserException ex) {
    System.out.println("Error! " + ex);
    }
    exp = input.nextLine();
    }

    }
    /**
     * @param args the command line arguments
     *
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.println("Print a for quit");

    String exp = input.nextLine();

    while (!"a".equals(exp)) {
    Parser parser = new Parser(exp);
    try {
    System.out.println(parser.parse());
    System.out.println("result = " + parser.result());
    } catch (ParseException ex) {
    System.out.println("Error! " + ex);
    }
    exp = input.nextLine();
    }


    }

    public class Calc extends Applet implements ActionListener {

    TextField exprText, resultText;
    Parser parser;
    String exp;


    @Override
    public void init() {
    Label heading = new Label("Expression calculator", Label.CENTER);

    Label exprLab = new Label("Expression", Label.CENTER);
    Label resultLab = new Label("Result", Label.CENTER);
    exprText = new TextField(24);
    resultText = new TextField(24);

    resultText.setEditable(false); // field of results only for map

    add(heading);
    add(exprLab);
    add(exprText);
    add(resultLab);
    add(resultText);

    exprText.addActionListener(this);

    parser = new Parser(exp);
    }

    //Enter
    public void actionPerformed(ActiveEvent a) {
    repaint();
    }

    @Override
    public void paint(Graphics g) {
    int res = 0;
    String expres = exprText.getText();

    try {
    if (expres.length() != 0) {
    res = parser.result();
    }

    exprText.setText("");

    resultText.setText(Integer.toString(res));
    showStatus("");
    } catch (ParseException e) {
    showStatus(e.toString());
    resultText.setText("");
    }

    }

    public void actionPerformed(ActionEvent arg0) {
    throw new UnsupportedOperationException("Not supported yet.");
    }
    }
     */
