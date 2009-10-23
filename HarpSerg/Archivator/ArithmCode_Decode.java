package archiver;

import archiver.Arithm.Unpack.UnpackException;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HarpSerg
 */
public class ArithmCode_Decode {

    HashMap<Byte, Integer> frequency = new HashMap<Byte, Integer>();
    int _size = 0;
    static int cursor = 0;

    public void CalcFrequency(String path) throws FileNotFoundException, IOException {
        InputFile filein = new InputFile(path);
        int readed = 0;
        int size = filein.lenLeft();

        while (readed++ < size) {
            AddByteToFrequency(filein.readOneByte());
            _size++;
        }
    }

    public void AddByteToFrequency(byte a) {
        Byte tmp = new Byte(a);
        if (!frequency.containsKey(tmp)) {
            frequency.put(tmp, 1);
        } else {
            int num = frequency.get(tmp);
            frequency.remove(tmp);
            frequency.put(tmp, num + 1);
        }
    }

    public void CodeArith(String in, String out) throws IOException {
        Arithm ar = new Arithm();
        ar.pack(in, out, _size, frequency);
    }

    public void DecodeArith(String in, String out) throws IOException {
        Arithm ar = new Arithm();
        try {
            ar.unpack(in, out);
        } catch (UnpackException ex) {
            Logger.getLogger(ArithmCode_Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
