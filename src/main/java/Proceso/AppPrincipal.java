package Proceso;
import Proceso.Controllers.RegisterController;
import Proceso.Controllers.TaskController;
import Proceso.Model.Tool;
import javafx.application.Application;
import Proceso.Controllers.InicioController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static Proceso.Controllers.AppController.INSTANCE;
import javafx.application.Platform;

import java.io.IOException;

public class AppPrincipal extends Application {
    private static Stage primaryStage;

    Tool tool = INSTANCE.getHerramienta();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppPrincipal.primaryStage = primaryStage;
        AppPrincipal.primaryStage.setTitle("Herramienta");

        mostrarVentanaIniciarHerramienta();
    }

    public void changeWindow(Scene newScene) {
        if (primaryStage != null) {
            primaryStage.setScene(newScene);
            primaryStage.show();
        } else {
            System.out.println("primaryStage es null");
        }
    }

    public void mostrarVentanaIniciarHerramienta() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AppPrincipal.class.getResource("InicioView.fxml"));

                AnchorPane rootLayout = loader.load();

                INSTANCE.setUsuarioActual(null);

                Scene scene = new Scene(rootLayout);
                changeWindow(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void mostrarVentanaLoginAdmin() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AppPrincipal.class.getResource("InicioAdminView.fxml"));

                AnchorPane rootLayout = loader.load();

                InicioController inicioController = loader.getController();
                inicioController.setAplicacion(new AppPrincipal());

                Scene scene = new Scene(rootLayout);
                changeWindow(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void mostrarVentanaProcesosAdmin() {
        Platform.runLater(() -> {
            // Aquí puedes agregar el código para mostrar la ventana de procesos admin
        });
    }

    public void mostrarVentanaTareasAdmin() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AppPrincipal.class.getResource("TaskView.fxml"));

                AnchorPane rootLayout = loader.load();

                TaskController taskController = loader.getController();
                taskController.setAplicacion(this);

                Scene scene = new Scene(rootLayout);
                changeWindow(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void mostrarVentanaRegistrarse() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AppPrincipal.class.getResource("RegisterView.fxml"));

                AnchorPane rootLayout = loader.load();

                RegisterController registerController = loader.getController();
                registerController.setAplicacion(this);

                Scene scene = new Scene(rootLayout);
                changeWindow(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}



