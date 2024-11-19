module EstructuraDatosPro {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires java.mail;
    requires java.desktop;

    opens Proceso.Controllers to javafx.fxml;
    exports Proceso to javafx.graphics;
    exports Proceso.Controllers to javafx.fxml;
    exports Proceso.Model;
}
