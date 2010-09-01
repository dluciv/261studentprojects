namespace Core
{
    public class Branch
    {
        public Branch()
        {
        }

        public Branch(string name, string head)
        {
            Name = name;
            Head = head;
        }

        public string Head { get; set; }//ссылка на верхний коммит
        public string Name { get; set; }
    }
}