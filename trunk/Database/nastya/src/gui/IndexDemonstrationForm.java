package gui;

import tree.BTree;
import tree.TreeNode;
import tree.Key;
import tree.TreeElement;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 23:04:17
 */
public class IndexDemonstrationForm {
    private JPanel panel1;
    private JTree tree;
    DefaultMutableTreeNode root;

    private void createUIComponents() {
        root = new DefaultMutableTreeNode("nn");
        tree = new JTree(root);
        tree.setShowsRootHandles(true);
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void fill(BTree bTree) {
        DefaultMutableTreeNode root1 = new DefaultMutableTreeNode(bTree.getRoot());
        root.add(root1);
        add(root1, bTree.getRoot());
        tree.updateUI();
    }

    private void add(DefaultMutableTreeNode root, TreeElement element) {
        if (element instanceof TreeNode) {
            TreeNode node = (TreeNode) element;
            for (int i = 0; i < node.listSize(); i++) {
                DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node.get(i));
                root.add(treeNode);
                Key key = node.get(i);
                add(treeNode, key.getLink());
            }
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("");
            root.add(treeNode);
            add(treeNode, node.getLink());

        } else {
            root.add(new DefaultMutableTreeNode(element));
        }
    }
}
