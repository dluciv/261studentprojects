package database.generator;

/**
 * Какая-либо ошибка при генерации базы
 *
 * @author nastya
 * Date: 20.08.2009
 * Time: 22:25:47
 *
 */
public class GeneratorException extends Exception{

    public GeneratorException(Throwable cause) {
        super(cause);
    }
}
