package savenko;

import savenko.ast.Program;

public class Controler {

     private IMainView view = null;
     private Program program = null;

     public Controler(IMainView mainv) {
          view = mainv;
     }

     public void interpret() {
          view.printError(null,null);
          try {
               program = new Program(view.getProgramText());
               view.printResult(program.Interpret());
          } catch (RightBracketExpectedException e){
               view.printError("Right bracket expected", e.getPosition());
          } catch (UnexpectedRightBracketException e){
               view.printError("UnexpectedRightBracket", e.getPosition());
          } catch (SemicolonExpectedException e) {
               view.printError("Semicolon expected", e.getPosition());
          } catch (ParserException e) {
               view.printError("Unknown parser error", e.getPosition());
          } catch (NullIDException e) {
               view.printError("Null ID Exception", e.getPosition());
          } catch (InterpreterException e) {
               view.printError("Unknown interpreter error", e.getPosition());
          }
     }

     public void debug() {
     }
}
