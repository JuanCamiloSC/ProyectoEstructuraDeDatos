package Proceso.Exception;

public class ActivityDoesntExistException extends Throwable {

    public ActivityDoesntExistException(){

        super("La actividad no existe");

    }
}
