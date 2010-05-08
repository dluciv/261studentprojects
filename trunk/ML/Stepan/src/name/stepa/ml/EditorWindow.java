package name.stepa.ml;

import name.stepa.ml.highlight.MlEditorKit;
import name.stepa.ml.model.Environment;
import name.stepa.ml.model.interpreter.IOutput;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class EditorWindow extends JFrame {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonClose;
    private JEditorPane editorPane;
    private JTree projectTree;
    private JLabel statusLabel;
    private JTextPane logTextPane;
    private JButton buttonInterpret;

    private JMenuItem miNewFile;
    private JMenuItem miRemoveFile;

    private boolean isSaved = false;

    public EditorWindow() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HotMlStudio");
        getRootPane().setDefaultButton(buttonSave);
        editorPane.setEditorKit(new MlEditorKit());

        setupMenu();

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        buttonInterpret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!isSaved) {
                        save();
                        Environment.get().setInterpretationProgram(editorPane.getText());
                    }
                    Environment.get().interpreter.interpret();
                } catch (Exception e1) {
                    addToLog(e1.getClass() + ":" + e1.getMessage());
                    e1.printStackTrace();
                }
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        projectTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        projectTree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) projectTree.getLastSelectedPathComponent();
                if ((node != null) && (!node.isRoot()) && (node.isLeaf())) {
                    String path = (String) node.getUserObject();

                    try {
                        String text = Environment.get().project.loadText(path);
                        editorPane.setText(text);
                        Environment.get().setInterpretationProgram(text);
                        editorPane.setEnabled(true);
                        isSaved = true;
                        Environment.get().setSelectedFile(path);
                        statusLabel.setText("Selected file " + path);

                        onProjectFileSelectionChanged();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    Environment.get().setSelectedFile(null);

                    if ((Environment.get().project != null) && (Environment.get().project.files.size() == 0))
                        editorPane.setText("Open project...");
                    else
                        editorPane.setText("Select file...");
                    editorPane.setEnabled(false);
                    onProjectFileSelectionChanged();
                    statusLabel.setText("Please, select file...");
                }
            }
        });
        fillTree();

        projectTree.setSelectionRow(0);

        editorPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                onTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                onTextChanged();
            }
        });

        Environment.get().interpreter.setOutput(new IOutput() {
            public void println(String s) {
                addToLog(s);
            }
        });
    }

    public void save() {
        Environment env = Environment.get();
        if (env.getSelectedFile() != null) {
            try {
                env.project.saveText(env.getSelectedFile(), editorPane.getText());
                statusLabel.setText("File " + env.getSelectedFile() + " saved...");
                isSaved = true;
            } catch (IOException e1) {
                e1.printStackTrace();
                statusLabel.setText("Error while saving " + env.getSelectedFile() + "...");
            }
        } else {
            getToolkit().beep();
            statusLabel.setText("Error! Nothing to save!");
        }
    }

    public void addToLog(String s) {
        try {
            Document d = logTextPane.getDocument();
            d.insertString(d.getLength(), s+"\n", null);
        } catch (BadLocationException e) {
        }
    }

    public void onTextChanged() {
        isSaved = false;
        Environment.get().setInterpretationProgram(null);
    }

    public void onProjectFileSelectionChanged() {
        if (Environment.get().getSelectedFile() == null) {
            miRemoveFile.setEnabled(false);
            buttonInterpret.setEnabled(false);
        } else {
            miRemoveFile.setEnabled(true);
            buttonInterpret.setEnabled(true);
        }
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
                    try {
                        Environment.get().loadProject(path);
                        fillTree();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        statusLabel.setText("Error loading project!");
                    }
                }


            }
        });

        miNewFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Enter file name");
                if (s == null)
                    return;
                try {
                    Environment.get().project.addFile(s);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                fillTree();
            }
        });

        miRemoveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Environment.get().getSelectedFile() != null)
                        Environment.get().project.removeFile(Environment.get().getSelectedFile());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                fillTree();
            }
        });


        mFile.add(miRemoveFile);
        mFile.add(miNewFile);
        mFile.add(miOpen);

        mb.add(mFile);

        setJMenuBar(mb);
    }

    private void fillTree() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Project");

        if (Environment.get().project != null) {
            for (String i : Environment.get().project.files) {
                node.add(new DefaultMutableTreeNode(i));
            }
            miNewFile.setEnabled(true);
        } else
            miNewFile.setEnabled(false);

        projectTree.setModel(new DefaultTreeModel(node));

        statusLabel.setText("Project loaded...");
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
        dialog.pack();
        dialog.setVisible(true);
        dialog.setSize(700, 500);
        Toolkit toolkit = dialog.getToolkit();
        Dimension size = toolkit.getScreenSize();
        dialog.setLocation((size.width - dialog.getWidth()) / 2, (size.height - dialog.getHeight()) / 2);

    }
}
