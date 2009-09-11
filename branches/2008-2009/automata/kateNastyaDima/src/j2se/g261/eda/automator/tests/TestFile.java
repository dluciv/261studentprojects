/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests;

import java.io.File;

/**
 *
 * @author nastya
 */
public class TestFile<ST> implements Comparable<TestFile> {

    File file;
    ST storage;

    public TestFile(File file, ST storage) {
        this.file = file;
        this.storage = storage;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ST getStorage() {
        return storage;
    }

    public void setStorage(ST storage) {
        this.storage = storage;
    }

    public int compareTo(TestFile o) {
        return file.compareTo(o.getFile());
    }
}
