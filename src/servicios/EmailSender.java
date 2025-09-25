package servicios;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    private final String remitente = "tucorreo@gmail.com"; 
    private final String clave = "TU_CLAVE_DE_APLICACION"; 

    public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente, clave);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            System.out.println("✅ Correo enviado a: " + destinatario);

        } catch (MessagingException e) {
            System.err.println("❌ Error al enviar correo: " + e.getMessage());
        }
    }
}
