package name.stepa.ml;

import name.stepa.ml.highlight.MlEditorKit;
import name.stepa.ml.model.Environment;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
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

    public EditorWindow() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("HotMlStudio");
        getRootPane().setDefaultButton(buttonSave);
        editorPane.setEditorKit(new MlEditorKit());

        setupMenu();

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = Environment.get();
                if (!env.selectedFile.equals(Environment.NONE)) {
                    try {
                        env.project.saveText(env.selectedFile, editorPane.getText());
                        statusLabel.setText("File " + env.selectedFile + " saved...");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        statusLabel.setText("Error while saving " + env.selectedFile + "...");
                    }
                } else {
                    getToolkit().beep();
                    statusLabel.setText("Error! Nothing to save!");
                }
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// callonCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        projectTree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        projectTree.getLastSelectedPathComponent();
                if ((!node.isRoot())&&(!node.isLeaf())) {
                    String path = (String) node.getUserObject();

                    try {
                        String text = Environment.get().project.loadText(path);
                        editorPane.setText(text);
                        editorPane.setEnabled(true);
                        Environment.get().selectedFile = path;

                        statusLabel.setText("Selected file " + path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    Environment.get().selectedFile = Environment.NONE;

                    if (Environment.get().project.files.size() == 0)
                        editorPane.setText("Open project...");
                    else
                        editorPane.setText("Select file...");

                    editorPane.setEnabled(false);
                    statusLabel.setText("Please, select file...");
                }
            }
        });
        fillTree();
    }

    private void setupMenu() {
        JMenuBar mb = new JMenuBar();

        JMenu mFile = new JMenu("File");
        JMenuItem miOpen = new JMenuItem("Open");

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
                        Environment.loadProject(path);
                        fillTree();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        statusLabel.setText("Error loading project!");
                    }
                }


            }
        });
        mFile.add(miOpen);

        mb.add(mFile);


        /*for (int i = 0; i < menus.length; i++)
        mb.add(menus[i]);*/

        setJMenuBar(mb);
    }

    private void fillTree() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Project");

        for (String i : Environment.get().project.files) {
            node.add(new DefaultMutableTreeNode(i));
        }

        projectTree.setModel(new DefaultTreeModel(node));

        statusLabel.setText("Project loaded...");
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
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
