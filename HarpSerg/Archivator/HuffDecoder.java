package archiver;

import java.io.IOException;

/**
 *
 * @author HarpSerg
 */
public class HuffDecoder {

    private Tree[] nodelist = new Tree[256];
    private final int notexist = 260;
    private final int bufsize = 1000;
    private final int shift = 128;

    /*public HuffDecoder(String infileName, String outfileName) throws IOException
    {
    InputFile fistr1 = new InputFile(infileName);
    OutputFile fostr1 = new OutputFile(outfileName);
    decode(fistr1, fostr1);
    }*/
    public void decode(String infileName, String outfileName) throws IOException {
        InputFile fistr = new InputFile(infileName);
        OutputFile fostr = new OutputFile(outfileName);
        int tail = 8;
        Tree result = new Tree();
        getProbability(fistr);
        result = huffTree();
        Tree localtree = result;
        boolean isthelastbuf = false;
        char direction = '0';
        String codeframe = "";


        while (!isthelastbuf) {
            byte[] buf = fistr.read(bufsize);
            if (bufsize > buf.length) {
                isthelastbuf = true;
            }
            for (int p = 0; p < buf.length - 1; p++) {
                codeframe += getBitsByByte(buf[p]);
                if (isthelastbuf && p == buf.length - 2) {
                    tail = buf[p + 1];
                }
                for (int t = 0; t < tail; t++) {
                    direction = codeframe.charAt(0);
                    codeframe = codeframe.substring(1);
                    localtree = getCharacter(localtree, direction);
                    if (localtree.leaf) {
                        //System.out.print(localtree.character + " ");
                        fostr.write(localtree.character + shift);
                        localtree = result;
                    }
                }
            }
        }
        fistr.clean();
        fostr.clean();
    }

    private Tree getCharacter(Tree tree, char direction) {
        if (direction == '0') {
            return tree.lchild;
        }
        if (direction == '1') {
            return tree.rchild;
        }
        return null;
    }

    public String getBitsByByte(byte b) {
        boolean[] bits = new boolean[8];
        String str = "";
        for (int i = 0; i < bits.length; i++) {
            bits[i] = ((b & (1 << i)) != 0);
            if (bits[i]) {
                str += "1";
            } else {
                str += "0";
            }
        }
        return str;
    }

    public void getProbability(InputFile fistr1) throws IOException {
        int codeslength = fistr1.read();
        int character;
        int weight;
        if (codeslength == 0) {
            codeslength = 256;
        }
        //System.out.print("\n");
        for (int i = 0; i < codeslength; i++) {
            if (i == 128) {
                int u = 0;
                u++;
            }
            character = fistr1.read();
            weight = fistr1.read();
            nodelist[character] = new Tree();
            nodelist[character].character = character;
            nodelist[character].leaf = true;
            nodelist[character].weight = weight;        
        }
    }

    private int getMinProbPlace() {
        int counter = 0;
        int pos = 0;

        for (int ln = 0; ln < 256; ln++) {
            if (nodelist[pos] != null && nodelist[pos].weight != 0) {
                counter++;
                if (nodelist[ln] != null && nodelist[ln].weight != 0) {
                    if (nodelist[pos].weight > nodelist[ln].weight) {
                        pos = ln;
                    }
                }
            } else {
                pos++;
            }
        }
        if (counter == 0) {
            return notexist;
        } else {
            return pos;
        }
    }

    private Tree huffTree() {
        Tree res = new Tree();
        int lpos = 0;
        int rpos = 0;

        lpos = getMinProbPlace();
        if (lpos != notexist) {
            res.weight = nodelist[lpos].weight;
            res.lchild = nodelist[lpos];
            nodelist[lpos] = null;
            rpos = getMinProbPlace();
            if (rpos != notexist) {
                res.weight = res.weight + nodelist[rpos].weight;
                res.rchild = nodelist[rpos];
                nodelist[rpos] = null;
            } else {
                return res.lchild;
            }
            nodelist[lpos] = res;
        } else {
            return res;
        }
        return huffTree();
    }
}
