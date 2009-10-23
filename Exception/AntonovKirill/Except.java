//(C), 2009 Antonov Kirill
// The Simple Exception

package exception;

class SimpleExc{
    public static void main(String[] args){
        try{
            int n = Integer.parseInt(args[0]);
            System.out.println("After parserInt()");
            System.out.println("10/n = " + (10/n));
            System.out.println("After Result Output");
        }catch(ArithmeticException ae){
            System.out.println("From ArithmeticException catch:" + ae);
        }catch(ArrayIndexOutOfBoundsException arre){
            System.out.println("From ArrayException catch:" + arre);
        }finally{
            System.out.println("From finally");
        }
        System.out.println("After all action");
    }
}