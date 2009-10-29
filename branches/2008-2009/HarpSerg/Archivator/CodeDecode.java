/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;
import java.io.IOException;

/**
 * @copyright 2009 HarpSerg
 * @author HarpSerg
 */
public interface CodeDecode {
    void code(String infileName, String outfileName) throws IOException;
    void decode(String infileName, String outfileName)throws IOException;
}
