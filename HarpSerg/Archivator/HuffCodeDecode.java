package archiver;

import java.io.IOException;
import java.util.PriorityQueue;


/**
 *
 * @author HarpSerg
 */
public class HuffCodeDecode implements CodeDecode {

    private final int bufsize = 1000;
    private final int notexist = 260;
    private final int shift = 128;
    
    private int[] limweight = new int[256];

    
    
    @Override
    public void code(String infileName, String outfileName) throws IOException {
        InputFile fistr = new InputFile(infileName);
        OutputFile fostr = new OutputFile(outfileName);        
        writeCodeToFile(infileName, fostr,fillCodeTable(huffTree(limitProbability(getProbability(fistr))), "", new String[256]));
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
        
        Tree result = huffTree(getProbabilityFromFile(fistr));
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

     public Tree[] getProbabilityFromFile(InputFile fistr1) throws IOException {
        Tree[] node = new Tree[256];
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
            node[character] = new Tree(character, weight);
        }
        return node;
    }

    public Tree[] getProbability(InputFile fistr) throws IOException {
        boolean isthelastbuf = false;
        Tree[] node = new Tree[256];

        while (!isthelastbuf) {
            byte[] buf = fistr.read(bufsize);
            if (bufsize > buf.length) {
                isthelastbuf = true;
            }
            for (int p = 0; p < buf.length; p++) {
                if (node[buf[p] + shift] != null) {
                    node[buf[p] + shift].weight++;
                } else {
                    node[buf[p] + shift] = new Tree(buf[p], 1);
                }
            }
        }
        fistr.clean();
        return node;
    }

    public Tree[] limitProbability(Tree[] node) {
       
        TreeComporator comp = new TreeComporator();
        PriorityQueue<Tree> queue = new PriorityQueue<Tree>(256, comp);
        PriorityQueue<Tree> temp = new PriorityQueue<Tree>(256, comp);

        for (int i = 0; i < 256; i++) {
            if (node[i] != null) {
                Tree t = node[i];
                t.character = t.character + shift;
                temp.add(t);
            }
        }

        Tree[] resnode = new Tree[256];
        int end = temp.size();
        for (int i = 0; i < end; i++) {
            Tree t = temp.poll();
            t.weight = i + 1;
            queue.add(t);
            limweight[t.character] = t.weight;
            t.character = t.character - shift;
            resnode[t.character + shift] = t;
        }

        
        
        return resnode;
    }

    private int getMinProbPlace(Tree[] node) {
        TreeComporator comp = new TreeComporator();
        int counter = 0;
        int pos = 0;

        for (int ln = 0; ln < 256; ln++) {
            if ( comp.nodeExists(node[pos]) ) {
                counter++;
                if ( comp.nodeExists(node[ln]) ) {
                    if ( comp.compare(node[pos], node[ln]) == 1 ) {
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

    private Tree huffTree(Tree[] node) {
        Tree res = new Tree(0, null, null);
        int lpos = 0;
        int rpos = 0;

        lpos = getMinProbPlace(node);
        if (lpos != notexist) {
            res.increaseWeight(node[lpos].weight);
            res.setLchild(node[lpos]);
            node[lpos] = null;

            rpos = getMinProbPlace(node);
            if (rpos != notexist) {
                res.increaseWeight(node[rpos].weight);
                res.setRchild(node[rpos]);
                node[rpos] = null;
            } else {
                return res.lchild;
            }
            node[lpos] = res;
        } else {
            return res;
        }
        return huffTree(node);
    }

    private String[] fillCodeTable(Tree tree, String trace, String[] codetable) {
        if (tree.isLeaf()) //System.out.print("|" + trace + "  " + (tree.character + shift) + "|");
        {
            codetable[tree.character + shift] = trace;
        }
        if (tree.lchild != null) {
            fillCodeTable(tree.lchild, trace + '0', codetable);
        }
        if (tree.rchild != null) {
            fillCodeTable(tree.rchild, trace + '1', codetable);
        }
        return codetable;
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

    public void writeCodeToFile(String infileName, OutputFile fostr, String[] codetable) throws IOException {
        //printCodeTable();
        //System.out.print("\n");
        byte tail = 0;//the real length of last byte
        boolean isthelastbuf = false;
        String bstring = "";
        InputFile fistr = new InputFile(infileName);
        int counter = 0;

        for (int i = 0; i < 256; i++) {
            if (codetable[i] != null) {
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
