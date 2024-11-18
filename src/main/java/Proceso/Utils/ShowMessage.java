package Proceso.Utils;

import javafx.scene.control.Alert;

public class ShowMessage {

    public static void mostrarMensaje(String titulo, String header, String contenido) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}

