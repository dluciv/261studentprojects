package archiver;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author HarpSerg
 */
public class Dictionary {

    public HashMap<Byte, Bits> direct_dictionary = new HashMap<Byte, Bits>();
    public HashMap<ArrayList<Boolean>, Byte> revert_dictionary = new HashMap<ArrayList<Boolean>, Byte>();
    HashSet<ArrayList<Boolean>> alphabet = new HashSet<ArrayList<Boolean>>();

    public void TranslateFromOriginal(InputFile str, OutputFile res) throws IOException {
        Bits tmp = new Bits();
        int size = str.lenLeft();
        for (int i = 0; i < size; i++) {
            Byte tmpr = str.readOneByte();
            Bits extern = (Bits) direct_dictionary.get(tmpr).clone();
            tmp.Enqueue(extern);
            if (tmp.str.size() != 0) {
                res.write(tmp.str.get(0));
                tmp.str.remove(0);
            }
        }
        byte cursor = tmp.cursor;
        tmp.ForseFormByte();
        res.write(tmp.str.get(0));
        res.write(cursor);
    }

    public ArrayList<Byte> MakeDepthRLE(HashMap<Byte, Byte> depth) {
        ArrayList<Byte> res = new ArrayList<Byte>();

        Integer pass = (int) Byte.MIN_VALUE;
        int length;
        int currentLength;

        if (!depth.keySet().contains(Byte.MIN_VALUE)) {
            length = Byte.MIN_VALUE;
        } else {
            length = depth.get(Byte.MIN_VALUE).byteValue() + 1;
        }

        for (int i = Byte.MIN_VALUE + 1; i <= Byte.MAX_VALUE; i++) {
            if (!depth.keySet().contains((byte) i)) {
                currentLength = Byte.MIN_VALUE;
            } else {
                currentLength = depth.get((byte) i).byteValue() + 1;
            }

            if (length == currentLength) {
                pass++;
                continue;
            } else {
                res.add((byte) length);
                res.add(pass.byteValue());
                length = currentLength;
                pass = (int) Byte.MIN_VALUE;
            }
        }

        res.add((byte) length);
        res.add((byte) (pass.byteValue()));

        return res;
    }

    public static void WriteFreqRLE(HashMap<Byte, Integer> freq, OutputFile stream) throws IOException {
        Integer pass = (int) Byte.MIN_VALUE;
        int length;
        int currentLength;

        if (!freq.keySet().contains(Byte.MIN_VALUE)) {
            length = Byte.MIN_VALUE;
        } else {
            length = freq.get(Byte.MIN_VALUE);
        }

        for (int i = Byte.MIN_VALUE + 1; i <= Byte.MAX_VALUE; i++) {
            if (!freq.keySet().contains((byte) i)) {
                currentLength = Byte.MIN_VALUE;
            } else {
                currentLength = freq.get((byte) i);
            }

            if (length == currentLength) {
                pass++;
                continue;
            } else {
                stream.write(Bits.intToByteArray(length));
                stream.write(pass.byteValue());
                length = currentLength;
                pass = (int) Byte.MIN_VALUE;
            }
        }

        stream.write(Bits.intToByteArray(length));
        stream.write((pass.byteValue()));
    }

    public static HashMap<Byte, Byte> RestoreDepth(InputFile tmp) throws IOException {
        HashMap<Byte, Byte> res = new HashMap<Byte, Byte>();
        int pass = Byte.MIN_VALUE;
        while (pass <= Byte.MAX_VALUE) {
            int temp = tmp.readOneByte();
            int tmpr = tmp.readOneByte();
            int passtmp = tmpr - Byte.MIN_VALUE + 1;
            ArithmCode_Decode.cursor += 2;
            if (temp == Byte.MIN_VALUE) {
                pass += passtmp;
                continue;
            }
            AddNewDepthValues(res, (byte) pass, temp, tmpr);
            pass += passtmp;
        }
        return res;
    }

    public static HashMap<Byte, Integer> RestoreFreq(InputFile input) throws IOException {
        byte[] bb = new byte[4];

        HashMap<Byte, Integer> res = new HashMap<Byte, Integer>();
        int pass = Byte.MIN_VALUE;
        while (pass <= Byte.MAX_VALUE) {
            input.read(bb);
            int temp = Bits.byteArrayToInt(bb);
            int tmpr = input.readOneByte();
            int passtmp = tmpr - Byte.MIN_VALUE + 1;
            if (temp == Byte.MIN_VALUE) {
                pass += passtmp;
                continue;
            }
            AddNewFreqValues(res, (byte) pass, temp, tmpr);
            pass += passtmp;
        }
        return res;
    }

    private static void AddNewFreqValues(HashMap<Byte, Integer> frequency, byte j, int val, int pass) {
        for (int i = 0; i < pass - Byte.MIN_VALUE + 1; i++) {
            Integer tmp = (int) (val);
            Byte tmpr = (byte) (j + i);
            frequency.put(tmpr, tmp);
        }
    }

    private static void AddNewDepthValues(HashMap<Byte, Byte> depth, byte j, int val, int pass) {
        for (int i = 0; i < pass - Byte.MIN_VALUE + 1; i++) {
            Integer tmp = (int) (val - 1);
            Byte tmpr = (byte) (j + i);
            depth.put(tmpr, tmp.byteValue());
        }
    }

    public void MakeAlphabet() {
        for (ArrayList<Boolean> tmp : revert_dictionary.keySet()) {
            alphabet.add(tmp);
        }
    }

    public ArrayList<Byte> TranslateToOriginal(ArrayList<Boolean> tmpr, byte tail) {
        ArrayList<Boolean> prev = new ArrayList<Boolean>();
        ArrayList<Byte> res = new ArrayList<Byte>();
        int cur = 0;
        HashSet<ArrayList<Boolean>> cur_alphabet = (HashSet<ArrayList<Boolean>>) alphabet.clone();
        HashSet<ArrayList<Boolean>> toDelete = new HashSet<ArrayList<Boolean>>();

        int len = tmpr.size();
        if (tail != 0) {
            len += tail - 8;
        }

        for (int i = 0; i < len; i++) {
            cur_alphabet.removeAll(toDelete);
            toDelete.clear();
            prev.add(tmpr.get(i));
            for (ArrayList<Boolean> tmp : cur_alphabet) {
                if (!tmp.get(prev.size() - 1).equals(prev.get(prev.size() - 1))) {
                    toDelete.add(tmp);
                    continue;
                }
                if (tmp.equals(prev)) {
                    cur = i;
                    res.add(FindByBitStr(prev));
                    cur_alphabet = (HashSet<ArrayList<Boolean>>) alphabet.clone();
                    prev.clear();
                    break;
                }
            }
        }


        if (cur != 0) {
            tmpr.clear();
            for (Boolean smth : prev) {
                tmpr.add(smth);
            }
        }

        return res;
    }

    private Byte FindByBitStr(ArrayList<Boolean> prev) {
        for (ArrayList<Boolean> tmp : revert_dictionary.keySet()) {
            if (tmp.equals(prev)) {
                Byte res = revert_dictionary.get(tmp);
                return res;
            }
        }
        return null;
    }
}
