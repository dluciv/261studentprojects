package dk.main;

import java.io.IOException;

import dk.ariphmeticCoding.AriphmeticDearchivator;
import dk.ariphmeticCoding.AriphmeticArchivator;
import dk.huffman.HuffmanArchivator;
import dk.huffman.HuffmanDearchivator;

/*
 *        http://www.255.ru/index.php?newsid=968
 */

public class Main {
	public static void main(String[] args) throws IOException {
		if (args.length == 3) {
			if (args[0].equals("huf")) {
				if (args[1].equals("a")) {
					HuffmanArchivator h = new HuffmanArchivator(args[2]);
					h.writeFile();
				} else if (args[1].equals("d")) {
					HuffmanDearchivator h = new HuffmanDearchivator(args[2]);
					h.writeFile();
				} else {
					printHelp();
				}
			} else if (args[0].equals("arc")) {
				if (args[1].equals("a")) {
					AriphmeticArchivator a = new AriphmeticArchivator();
					a.writeFile(args[2]);
				} else if (args[1].equals("d")) {
					AriphmeticDearchivator a = new AriphmeticDearchivator(
							args[2]);
					a.writeFile();
				} else {
					printHelp();
				}
			} else {
				printHelp();
			}
		} else {
			printHelp();
		}
	}

	public static void printHelp() {
		System.out.println("Archivator (c)2009 Dmitry Kolyanov");
		System.out
				.println("How to use: java -jar [path to Archivator.jar] <compression method> <command> <input file>");
		System.out
				.println("commands:\n a - to compress input file to output file\n d - to extract input file to output file");
		System.out
				.println("coding methods:\n huf - Huffman coding method\n arc - Arithmetic coding method ");

	}

}
