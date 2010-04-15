package name.stepa.turing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class MachineFactory {

    private static MachineRule[] loadRules(String rules) {
        ArrayList<MachineRule> res = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(rules));
            String ruleData = br.readLine();
            while (ruleData != null) {
                String[] data = ruleData.split(" ");
                Move mv = Move.NONE;
                if (data[4].equals("R"))
                    mv = Move.RIGHT;
                if (data[4].equals("L"))
                    mv = Move.LEFT;
                if (data[4].equals("H"))
                    mv = Move.STOP;

                MachineRule rule = new MachineRule(Integer.parseInt(data[0]), data[1].charAt(0),
                        Integer.parseInt(data[2]), data[3].charAt(0), mv);

                res.add(rule);
                ruleData = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toArray(new MachineRule[0]);
    }

    public static Machine createMachine(String state, String fileName) {
        return new Machine(0, 0, state, loadRules(fileName));
    }
}
