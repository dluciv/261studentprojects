/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Compress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Administrator
 */
public class Main {
    public static final int EOF = 256;

    static String workspace = new String("C:\\Documents and Settings\\Administrator\\My Documents\\NetBeansProjects\\Compress\\test\\");

    static String sourse_haffman = workspace + new String("Хаффман - изначальный.txt");
    static String packed_haffman = workspace + new String("Хаффман - кодированный.txt");
    static String unpacked_haffman = workspace + new String("Хаффман - декодированный.txt");

    static String sourse_arithm = workspace + new String("Арифметическое коддирование - изначальный.txt");
    static String packed_arithm = workspace + new String("Арифметическое коддирование - кодированный.txt");
    static String unpacked_arithm = workspace + new String("Арифметическое коддирование - декодированный.txt");

    public static void main(String[] args) throws IOException, CloneNotSupportedException {



//         ArrayList<Byte> alphabet = new ArrayList<Byte>();
//         alphabet.add((byte)-128);
//         alphabet.add((byte)127);
//         alphabet.add((byte)-126);

//        MakeFileWithSymbolsDoubling(sourse_haffman, MakeUniformAlphabet(), 3);
//        MakeFileWithSymbolsDoubling(sourse_arithm, MakeUniformAlphabet(), 3);
        MakeRandomizeFile(sourse_haffman, 300);
//        MakeRandomizeFile(sourse_arithm, 2);
        MakeRandomizeFile(sourse_arithm, 30000);

//        MakeFileOnAlphabet(sourse_arithm, alphabet, 00);

        Coder input_haffman = new Coder();
        Coder output_haffman = new Coder();

        input_haffman.CalcFrequency(sourse_haffman);
        input_haffman.CodeHaffman(sourse_haffman, packed_haffman);
        output_haffman.DecodeHaffman(packed_haffman, unpacked_haffman);

        System.out.println(CompareFiles(sourse_haffman, unpacked_haffman));

        Coder input_arithm = new Coder();
        Coder output_arithm = new Coder();

        input_arithm.CalcFrequency(sourse_arithm);

        input_arithm.CodeArith(sourse_arithm, packed_arithm);
        output_arithm.DecodeArith(packed_arithm, unpacked_arithm);

        System.out.println(CompareFiles(sourse_arithm, unpacked_arithm));
    }

    public static void MakeRandomizeFile(String filename, int sizeinbytes) throws IOException
    {
        FileOutput fileout = new FileOutput(filename);
        while(sizeinbytes-- != 0){
            Double rand_d = Math.random();
            Integer rand = Math.abs((int)rand_d.doubleToLongBits(rand_d)) % 256 + Byte.MIN_VALUE;
            fileout.write(rand.byteValue());
        }
        fileout.close();
    }

    public static void MakeFileWithSymbolsDoubling(String filename, ArrayList<Byte> alphabet, int degree) throws IOException
    {
        FileOutput fileout = new FileOutput(filename);
        int j = 0;
        int prevdegree = 0;
        while(j <= degree){            
            prevdegree = (int) Math.pow(2, j);
            for(int k = 0; k < prevdegree; k++){
                Integer rand = (j) % alphabet.size();
                fileout.write(alphabet.get(rand));
            }

            j++;
        }
        fileout.close();
    }

    public static void MakeFileOnAlphabetRandomly(String filename, ArrayList<Byte> alphabet, int sizeinbytes) throws IOException
    {
        FileOutput fileout = new FileOutput(filename);
        while(sizeinbytes-- != 0){
            Double rand_d = Math.random();
            Integer rand = Math.abs((int)rand_d.doubleToLongBits(rand_d)) % alphabet.size();
            fileout.write(alphabet.get(rand));
        }
        fileout.close();
    }

    public static void MakeFileOnAlphabet(String filename, ArrayList<Byte> alphabet , int sizeinbytes) throws IOException
    {
        FileOutput fileout = new FileOutput(filename);
        int i = 0;
        while(sizeinbytes-- != 0){
            Integer rand = i % alphabet.size();
            fileout.write(alphabet.get(rand));
            i++;
        }
        fileout.close();
    }

    public static ArrayList<Byte> MakeUniformAlphabet()
    {
        ArrayList<Byte> res = new ArrayList<Byte>();
        for(int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++){
            res.add((byte)i);
        }
        return res;
    }

    public static boolean CompareFiles(String first, String second) throws IOException
    {
        FileInput filein = new FileInput(first);
        FileInput fileout = new FileInput(second);

        if(filein.lenLeft() != fileout.lenLeft()){
            return false;
        }
        int size = filein.lenLeft();
        while(size-- != 0){
            Byte frst = filein.readOneByte();
            Byte scnd = fileout.readOneByte();
            if(frst != scnd){
                return false;
            }
        }
        return true;
    }
}
