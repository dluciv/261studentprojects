package dk.archivator;

import java.util.Vector;

public class DataList {

	private Vector<Integer> dataList;

	public DataList(int size, int content) {
		dataList = new Vector<Integer>();
		for (int i = 0; i < size; i++) {
			dataList.add(i, content);
		}
	}

	public void putData(int index, int data) {
		dataList.remove(index);
		dataList.add(index, data);
	}

	public void deleteData(int index) {
		int tmp = dataList.get(index);
		if (tmp == 1) {
			dataList.remove(index);
		} else {
			dataList.remove(index);
			dataList.add(index, --tmp);
		}
	}

	public boolean isEmpty() {
		if (dataList.isEmpty()) {
			return true;
		} else
			return false;
	}

	public void addData(int index) {
		int tmp = dataList.get(index);
		dataList.remove(index);
		dataList.add(index, ++tmp);
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < dataList.size(); i++) {
			s += (dataList.get(i) + " ");
		}
		return s;
	}

	public int size() {
		return dataList.size();
	}

	public int getData(int i) {
		return dataList.get(i);
	}

}
