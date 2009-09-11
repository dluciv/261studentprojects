package dk.main;

public class DataList {

	private int dataList[];

	public DataList(int size, int content) {
		dataList = new int[size];
		for (int i = 0; i < size; i++) {
			dataList[i] = content;
		}
	}

	public void putData(int index, int data) {	
		dataList[index] = data; 
	}

	public void addOne(int index) {
		dataList[index]++;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < dataList.length; i++) {
			s += (dataList[i] + " ");
		}
		return s;
	}

	public int size() {
		return dataList.length;
	}

	public int getData(int i) {
		return dataList[i];
	}

}
