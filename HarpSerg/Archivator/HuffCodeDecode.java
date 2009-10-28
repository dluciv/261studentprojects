package archiver;

import java.io.IOException;
import java.util.PriorityQueue;


/**
 *
 * @author HarpSerg
 */
public class HuffCodeDecode implements CodeDecode {

    private Tree[] nodelist = new Tree[256];
    private String[] codetable = new String[256];
    private final int bufsize = 1000;
    private int[] limweight = new int[256];
    private final int notexist = 260;
    private final int shift = 128;
    
    
    @Override
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
    
    @Override
    public void decode(String infileName, String outfileName) throws IOException {
        InputFile fistr = new InputFile(infileName);
        OutputFile fostr = new OutputFile(outfileName);
        int tail = 8;
        getProbabilityFromFile(fistr);
        Tree result = huffTree();
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
                    if (localtree.isLeaf()) {
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
            nodelist[character] = new Tree(character, weight);
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
                    nodelist[buf[p] + shift] = new Tree(buf[p], 1);
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
        TreeComporator comp = new TreeComporator();
        int counter = 0;
        int pos = 0;

        for (int ln = 0; ln < 256; ln++) {
            if ( comp.nodeExists(nodelist[pos]) ) {
                counter++;
                if ( comp.nodeExists(nodelist[ln]) ) {
                    if ( comp.compare(nodelist[pos], nodelist[ln]) == 1 ) {
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
        Tree res = new Tree(0, null, null);
        int lpos = 0;
        int rpos = 0;

        lpos = getMinProbPlace();
        if (lpos != notexist) {
            res.increaseWeight(nodelist[lpos].weight);
            res.setLchild(nodelist[lpos]);
            nodelist[lpos] = null;

            rpos = getMinProbPlace();
            if (rpos != notexist) {
                res.increaseWeight(nodelist[rpos].weight);
                res.setRchild(nodelist[rpos]);
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
        if (tree.isLeaf()) //System.out.print("|" + trace + "  " + (tree.character + shift) + "|");
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
