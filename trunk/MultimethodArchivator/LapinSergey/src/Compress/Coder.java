package Compress;

import Bits.Bits;
import Compress.Arithm.Unpack.UnpackException;
import Compress.Haffman.Depth;
import Compress.Haffman.Tree;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lapin Sergey
 */
public class Coder {
    HashMap<Byte, Integer> frequency = new HashMap<Byte, Integer>();
    int _size = 0;
    static int cursor = 0;

    public void CalcFrequency(String path) throws FileNotFoundException, IOException {
        FileInput filein = new FileInput(path);
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
    
    
    public void CodeHaffman(String in, String out) throws IOException, CloneNotSupportedException{        
        Dictionary dic;

        Tree a = new Tree(frequency);
        a.MakeCanonical();
        System.out.print(a);
//      Составить словарь
        dic = a.CalcDictionary(0);

        FileOutput output = new FileOutput(out);
//      закодировать словарь в РЛЕ
        Depth dp = a.CalcDepth();
        System.out.println(dp);
        for(Byte tmp : dic.MakeDepthRLE(dp.date)){
            output.write(tmp);
        }
// перевести
        dic.TranslateFromOriginal(new FileInput(in), output);
    }

    public void DecodeHaffman(String in, String out) throws IOException, CloneNotSupportedException{
        FileOutput output = new FileOutput(out);

        FileInput filein = new FileInput(in);
        int size = filein.lenLeft();

        Depth depth = new Depth(Dictionary.RestoreDepth(filein));
        System.out.print(depth);

        Tree a = new Tree(depth);
        System.out.print(a);
        Dictionary dic = a.CalcDictionary(1);
        dic.MakeAlphabet();

        ArrayList<Boolean> tmpr = new ArrayList<Boolean>();

        ArrayList<Byte> res = new ArrayList<Byte>();

        while(cursor++ < size - 2){
            tmpr.addAll(Bits.DecodeExternalByte(filein.readOneByte()));
            res = dic.TranslateToOriginal(tmpr, (byte)0);
            if(!res.isEmpty()){
                for(int i = 0; i < res.size(); i++){
                    output.write(res.get(i));
                }
            }
        }

        tmpr.addAll(Bits.DecodeExternalByte(filein.readOneByte()));
        byte cur = filein.readOneByte();
        if(cur == 0){
            return;
        }
        res = dic.TranslateToOriginal(tmpr, cur);
        if(!res.isEmpty()){
            for(int i = 0; i < res.size(); i++){
                output.write(res.get(i));
            }
        }
    }

    public void CodeArith(String in, String out) throws IOException{
        Arithm ar = new Arithm();
		ar.pack(in, out, _size, frequency);
    }

    public void DecodeArith(String in, String out) throws IOException{
        try {
            Arithm ar = new Arithm();
            ar.unpack(in, out);
        } catch (UnpackException ex) {
            Logger.getLogger(Coder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
