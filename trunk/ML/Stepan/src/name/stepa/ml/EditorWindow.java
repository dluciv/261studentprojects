package name.stepa.ml;

import name.stepa.ml.highlight.MlEditorKit;
import name.stepa.ml.model.Environment;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.event.*;
import java.io.IOException;

public class EditorWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonClose;
    private JEditorPane editorPane;
    private JTree projectTree;

    public EditorWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSave);
        editorPane.setEditorKit(new MlEditorKit());

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Environment env = Environment.get();
                if (!env.selectedFile.equals(Environment.NONE)) {
                    try {
                        env.project.saveText(env.selectedFile, editorPane.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        try {
            Environment.loadProject("e:\\Study\\Prog\\trunk\\ML\\Stepan\\project\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
        projectTree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        projectTree.getLastSelectedPathComponent();
                if (node.isLeaf()) {
                    String path = (String) node.getUserObject();

                    try {
                        String text = Environment.get().project.loadText(path);
                        editorPane.setText(text);
                        Environment.get().selectedFile = path;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        fillTree();
    }

    private void fillTree() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Project");

        for (String i : Environment.get().project.files) {
            node.add(new DefaultMutableTreeNode(i));
        }

        projectTree.setModel(new DefaultTreeModel(node));
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EditorWindow dialog = new EditorWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
