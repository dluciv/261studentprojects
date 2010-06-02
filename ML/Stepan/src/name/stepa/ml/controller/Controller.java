package name.stepa.ml.controller;

import name.stepa.ml.About;
import name.stepa.ml.EditorWindow;
import name.stepa.ml.IMainWindow;
import name.stepa.ml.model.Environment;
import name.stepa.ml.model.Project;
import name.stepa.ml.model.interpreter.IInterpreterStateListener;
import name.stepa.ml.model.interpreter.IO;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.IOException;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class Controller {
    public boolean isSaved = false;

    IMainWindow window;

    public Controller(final IMainWindow window) {
        this.window = window;
        IO.setOutputInterface(new IO.IOutput() {
            public void println(String s) {
                window.writeToLog(s);
            }
        });

        Environment.get().interpreter.setStateListener(new IInterpreterStateListener() {
            public void onLineChanged(int start, int last) {
                window.highlight(start, last);
            }

            public void onExecutionStopped() {
                window.disableInterpretControls();
                window.beep();
                window.removeHighlight();
                window.writeToLog("Execution stopped.");
            }

            public void onExecutionStarted() {
                window.writeToLog("Execution started.");
            }
        });
    }

    public void onTextChanged() {
        if (isSaved) {
            isSaved = false;
            try {
                Environment.get().setInterpretationProgram(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProject() {
        Project P = Environment.get().project;
        window.setProject(P);
        if (P != null)
            window.setStatus("Project loaded...");
        else
            window.setStatus("Please, load project...");
    }

    public void saveProgram() {
        if (!isSaved) {
            Environment env = Environment.get();
            if (env.getSelectedFile() != null) {
                try {
                    env.project.saveText(env.getSelectedFile(), window.getProgramText());
                    window.setStatus("File " + env.getSelectedFile() + " saved...");
                    isSaved = true;
                } catch (IOException e1) {
                    e1.printStackTrace();
                    window.setStatus("Error while saving " + env.getSelectedFile() + "...");
                }
            } else {
                window.beep();
                window.setStatus("Error! Nothing to save!");
            }
        }
    }

    public void startStepByStep() {
        try {
            saveProgram();
            Environment.get().setInterpretationProgram(window.getProgramText());
            Environment.get().interpreter.startStepByStep();
            updateVariables();
            window.enableInterpretControls();
        } catch (Exception e1) {
            window.writeToLog(e1.getClass().getSimpleName() + ":" + e1.getMessage());
            e1.printStackTrace();
        }
    }

    public void step() {
        try {
            Environment.get().interpreter.core.step();
            updateVariables();
        } catch (Exception e1) {
            window.writeToLog(e1.getClass() + ":" + e1.getMessage());
            e1.printStackTrace();
        }
    }

    public void stepInto() {
        try {
            Environment.get().interpreter.core.stepInto();
            updateVariables();
        } catch (Exception e1) {
            window.writeToLog(e1.getClass() + ":" + e1.getMessage());
            e1.printStackTrace();
        }
    }

    public void close() {
        window.dispose();
    }

    public void setSelectedFile(String file) {
        try {
            if (file != null) {
                String text = Environment.get().project.loadText(file);
                isSaved = true;

                window.setProgramText(text);
                window.enableEditor();
                window.setStatus("Selected file " + file);
                window.enableMenuForFiles();
                window.enableStartInterpretControls();

                Environment.get().setSelectedFile(file);
            } else {
                window.disableMenuForFiles();

                if ((Environment.get().project != null) && (Environment.get().project.files.size() == 0))
                    window.setProgramText("Open project...");
                else
                    window.setProgramText("Select file...");

                window.setStatus("Please, select file...");

                window.disableEditor();
                window.disableInterpretControls();
                window.disableStartInterpretControls();

                Environment.get().setSelectedFile(null);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeSelectedFile() {
        try {
            if (Environment.get().getSelectedFile() != null)
                Environment.get().project.removeFile(Environment.get().getSelectedFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        updateProject();
    }

    public void addFile(String name) {
        if (name != null) {
            try {
                Environment.get().project.addFile(name);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            updateProject();
        }
    }

    public void loadProject(String fileName) {
        try {
            Environment.get().loadProject(fileName);
            updateProject();
        } catch (IOException e1) {
            e1.printStackTrace();
            window.setStatus("Error loading project!");
        }
    }

    public void updateVariables() {
        if (Environment.get().interpreter.core != null)
            window.updateContext(Environment.get().interpreter.core.rootContext.values);
    }

    public void showAbout() {
        About about = new About();
        about.setVisible(true);
    }
}
