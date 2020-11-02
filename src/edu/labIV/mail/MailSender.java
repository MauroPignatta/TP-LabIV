package edu.labIV.mail;

import edu.labIV.cfg.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;

public class MailSender {

    private static String account;
    private static String pass;
    private static String protocol;
    private static String host;
    private static String port;
    private static boolean isInitilized;

    private static void init(){
        account = Config.get(ConfigSection.MAIL, ConfigKey.MAIL_USER);
        pass = Config.get(ConfigSection.MAIL, ConfigKey.MAIL_PASS);
        protocol = Config.get(ConfigSection.MAIL, ConfigKey.MAIL_PROTOCOL);
        host = Config.get(ConfigSection.MAIL, ConfigKey.MAIL_SERVER);
        port = Config.get(ConfigSection.MAIL, ConfigKey.MAIL_PORT);
        isInitilized = true;
    }

    public static void sendMail(String recipient, String subject, String body, MailContentType type) throws Exception {
        if (!isInitilized)
            init();

        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);  //El servidor SMTP de Google
        props.put("mail.smtp.user", account);
        props.put("mail.smtp.clave", pass);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", port); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        InternetAddress address = new InternetAddress(recipient);
        message.setFrom(address);
        message.addRecipient(Message.RecipientType.TO, address);
        message.setSubject(subject);
        //message.setText(body);
        message.setContent(body, type.getType());
        Transport transport = session.getTransport(protocol);
        transport.connect(host, account, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
