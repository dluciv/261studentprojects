
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        B_Tree<Card> tree = new B_Tree<Card>(CardSet.name_comparator, 2,
                "C:\\Documents and Settings\\Administrator\\My Documents\\NetBeansProjects\\B_Tree\\test\\smth.lsk");
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        System.out.println(tree);
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        System.out.println(tree);
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        System.out.println(tree);
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        System.out.println(tree);
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        System.out.println(tree);
        tree.B_Tree_Insert(CardSet.GenerateRandomCard());
        System.out.println(tree);
        Card a = CardSet.GenerateRandomCard();
        tree.B_Tree_Insert(a);
        System.out.println(tree);
        B_Tree_Node b = new B_Tree_Node(1, 1);        
        B_Tree.KnotInfo r = tree.B_Tree_Search(tree.root, a);
    }

}
