/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
namespace Huffman.Tree
{
    /// <summary>
    /// Базовый класс для ветвей и листьев дерева Хаффмана
    /// </summary>
    public abstract class TreeElement
    {
        protected TreeElement()
        {
            Level = 0;
        }

        public byte Level
        {
            get; set;
        }

        public virtual void IncrementLevel()
        {
            Level++;
        }
    }
}