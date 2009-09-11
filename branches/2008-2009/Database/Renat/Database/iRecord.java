package Database;

public interface iRecord {
	// возвращает количество байт, необходимое для хранения сериализованного объекта
	int getRecordLength();
	
	// возвращает массив байт, представляющих сериализованный объект,
	// и длиной ровно getRecordLength()
	byte[] serialize();
	
	// "десериализует" объект из массива байт
	void unserialize(byte[] data);
}