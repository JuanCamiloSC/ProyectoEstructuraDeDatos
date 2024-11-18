package Proceso.Exception;

public class AccesDeniedException extends Exception {
    public AccesDeniedException() {
        super("El usuario no cuenta con permisos suficientes para realizar esta acción");
    }
}
