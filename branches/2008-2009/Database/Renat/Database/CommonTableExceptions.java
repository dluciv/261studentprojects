package Database;

public interface CommonTableExceptions {
	class InsertException extends Exception {}
	class SelectException extends Exception {}
	class IndexException extends Exception {}
}