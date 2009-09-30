/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
namespace Huffman.Tree
{
    /// <summary>
    /// Узел дерева Хаффнама. Так же является самим деревом
    /// </summary>
    public class TreeNode : TreeElement
    {
        public TreeElement Right { get; set; }

        public TreeElement Left { get; set; }

        public TreeNode(TreeElement right, TreeElement left)
        {
            Right = right;
            Left = left;

            if (right != null)
            {
                right.IncrementLevel();
            }
            if (left != null)
            {
                left.IncrementLevel();
            }
        }

        override public void IncrementLevel()
        {
            base.IncrementLevel();

            if (Right != null)
            {
                Right.IncrementLevel();
            }
            if (Left != null)
            {
                Left.IncrementLevel();
            }
        }
    }
}
