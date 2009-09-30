/// © Бабак Юрий, 261 гр., МатМех, СПбГУ
using System.Collections.Generic;

namespace Huffman.Tree
{
    /// <summary>
    /// Класс, разворачивающий дерево в список(Dictionary) уровней
    /// </summary>
    public class TreeUnfolder : TreeTraverser
    {
        private Dictionary<byte, LevelInfo> _levels;
        /// <summary>
        /// Обходим данное деререво и строим список уровней
        /// </summary>
        /// <param name="tree"></param>
        public TreeUnfolder(TreeElement tree)
            : base(tree)
        {
            _levels = new Dictionary<byte, LevelInfo>();
        }

        protected override void ProcessLeaf(TreeLeaf leaf)
        {
            AddToLevels(leaf.Level, leaf.Content);
        }
        protected override void ProcessNode(TreeNode node)
        {
            AddToLevels(node.Level);
        }

        private void AddToLevels(byte level, byte symbol)
        {
            if (_levels.ContainsKey(level))
            {
                LevelInfo info;
                _levels.TryGetValue(level, out info);
                info.AddByte(symbol);
            }
            else
            {
                LevelInfo info = new LevelInfo();
                info.AddByte(symbol);
                _levels.Add(level, info);
            }
        }
        private void AddToLevels(byte level)
        {
            if (_levels.ContainsKey(level))
            {
                LevelInfo info;
                _levels.TryGetValue(level, out info);
                info.AddNode();
            }
            else
            {
                LevelInfo info = new LevelInfo();
                info.AddNode();
                _levels.Add(level, info);
            }
        }
        /// <summary>
        /// Возвращает список уровней
        /// </summary>
        /// <returns></returns>
        public Dictionary<byte, LevelInfo> GetLevels()
        {
            return _levels;
        }
    }
}
