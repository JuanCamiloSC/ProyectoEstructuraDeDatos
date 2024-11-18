package Proceso.Exception;

public class ActivityAlreadyExistsException extends Exception{

    public ActivityAlreadyExistsException(){
        super("La actividad ya existe");
    }
}
