package utils;

import com.sun.mail.smtp.SMTPTransport;
import org.apache.logging.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class GoogleMail {
    private static final Logger LOGGER = LoggerManager.createLogger();

    public void send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message, String attachmentPath) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        // -- Set message body --
        msg.setSubject(title);
       // msg.setText(message, "utf-8");
        msg = attachFile(attachmentPath, message, msg);

        msg.setSentDate(new Date());

        // -- Send message --
        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

        t.connect("smtp.gmail.com", username, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

    private MimeMessage attachFile(String path, String messageText, MimeMessage message) {
        Multipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        File file = new File(path);
        try {
            messageBodyPart.setText(messageText);
            multipart.addBodyPart(messageBodyPart);
            if (path.length() > 0) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(path);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(file.getName());
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);
            LOGGER.info("Mail body was set up");
        } catch (MessagingException e) {
            //LOGGER.error("Attachment was not set to the message body");
            e.printStackTrace();
        }
        return message;
    }
}