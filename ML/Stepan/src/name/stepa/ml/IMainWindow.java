package name.stepa.ml;

import name.stepa.ml.model.Project;

import java.util.Map;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public interface IMainWindow {
    public void updateContext(Map<String, Object> values);

    public void setStatus(String status);

    public void setProject(Project p);

    public void writeToLog(String s);

    public void beep();

    public String getProgramText();

    public void setProgramText(String text);

    public void enableStartInterpretControls();

    public void disableStartInterpretControls();

    public void enableInterpretControls();

    public void disableInterpretControls();

    public void enableEditor();

    public void disableEditor();

    public void disableMenuForFiles();

    public void enableMenuForFiles();

    public void highlight(int start, int last);

    public void removeHighlight();

    public void dispose();
}
