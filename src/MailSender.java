import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.*;

public class MailSender {

    public MailSender() {

    }

    public void send(String to, String sub, String msg){
        //Properties
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "465");
        //Session
        Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("easvanimalshelter@gmail.com", "easv2022");
                    }
                });
        //compose message
        try {
            MimeMessage m = new MimeMessage(s);
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            m.setSubject(sub);
            m.setText(msg+"\n\nYours Sincerely\n\nEASV Animal Shelter\nP: +45 20304060\nA: Alsion 4, 6400 Sønderborg");
            //send the message
            Transport.send(m);
            System.out.println("Message sent successfully");
            PopUp popUpText = new PopUp();
            popUpText.popText("Receipt send!", "black", "18", Application.stage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
