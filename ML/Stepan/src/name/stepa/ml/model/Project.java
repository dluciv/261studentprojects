package name.stepa.ml.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Project {
    public List<String> files = new ArrayList<String>();

    public String projectPath = "\\";

    public Project(String projectPath) throws IOException {
        this.projectPath = projectPath;
        BufferedReader br = new BufferedReader(new FileReader(new File(projectPath + "hmls.proj")));
        String data = br.readLine();
        while (data != null) {
            files.add(data);
            data = br.readLine();
        }
        br.close();
    }

    public byte[] loadFile(String projectFile) throws IOException {
        File file = new File(projectPath + projectFile);
        FileInputStream in = new FileInputStream(file);
        byte[] res = new byte[(int) file.length()];
        in.read(res);
        in.close();
        return res;
    }

    public String loadText(String projectFile) throws IOException {
        return new String(loadFile(projectFile));
    }

    public void saveFile(String projectFile, byte[] data) throws IOException {
        File file = new File(projectPath + projectFile);
        FileOutputStream out = new FileOutputStream(file);
        out.write(data);
        out.close();
    }

    public void saveText(String projectFile, String text) throws IOException {
        saveFile(projectFile, text.getBytes());
    }

    public void addFile(String fileName) throws IOException {
        files.add(fileName);
        saveFile(fileName, new byte[0]);
        saveProject();
    }

    public void removeFile(String fileName) throws IOException {
        files.remove(fileName);
        new File(projectPath + fileName).delete();
        saveProject();
    }

    public void saveProject() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(projectPath + "hmls.proj")));
        for (String f : files) {
            bw.write(f + "\n");
        }
        bw.close();
    }
}
