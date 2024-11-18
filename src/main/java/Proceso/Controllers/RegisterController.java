package Proceso.Controllers;

import Proceso.AppPrincipal;
import Proceso.Model.NotificationType;
import Proceso.Model.Tool;
import Proceso.Model.UserType;
import Proceso.Utils.ShowMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import  static Proceso.Controllers.AppController.INSTANCE;

public class RegisterController {

        Tool tool=  INSTANCE.getHerramienta();

        AppPrincipal appPrincipal;

        @FXML
        private Button btnRegisterRegister;

        @FXML
        private ComboBox<NotificationType> cBoxNotificationRegister;

        @FXML
        private TextField txtCountryRegister;

        @FXML
        private TextField txtIdRegister;

        @FXML
        private TextField txtMailRegister;

        @FXML
        private TextField txtNameRegister;

        @FXML
        private TextField txtNewPasswordRegister;

        @FXML
        private TextField txtPhoneNumberRegister;

        @FXML
        void clickedRegisterRegister(ActionEvent event) {
                String name= txtNameRegister.getText();
                String password= txtNewPasswordRegister.getText();
                String email= txtMailRegister.getText();

                if(name.isEmpty() || password.isEmpty() || email.isEmpty()|| cBoxNotificationRegister.getValue()==null){
                        ShowMessage.mostrarMensaje("Error", "Campos vacios", "Por favor ingrese todos los datos");
                }else {
                        tool.createUser(name, password, UserType.REGULAR,email, cBoxNotificationRegister.getValue() );
                        ShowMessage.mostrarMensaje("Exito", "Usuario registrado", "El usuario se ha registrado exitosamente");
                        appPrincipal.mostrarVentanaIniciarHerramienta();
                }

        }
        @FXML
        void initialize(){
                cBoxNotificationRegister.getItems().addAll(NotificationType.values());
        }
        public void setAplicacion(AppPrincipal aplicacion){
                this.appPrincipal= aplicacion;
        }



}
