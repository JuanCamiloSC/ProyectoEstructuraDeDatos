package Proceso.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    private String from;
    private String pasword;
    private String to;
    private String subject;
    private String text;

    public Mail(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public void sendMail(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); //Cambia al servidor de correo que está utiliznado
        props.put("mail.smtp.port", "587"); //Puerto del servidor de correo

         // Credenciales del remitente
        from = "finalproyectea@gmail.com";
        pasword = "uotg uxqy ybje blrc";
        // Crear una sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pasword);
            }
        });
        try {
            // Crear un objeto de mensaje Mime
            Message message = new MimeMessage(session);
            // Configurar el remitente
            message.setFrom(new InternetAddress(from));
            // Configurar el destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Configurar el asunto
            message.setSubject(subject);
            // Configurar el contenido del mensaje
            message.setText(text);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
