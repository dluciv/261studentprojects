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

    public static void loadProject(String projectPath) throws IOException {
        instance = new Environment(projectPath);
    }

    public Project project;

    private Environment() {
        project = null;
    }

    private Environment(String projectPath) throws IOException {
        project = new Project(projectPath);
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public String getSelectedFile() {
        return selectedFile;
    }

    private String selectedFile = null;
}
