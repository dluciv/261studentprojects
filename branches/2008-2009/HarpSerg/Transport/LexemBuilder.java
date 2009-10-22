
package transport;

import java.io.IOException;

/**
 *
 * @author Artem
 */
public class LexemBuilder {
    byte[] buf;
    int curpos = 0;
    LexemBuilder(String filename) throws IOException{
        int bufsize = 1000;
        InputFile fistr = new InputFile(filename);
        buf = fistr.read(bufsize);
    }

    public int getNextNumber(){
        if(curpos >= buf.length-1) {
            return -2;
        }
        int tempres = 0;
        int result = 0;
        int grade = 1;
        
        tempres = buf[curpos]-48;
        
        if (tempres == -35) {
            curpos++;
            curpos++;
            return -1;
        }
        while ( tempres == -16) {
            curpos++;
             tempres = buf[curpos]-48;
        }
        
        while ( tempres != -16 && tempres != -35) {
            

            result = result*grade + tempres;
            grade = grade*10;
            if ( curpos >= buf.length-1) {
                return result;
            }
            curpos++;
            tempres = buf[curpos]-48;
            if ( tempres == -35) {
                return result;
            }
        }
        if ( tempres == -35) {
            curpos++;
            curpos++;
            return -1;
        }
        
        return  result;
    }
}
