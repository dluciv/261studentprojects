package HuffmanCoding;

public class Bits
{
	private boolean[] _bits = new boolean[(0xFFF+1)];
	private int _length = 0;
	private int _cursor = 0;
	
	Bits()
	{
	}
	
	public Bits clone()
	{
		Bits result = new Bits();
		result.bits(_bits.clone());
		result.length(_length);
		return result;
	}
	
	public Bits add(boolean value)
	{
		_bits[(_length + _cursor) & 0xFFF] = value;
		_length++;
		return this;
	}
	
	public void enqueue(Bits bits)
	{
		boolean[] bits_values = bits.bits();
		int index;
		for (int i=0; i<bits.length(); i++)
		{
			_bits[(_length + _cursor) & 0xFFF] = bits_values[i];
			_length++;
		}
	}
	
	public int dequeue()
	{
		int result = 0;
		for (int i=0; i<8; i++)
		{
			if (_length > 0)
			{
				result += _bits[_cursor] ? (1 << (7-i)) : 0;
				//System.out.print(_bits[_cursor] ? '1' : '0');
				_length--;
				_cursor++;
				_cursor &= 0xFFF;
			}
		}
		//System.out.println(" "+result+" "+Integer.toHexString(result));
		return result;
	}
	
	public boolean[] bits()
	{
		return _bits;
	}
	
	public void bits(boolean[] bits)
	{
		_bits = bits;
	}
	
	public int length()
	{
		return _length;
	}
	
	public void length(int length)
	{
		_length = length;
	}
	
	public String toString()
	{
		char[] out = new char[_length];
		for (int i=0; i<_length; i++)
			out[i] = _bits[i] ? '1' : '0';
		return new String(out);
	}
}