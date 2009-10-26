package archiver;

import java.io.IOException;

/**
 *
 * @author HarpSerg
 */
public class HuffCode_Decode {

    private Tree[] nodelist = new Tree[256];;
    private String[] codetable = new String[256];
    private final int bufsize = 1000;
    private int[] limweight = new int[256];
    private final int notexist = 260;
    private final int shift = 128;
    
    public void code(String infileName, String outfileName) throws IOException {
        InputFile fistr = new InputFile(infileName);
        OutputFile fostr = new OutputFile(outfileName);
        getProbability(fistr);
        fillCodeTable(huffTree(), "");
        writeCodeToFile(infileName, fostr);
        fistr.clean();
        fostr.clean();
    }

    /**
    @param fistr - file input stream;
     */
    public void decode(String infileName, String outfileName) throws IOException {
        InputFile fistr = new InputFile(infileName);
        OutputFile fostr = new OutputFile(outfileName);
        int tail = 8;
        Tree result = new Tree();
        getProbabilityFromFile(fistr);
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

     public void getProbabilityFromFile(InputFile fistr1) throws IOException {
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

    public void getProbability(InputFile fistr) throws IOException {
        boolean isthelastbuf = false;

        while (!isthelastbuf) {
            byte[] buf = fistr.read(bufsize);
            if (bufsize > buf.length) {
                isthelastbuf = true;
            }
            for (int p = 0; p < buf.length; p++) {
                if (nodelist[buf[p] + shift] != null) {
                    nodelist[buf[p] + shift].weight++;
                } else {
                    nodelist[buf[p] + shift] = new Tree();
                    nodelist[buf[p] + shift].character = buf[p];
                    nodelist[buf[p] + shift].leaf = true;
                    nodelist[buf[p] + shift].weight = 1;
                }
            }
        }
        fistr.clean();
        limitProbability();
    //printProb();

    }

    public void limitProbability() {
        long minweight = 1;
        int curlimweight = 1;
        boolean finish = true;

        while (finish) {
            finish = false;
            if (getMinProbPlace() != notexist) {
                minweight = nodelist[getMinProbPlace()].weight;
                for (int ln = 0; ln < 256; ln++) {
                    if (nodelist[ln] != null && nodelist[ln].weight != 0) {
                        finish = true;
                        if (nodelist[ln].weight == minweight) {
                            nodelist[ln].weight = 0;
                            limweight[ln] = curlimweight;
                        }
                    }
                }
                curlimweight++;
            }
        }
        for (int ln = 0; ln < 256; ln++) {
            if (nodelist[ln] != null) {
                nodelist[ln].weight = limweight[ln];
            //System.out.print(ln + "-" + nodelist[ln].weight + "|");
            }
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

    private void fillCodeTable(Tree tree, String trace) {
        if (tree.leaf) //System.out.print("|" + trace + "  " + (tree.character + shift) + "|");
        {
            codetable[tree.character + shift] = trace;
        }
        if (tree.lchild != null) {
            fillCodeTable(tree.lchild, trace + '0');
        }
        if (tree.rchild != null) {
            fillCodeTable(tree.rchild, trace + '1');
        }
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



    public byte getByteByBits(String bits) {
        int value = 0;
        if (bits != null) {
            for (int i = 0; i < bits.length(); i++) {
                if (bits.charAt(i) == '1') {
                    value = value | (1 << i);
                }
            }
        }
        return (byte) value;
    }

    public void writeCodeToFile(String infileName, OutputFile fostr) throws IOException {
        //printCodeTable();
        //System.out.print("\n");
        byte tail = 0;//the real length of last byte
        boolean isthelastbuf = false;
        String bstring = "";
        InputFile fistr = new InputFile(infileName);
        int counter = 0;

        for (int i = 0; i < 256; i++) {
            if (limweight[i] != 0) {
                counter++;
            }
        }
        fostr.write(counter);
        for (int i = 0; i < 256; i++) {

            if (limweight[i] != 0) {
                fostr.write(i);
                fostr.write(limweight[i]);
            }
        }

        while (!isthelastbuf) {
            byte[] buf = fistr.read(bufsize);
            if (bufsize > buf.length) {
                isthelastbuf = true;
            }
            for (int p = 0; p < buf.length; p++) {
                bstring = bstring + codetable[buf[p] + shift];
                while (bstring.length() >= 8) {
                    //System.out.print(getBitsByByte(getByteByBits(bstring)) + "  ");
                    fostr.write(getByteByBits(bstring));
                    bstring = bstring.substring(8);
                }
            }
        }

        fostr.write(getByteByBits(bstring));
        tail = (byte) (bstring.length());
        fostr.write(tail);					//write the real length of last byte

        fostr.clean();
        fistr.clean();

    }
}
