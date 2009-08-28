package gui;

import tree.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import gui.tests.TestData;

import java.util.Random;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import utils.Messages;

/**
 * @author nastya
 *         Date: 22.08.2009
 *         Time: 23:04:17
 */
public class TestingIndexForm {
    private JPanel panel1;
    private JTree tree;
    private JTextField tfKeyCount;
    private JButton btnGenerate;
    private JTextArea log;
    private JButton btnGenerateSelection;
    DefaultMutableTreeNode root;

    public TestingIndexForm() {
        btnGenerate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onGenerate();
            }
        });

        btnGenerateSelection.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                onGenerateBySelection();
            }
        });

    }

    private void onGenerateBySelection() {
        root.removeAllChildren();
        BPlusTree<TestData> bPlusTree = new BPlusTree<TestData>(3);
        String selected = log.getSelectedText();
        if(selected == null || selected.trim().isEmpty()) return;
        StringTokenizer tokenizer = new StringTokenizer(selected, "\n");
        while(tokenizer.hasMoreTokens()){
            TestData newData = generateElement(tokenizer.nextToken());
            if(newData != null){
            bPlusTree.add(newData);
            }
        }
        fill(bPlusTree);
        tree.updateUI();
    }

    private TestData generateElement(String s) {
        int element = -1;
        try{
            element = Integer.parseInt(s);
        }catch(NumberFormatException e){

        }
        if(element > -1){
            return new TestData(element, (long)element);
        }
        return null;
    }

    private void createUIComponents() {
        root = new DefaultMutableTreeNode("Демонстрационное дерево");
        tree = new JTree(root);
        tree.setShowsRootHandles(true);
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void fill(BPlusTree bPlusTree) {
        DefaultMutableTreeNode root1 = new DefaultMutableTreeNode(bPlusTree.getRoot());
        root.add(root1);
        add(root1, bPlusTree.getRoot());
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
            if (node.haveChildrenNodes()) {
                DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("rest");
                root.add(treeNode);
                add(treeNode, node.getLink());
            } else {
                root.add(new DefaultMutableTreeNode("linked to: " + String.valueOf(node.getLink())));
            }

        } else {
            root.add(new DefaultMutableTreeNode(element));
        }
    }

    private void onGenerate() {
        int number = -1;
        try {
            number = Integer.valueOf(tfKeyCount.getText().trim());
        } catch (NumberFormatException e) {

        }
        if (number == -1) {
            JOptionPane.showMessageDialog(panel1, Messages.WRONG_NUMBER_OF_KEYS, Messages.ERROR, JOptionPane.ERROR_MESSAGE);
        }
        root.removeAllChildren();
        log.setText("START LOGGING\n");
        BPlusTree<TestData> bPlusTree = new BPlusTree<TestData>(3);
        for (int i = 0; i < number; i++) {
            bPlusTree.add(generateNewRandomElement(number));
        }
        fill(bPlusTree);
        log.append("END");
        tree.updateUI();
        log.updateUI();

    }

    private TestData generateNewRandomElement(int diapason) {
        Random random = new Random();
        int generated = random.nextInt(diapason);
        log.append(generated + "\n");
        return new TestData(generated, random.nextLong());
    }

}

