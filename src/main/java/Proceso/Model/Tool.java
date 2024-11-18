package Proceso.Model;

import Proceso.Utils.Mail;
import Proceso.Utils.ShowMessage;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import Proceso.Exception.UserDoesntExistException;
import Proceso.Exception.UserAlreadyExistException;
import Proceso.Exception.ProcessAlreadyExist;
import  static Proceso.Controllers.AppController.INSTANCE;

import java.util.ArrayList;
import java.util.Optional;


public class Tool {

    private String name;
    private final ArrayList<User> userList = new ArrayList<>();

    private final ArrayList<Process> processList = new ArrayList<>();

    public Tool(String name) {
        super();
        this.name = name;

        initialistData();
    }

    public Tool() {
        super();
        initialistData();
    }

    public boolean userExist(String mail) {
        for (User user : userList) {
            if (user.getMail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

    public User searchUser(String mail) throws UserDoesntExistException {
        for (User user : userList) {
            if (user.getMail().equals(mail)) {
                return user;
            }
        }
        throw new UserDoesntExistException();
    }

    public void addUser(User user) throws UserAlreadyExistException {
        if (!userExist(user.getUserName())) {
            userList.add(user);
        } else
            throw new UserAlreadyExistException();
    }

    public void createUser(String userName, String password, UserType userType, String mail, NotificationType notificationType) {
        try {
            addUser(new User(userType, userName, password, notificationType, mail));
        } catch (UserAlreadyExistException e) {
            ShowMessage.mostrarMensaje("Error", "Error al crear el usuario", "El usuario ya existe");
        }
    }

    public boolean delete(User user) {
        return userList.remove(user);
    }

    public boolean deleteUser() throws UserDoesntExistException {
        if (userExist(name))
            return delete(searchUser(name));
        else
            throw new UserDoesntExistException();
    }

    public void addProcess(Process process) {
        if (!processList.contains(process))
            processList.add(process);
        else
            try {
                throw new ProcessAlreadyExist();
            } catch (Exception e) {
                ShowMessage.mostrarMensaje("Error", "Error al agregar el proceso", "El proceso ya existe");
            }
    }

    public Process searchProcess(String name) {
        for (Process process : processList) {
            if (process.getName().equals(name)) {
                return process;
            }
        }
        return null;
    }

    public void createProcess(String name, String id) {
        addProcess(new Process(name, id));
    }

    public void delete(Process process) {
        processList.remove(process);
    }

    public void deleteProcess(String name) {
        for (Process process : processList) {
            if (process.getName().equals(name)) {
                delete(process);
                return;
            }
        }
    }

    public boolean isAdmin(String name) {
        for (User user : userList) {
            if (user.getUserName().equals(name)) {
                return user.getUserType().equals(UserType.ADMINISTRATOR);
            }
        }
        return false;
    }

    public void notifyUser(String message){
        if ((INSTANCE.getUsuarioActual().getNotificationType().equals(NotificationType.MAIL))) {
            Mail mail = new Mail(INSTANCE.getUsuarioActual().getMail(), "Notificacion", message);
            mail.sendMail();
        }else
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText(message);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Puedes agregar más acciones después de hacer clic en "OK"
                }
            });

    }

    public ArrayList<Process> getProcessList(){ return  processList;}

    private void initialistData(){

        User user1 = new User();
        user1.setUserName("Camilo Sanchez");
        user1.setPassword("12345");
        user1.setMail("teo154@outlook.com");
        user1.setNotificationType(NotificationType.MAIL);
        user1.setUserType(UserType.ADMINISTRATOR);

        userList.add(user1);

        User user2 = new User();
        user2.setUserName("Juan Esteban Victoria");
        user2.setPassword("12344");
        user2.setMail("juane.victorial@uqvirtual.edu.co");
        user2.setNotificationType(NotificationType.MAIL);
        user2.setUserType(UserType.ADMINISTRATOR);

        userList.add(user2);

        User user3 = new User();
        user3.setUserName("Tatiana Mosquera");
        user3.setPassword("12355");
        user3.setMail("");
        user3.setNotificationType(NotificationType.MAIL);
        user3.setUserType(UserType.ADMINISTRATOR);

        userList.add(user3);
    }
}


