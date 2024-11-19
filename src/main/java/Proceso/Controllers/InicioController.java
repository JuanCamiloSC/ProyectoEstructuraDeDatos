package Proceso.Controllers;
import Proceso.AppPrincipal;
import Proceso.Model.Tool;
import Proceso.Utils.ShowMessage;
import Proceso.Exception.IncompleteDataException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Proceso.Exception.UserDoesntExistException;
import javafx.scene.input.MouseEvent;



import java.io.IOException;

import  static Proceso.Controllers.AppController.INSTANCE;
public class InicioController {


    Tool tool = INSTANCE.getHerramienta();


    @FXML
    private Button btnGetIntoLogin;

    @FXML
    private TextField txtMailLogin;

    @FXML
    private TextField txtPasswordLogin;

    @FXML
    private Button btnManagerLogin;

    @FXML
    private Button btnRegisterLogin;

    @FXML
    private Label labelManagerLogin;
    @FXML
    private  Label labelRegisterLogin;

    @FXML
    void clickedGetIntoLogin(ActionEvent event) {
        String email = "";
        String password = "";

        email = txtMailLogin.getText();
        password = txtPasswordLogin.getText();
        try {
            if (datosValidos(email, password)) {
                if (tool.searchUser(email).getPassword().equals(password)) {
                    INSTANCE.setUsuarioActual(tool.searchUser(email));

                    AppPrincipal.showAdminProcessView();
                    btnGetIntoLogin.getScene().getWindow().hide();
                } else {
                    throw new UserDoesntExistException();
                }
            } else {
                throw new IncompleteDataException();
            }
        } catch (IncompleteDataException e) {
            ShowMessage.mostrarMensaje("Notificacion Inicio sesion", "Datos Incompletos", "Debe ingresar los datos correctamente, despues de 3 intentos se bloqueara el usuario");
        } catch (UserDoesntExistException e) {
            ShowMessage.mostrarMensaje("Notificacion Inicio sesion", "Usuario no existe", "El usuario no existe o la contrasenia es incorrecta");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    private boolean datosValidos(String userName, String password) {
        return !userName.isEmpty() && !password.isEmpty();
    }

    @FXML
    void clickedManagerLogin(ActionEvent event) throws IOException {
        AppPrincipal.showInicioAdminView();

    }

    @FXML
    void clickedRegisterLogin(ActionEvent event) throws IOException {
        AppPrincipal.showRegisterView();
    }


    @FXML
    void managerLoginAction(MouseEvent mouseEvent) throws IOException {
        AppPrincipal.showInicioAdminView();
    }
    public void registerLoginAction(MouseEvent mouseEvent) throws IOException {
        AppPrincipal.showRegisterView();
        labelRegisterLogin.getScene().getWindow().hide();
    }




    @FXML
    void initialize(){
        assert btnGetIntoLogin != null : "fx:id=\"btnGetIntoLogin\" was not injected: check your FXML file 'InicioView.fxml'.";
        assert txtMailLogin != null : "fx:id=\"txtMailLogin\" was not injected: check your FXML file 'InicioView.fxml'.";
        assert txtPasswordLogin != null : "fx:id=\"txtPasswordLogin\" was not injected: check your FXML file 'InicioView.fxml'.";
        assert labelManagerLogin != null : "fx:id=\"labelManagerLogin\" was not injected: check your FXML file 'InicioView.fxml'.";
        assert labelRegisterLogin != null : "fx:id=\"labelRegisterLogin\" was not injected: check your FXML file 'InicioView.fxml'.";
        assert btnManagerLogin != null : "fx:id=\"btnManagerLogin\" was not injected: check your FXML file 'InicioView.fxml'.";
        assert btnRegisterLogin!= null : "fx:id=\"btnRegisterLogin\" was not injected: check your FXML file 'InicioView.fxml'.";

    }

}