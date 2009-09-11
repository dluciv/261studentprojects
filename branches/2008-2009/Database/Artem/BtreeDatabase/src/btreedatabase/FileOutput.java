package btreedatabase;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class FileOutput {
	public RandomAccessFile fileStream;

    public FileOutput(String fileName) throws IOException
	{
		File file = new File(fileName);
		if (file.exists())
			file.delete();

		if(file.createNewFile()) {
			fileStream = new RandomAccessFile(file, "rw");
        }
	}
}
