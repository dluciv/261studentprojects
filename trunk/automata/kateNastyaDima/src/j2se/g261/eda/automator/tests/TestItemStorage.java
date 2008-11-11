package j2se.g261.eda.automator.tests;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author nastya
 */
public class TestItemStorage implements Serializable{
    private Vector<TestItem> storage;

    public static final String MATCHES = "1";
    public static final String NOT_MATCHES = "0";
    
    
    public TestItemStorage() {
        storage = new Vector<TestItem>();
    }

    public void addTest(String pattern, String string, boolean matches){
        storage.add(new TestItem(pattern, string, matches));
    }
    
    public String getPattern(int index){
        return storage.get(index).getPattern();
    }
    
    public String getString(int index){
        return storage.get(index).getString();
    }
    
    public String isMatches(int index){
        return storage.get(index).isMatches() ? MATCHES : NOT_MATCHES;
    }
    
    public void removeTest(int index){
        storage.removeElementAt(index);
    }
    
    public int size(){
        return storage.size();
    }


    public static TestItemStorage concatanate(TestItemStorage[] storages){
        TestItemStorage result = new TestItemStorage();
        for (TestItemStorage testItemStorage : storages) {
            for (int i = 0; i < testItemStorage.storage.size(); i++) {
                result.storage.add(testItemStorage.storage.get(i));
            }
        }
        return result;
    }
}
