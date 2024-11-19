package Proceso;
import Proceso.Controllers.RegisterController;
import Proceso.Controllers.SettingsViewController;
import Proceso.Controllers.TaskController;
import Proceso.Model.Tool;
import javafx.application.Application;
import Proceso.Controllers.InicioController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static Proceso.Controllers.AppController.INSTANCE;
import javafx.application.Platform;

import java.io.IOException;

public class AppPrincipal extends Application {
    private static Stage primaryStage;

    Tool tool = INSTANCE.getHerramienta();

    @Override
    public void start(Stage stage) throws IOException {
        showTool();

    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void  showTool() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("InicioView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tool");
        stage.show();
        stage.setResizable(false);
    }

    public static void  showInicioAdminView() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("InicioAdminView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Inicio");
        stage.show();
        stage.setResizable(false);
    }

    public static void  showAdminProcessView() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("AdminProcesses.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Process");
        stage.show();
        stage.setResizable(false);
    }


    public static void  showTaskView() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("TaskView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tasks");
        stage.show();
        stage.setResizable(false);
    }



    public static void  showRegisterView() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("RegisterView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Register");
        stage.show();
        stage.setResizable(false);
    }


    public static void  showSettingsView() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("SettingsView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Settings");
        stage.show();
        stage.setResizable(false);
    }

    public static void  showAdminActivitiesView() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("AdminActivities.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Activities");
        stage.show();
        stage.setResizable(false);
    }

}
