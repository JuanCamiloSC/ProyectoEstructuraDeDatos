package Proceso.Model;

import java.util.Objects;

public class User {
    private UserType userType;
    private String userName;
    private String password;
    private NotificationType notificationType = NotificationType.MAIL;
    private String mail;

    public User(UserType userType, String userName, String password, NotificationType notificationType, String mail) {
        this.userType = userType;
        this.userName = userName;
        this.password = password;
        this.notificationType = notificationType;
        this.mail = mail;
    }

    public User(){
        super();
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o){
        if(this == o ) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User)o;
        return userType == user.userType && Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userType, userName, password);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "tipoUsuario=" + userType +
                ", nombreUsuario='" + userName + '\'' +
                ", contrasenia='" + password + '\'' +
                '}';
    }


}
