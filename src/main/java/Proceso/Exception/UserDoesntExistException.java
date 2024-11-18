package Proceso.Exception;

public class UserDoesntExistException extends Throwable {

    public UserDoesntExistException(){
        super("La tarea ya existe");
    }

}
