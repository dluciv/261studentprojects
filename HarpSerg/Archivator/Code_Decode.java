/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package archiver;
import java.io.IOException;

/**
 *
 * @author HarpSerg
 */
public interface Code_Decode {
    void code(String infileName, String outfileName) throws IOException;
    void decode(String infileName, String outfileName)throws IOException;
}
