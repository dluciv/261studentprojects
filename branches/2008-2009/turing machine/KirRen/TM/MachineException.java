package TM;

public class MachineException extends Exception
{
    String message;

    MachineException(String message) {
        this.message = message;
    }
}
