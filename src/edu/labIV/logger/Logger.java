package edu.labIV.logger;

import java.io.*;

public class Logger {

    private static Logger logger;
    private  BufferedWriter bufferedWriter;
    private static final String PATH = "log/ErrorLog.txt";

    private Logger() {
        openFile();
    }

    public static Logger getInstance(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    private void openFile(){
        File log = new File(PATH);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(log));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logError(String message){
        try{
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //no esta en uso
    public void closeFile(){
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
