package name.stepa.ml.model;

import java.io.IOException;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Environment {

    private static Environment instance = null;

    static {
        clear();
    }

    public static void clear() {
        instance = new Environment();
    }

    public static Environment get() {
        return instance;
    }

    public void loadProject(String projectPath) throws IOException {
        project = new Project(projectPath);
    }

    public Project project;
    private String selectedFile;
    public Interpreter interpreter;

    private Environment() {
        project = null;
        selectedFile = null;
        interpreter = new Interpreter();
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setInterpretationProgram(String data) throws Exception {
        interpreter.setProgram(data);
    }
}
