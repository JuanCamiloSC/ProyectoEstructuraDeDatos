package Proceso.Exception;

public class TaskAlreadyExistException extends Throwable {

    public TaskAlreadyExistException(){
        super("La tarea ya existe");
    }
}
