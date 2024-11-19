package Proceso.Controllers;

import Proceso.AppPrincipal;
import Proceso.Model.NotificationType;
import Proceso.Model.User;
import Proceso.Model.UserType;
import Proceso.Utils.ShowMessage;
import Proceso.Exception.IncompleteDataException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Proceso.Exception.IncorrectDataException;

import java.io.IOException;

import  static Proceso.Controllers.AppController.INSTANCE;

public class SettingsViewController {

    User user= INSTANCE.getUsuarioActual();



    @FXML
    private ImageView backSettings;

    @FXML
    private Button btnSaveSettings;

    @FXML
    private ComboBox<UserType> comboBoxUserTypeSettings;

    @FXML
    private ComboBox<NotificationType> comoBoxNotificationTypeSettings;

    @FXML
    private TextField txtMailSettings;

    @FXML
    private TextField txtNameSettings;

    @FXML
    private TextField txtPasswordSettings;

    @FXML
    void clickedSaveSettings(ActionEvent event) throws IOException {
        try{
            if(txtMailSettings.getText().isEmpty()|| txtPasswordSettings.getText().isEmpty()|| txtNameSettings.getText().isEmpty())
                throw new IncompleteDataException();
            if(comboBoxUserTypeSettings.getValue()==null|| comoBoxNotificationTypeSettings.getValue()==null)
                throw new IncompleteDataException();
            if(user.getPassword().equals(txtPasswordSettings.getText())){
                user.setUserName(txtNameSettings.getText());
                user.setMail(txtMailSettings.getText());
                user.setUserType(comboBoxUserTypeSettings.getValue());
                user.setNotificationType(comoBoxNotificationTypeSettings.getValue());
                AppPrincipal.showTool();

            }else {
                throw new IncorrectDataException();
            }
        }catch (IncompleteDataException e){
            ShowMessage.mostrarMensaje("Error","Datos incorrectos","datos incompletos");
        }catch (IncorrectDataException e){
            ShowMessage.mostrarMensaje("Error", "Datos incorrectos", "La contraseña es incorrecta");
        }

    }
    @FXML
    void backSettingsAction(ActionEvent event) throws IOException {
        AppPrincipal.showTool();
    }

    @FXML
    void initialize(){
        comboBoxUserTypeSettings.getItems().addAll(UserType.values());
        comoBoxNotificationTypeSettings.getItems().addAll(NotificationType.values());
    }



}

