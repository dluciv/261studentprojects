package name.stepa.ml;

import name.stepa.ml.controller.Controller;
import name.stepa.ml.highlight.MlEditorKit;
import name.stepa.ml.model.Project;
import name.stepa.ml.model.interpreter.values.functions.AbstractFunctionValue;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class EditorWindow extends JFrame {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonClose;
    private JEditorPane editorPane;
    private JTree projectTree;
    private JLabel statusLabel;
    private JTextPane logTextPane;
    private JButton buttonInterpret;
    private JButton buttonStartStepByStep;
    private JButton stepIntoButton;
    private JTable variablesTable;

    private JMenuItem miNewFile;
    private JMenuItem miRemoveFile;

    Controller controller;
    Project project;

    public void updateContext(Map<String, Object> values) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Variable");
        model.addColumn("Name");
        for (String id : values.keySet()) {
            Object value = values.get(id);
            if (!(value instanceof AbstractFunctionValue))
                model.addRow(new Object[]{id, value});
        }
        variablesTable.setModel(model);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public void setProject(Project p) {
        this.project = p;

        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Project");

        if (p != null) {
            for (String i : p.files) {
                node.add(new DefaultMutableTreeNode(i));
            }
            miNewFile.setEnabled(true);
        } else
            miNewFile.setEnabled(false);

        projectTree.setModel(new DefaultTreeModel(node));

        controller.setSelectedFile(null);
    }

    public void writeToLog(String s) {
        try {
            Document d = logTextPane.getDocument();
            d.insertString(d.getLength(), s + "\n", null);
        } catch (BadLocationException e) {
        }
    }

    public void beep() {
        getToolkit().beep();
    }

    public String getProgramText() {
        return editorPane.getText();
    }

    public void setProgramText(String text) {
        editorPane.setText(text);
    }

    public void enableStartInterpretControls() {
        buttonStartStepByStep.setEnabled(true);
    }

    public void disableStartInterpretControls() {
        buttonStartStepByStep.setEnabled(false);
    }

    public void enableInterpretControls() {
        buttonInterpret.setEnabled(true);
        stepIntoButton.setEnabled(true);
    }

    public void disableInterpretControls() {
        buttonInterpret.setEnabled(false);
        stepIntoButton.setEnabled(false);
    }

    public void enableEditor() {
        editorPane.setEnabled(true);
    }

    public void disableEditor() {
        editorPane.setEnabled(false);
    }

    public void disableMenuForFiles() {
        miRemoveFile.setEnabled(false);
        buttonStartStepByStep.setEnabled(false);
    }

    public void enableMenuForFiles() {
        miRemoveFile.setEnabled(false);
        buttonStartStepByStep.setEnabled(false);
    }

    public void highlight(int start, int last) {
        removeHighlight();
        try {
            editorPane.getHighlighter().addHighlight(start, last, new DefaultHighlighter.DefaultHighlightPainter(Color.orange));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void removeHighlight() {
        editorPane.getHighlighter().removeAllHighlights();
    }

    public EditorWindow() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HotMlStudio");
        getRootPane().setDefaultButton(buttonSave);
        editorPane.setEditorKit(new MlEditorKit());

        setupMenu();

        controller = new Controller(this);

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.saveProgram();
            }
        });

        buttonStartStepByStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.startStepByStep();
            }
        });


        buttonInterpret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.step();
            }
        });

        stepIntoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.stepInto();
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.close();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.close();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.close();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        projectTree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();
                if ((node != null) && (!node.isRoot()) && (node.isLeaf())) {
                    String path = (String) node.getUserObject();
                    controller.setSelectedFile(path);
                } else {
                    controller.setSelectedFile(null);
                }
            }
        });

        editorPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                controller.onTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                controller.onTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                controller.onTextChanged();
            }
        });

        controller.updateProject();
    }

    private void setupMenu() {
        JMenuBar mb = new JMenuBar();

        JMenu mFile = new JMenu("File");
        JMenuItem miOpen = new JMenuItem("Open");
        miNewFile = new JMenuItem("New file...");
        miRemoveFile = new JMenuItem("Remove selected file");

        miOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory())
                            return true;
                        else
                            return f.getName().equals("hmls.proj");
                    }

                    @Override
                    public String getDescription() {
                        return "Project files";
                    }
                });
                chooser.setAcceptAllFileFilterUsed(false);
                int res = chooser.showOpenDialog(EditorWindow.this);
                if (res != JFileChooser.CANCEL_OPTION) {
                    String path = chooser.getSelectedFile().getParent() + "\\";
                    controller.loadProject(path);
                }
            }
        });

        miNewFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Enter file name");
                controller.addFile(s);
            }
        });

        miRemoveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.removeSelectedFile();
            }
        });


        mFile.add(miRemoveFile);
        mFile.add(miNewFile);
        mFile.add(miOpen);

        mb.add(mFile);


        JMenu mHelp = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About");
        miAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.showAbout();
            }
        });

        mHelp.add(miAbout);
        mb.add(mHelp);

        setJMenuBar(mb);
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
        }
        catch (ClassNotFoundException e) {
        }
        catch (InstantiationException e) {
        }
        catch (IllegalAccessException e) {
        }


        EditorWindow dialog = new EditorWindow();
        Toolkit tk = Toolkit.getDefaultToolkit();
        URL image = Thread.currentThread().getContextClassLoader().getResource("images/small_90.png");
        dialog.setIconImage(tk.getImage(image));
        dialog.pack();
        dialog.setVisible(true);
        dialog.setSize(700, 500);
        Toolkit toolkit = dialog.getToolkit();
        Dimension size = toolkit.getScreenSize();
        dialog.setLocation((size.width - dialog.getWidth()) / 2, (size.height - dialog.getHeight()) / 2);

    }
}

