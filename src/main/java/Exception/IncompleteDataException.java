package Exception;

public class IncompleteDataException extends Exception{
    public IncompleteDataException(){
        super("Datos incompletos");
    }
}