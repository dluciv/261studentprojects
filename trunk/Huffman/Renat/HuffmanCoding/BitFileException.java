/**
 * @author Renat Akhmedyanov
 */

package HuffmanCoding;

public class BitFileException extends Exception
{
    String message;

    BitFileException(String message) {
        this.message = message;
    }
    
    BitFileException() {
        this.message = "";
    }
}