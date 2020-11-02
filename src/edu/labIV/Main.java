package edu.labIV;


import edu.labIV.entity.User;
import edu.labIV.mail.MailSender;
import edu.labIV.mail.RegisterMail;
import edu.labIV.manager.ManagerGod;

public class Main {

    public static void main(String[] args) {

        ManagerGod managerGod = new ManagerGod();

        String body = RegisterMail.getRegisterMailBody("Mariano");
        try {
            MailSender.sendMail("winarmarianoj@gmail.com", "Test", body);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


