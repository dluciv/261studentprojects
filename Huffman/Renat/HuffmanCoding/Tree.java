package HuffmanCoding;

public class Tree
{
	// потомки
	private Tree _left;
	private Tree _right;
	
	// родитель
	private Tree _parent;
	
	private int _symbol;
	private int _weight;
	
	// для обхода дерева
	private int _mark = 0;
	private int[] _alphabet = new int[256];
	
	Tree()
	{
		_symbol = -1;
		_weight = 0;
	}
	
	Tree(int symbol)
	{
		_symbol = symbol;
		_weight = 1;
	}
	
	Tree(Tree left, Tree right)
	{
		_symbol = -1;
		_weight = left.weight() + right.weight();
		_left = left;
		_right = right;
		_left.parent(this);
		_right.parent(this);
	}
	
	public void inc()
	{
		_weight++;
	}
	
	public int symbol()
	{
		return _symbol;
	}
	
	public void symbol(int symbol)
	{
		_symbol = symbol;
	}
	
	public int weight()
	{
		return _weight;
	}
	
	public boolean isLeaf()
	{
		return _symbol != -1;
	}
	
	public Tree left()
	{
		return _left;
	}
	
	public void left(Tree left)
	{
		_left = left;
	}
	
	public Tree right()
	{
		return _right;
	}
	
	public void right(Tree right)
	{
		_right = right;
	}
	
	public Tree parent()
	{
		return _parent;
	}
	
	public void parent(Tree parent)
	{
		_parent = parent;
	}
	
	public String toString()
	{
		if (_symbol != -1)
			return "'"+(char)_symbol+"':"+_weight;
		else
			return "("+_left+", "+_right+")";
	}
	
	public int mark()
	{
		return _mark;
	}
	
	public void mark(int increment)
	{
		_mark += increment;
	}
	
	// максимальная длина дерева в битах - 255*4
	public Bits toBits()
	{
		Bits result = new Bits();
		
		Tree current = this;
		
		int size = 0;
		
		while (true)
		{
			if (!current.isLeaf() && current.left().mark() < 2)
			{
				result.add(false);
				current = current.left();
				current.mark(1);
				if (current.isLeaf())
				{
					_alphabet[size] = current.symbol();
					size++;
				}
			}
			else if (!current.isLeaf() && current.right().mark() < 2)
			{
				result.add(false);
				current = current.right();
				current.mark(1);
				if (current.isLeaf())
				{
					_alphabet[size] = current.symbol();
					size++;
				}
			}
			else if (current.isLeaf() || (current.mark() < 2 && current.parent() != null))
			{
				current.mark(1);
				result.add(true);
				current = current.parent();
			}
			else
				return result;
		}
	}
	
	// этот метод должен вызываться после toBits(),
	// потому что там формируется алфавит ;(
	public int[] alphabet()
	{
		return _alphabet;
	}
}