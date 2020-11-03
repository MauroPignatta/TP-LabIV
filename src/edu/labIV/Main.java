package edu.labIV;

import edu.labIV.mail.MailSender;
import edu.labIV.mail.RegisterMail;
import edu.labIV.manager.ManagerGod;
import edu.labIV.util.PasswordEncryptor;

public class Main {

    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();

        String body = RegisterMail.getRegisterMailBody("Mauro", "https://google.com");
        try {
            MailSender.sendMail("mauritto10@gmail.com", "Test", body);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


