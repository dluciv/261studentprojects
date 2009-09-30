/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
namespace Huffman.Tree
{
    /// <summary>
    /// Класс обходящий дерево в глубину
    /// </summary>
    public abstract class TreeTraverser
    {
        private TreeElement _tree;

        protected TreeTraverser(TreeElement tree)
        {
            _tree = tree;
        }
        /// <summary>
        /// Обойти дерево
        /// </summary>
        public void Traverse()
        {
            Traverse(_tree);
        }

        /// <summary>
        /// Реккурсивно обходит дерево с текущего элемента
        /// </summary>
        /// <param name="el"></param>
        private void Traverse(TreeElement el)
        {
            if (el == null) return;
            if (el is TreeLeaf)
            {
                ProcessLeaf((TreeLeaf)el);
            }
            else
            {
                ProcessNode((TreeNode)el);
                Traverse(((TreeNode)el).Right);
                Traverse(((TreeNode)el).Left);
            }
        }

        protected abstract void ProcessLeaf(TreeLeaf leaf);
        protected abstract void ProcessNode(TreeNode node);
    }
}
