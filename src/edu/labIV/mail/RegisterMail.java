package edu.labIV.mail;

import java.io.*;

public class RegisterMail {

    private static String htmlContent;
    private static boolean isInitialized;

    private static void init(){
        File htmlFile = new File("res/mail/activation.html");
        StringBuilder builder = new StringBuilder();;
        try {
            FileReader fileReader = new FileReader(htmlFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while( (line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        htmlContent = builder.toString();
        isInitialized = true;
    }

    public static String getRegisterMailBody(String userName){
        if(!isInitialized)
            init();
        return htmlContent.replace("$USER$", userName);
    }

}
