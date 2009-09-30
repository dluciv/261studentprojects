/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
namespace Huffman.Tree
{
    /// <summary>
    /// Лист дерева Хаффмана
    /// </summary>
    public class TreeLeaf : TreeElement
    {
        public byte Content { get; set; }

        public TreeLeaf(byte content)
        {
            Content = content;
        }
    }
}
