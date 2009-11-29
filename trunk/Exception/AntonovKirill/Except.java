//(C), 2009 Antonov Kirill
// The Simple Exception

package exception;

class SimpleExc{
    public static void main(String[] args){
        try {
            int InPutNumber = Integer.parseInt(args[0]);
            System.out.println("After parserInt()");
            System.out.println("10/InPutNumber = " + (10/InPutNumber));
            System.out.println("After Result Output");
        } catch(ArithmeticException ae) {
            System.out.println("From ArithmeticException catch:" + ae);
        } catch(ArrayIndexOutOfBoundsException arre) {
            System.out.println("From ArrayException catch:" + arre);
        } finally {
            System.out.println("From finally");
        }
        System.out.println("After all action");
    }
}