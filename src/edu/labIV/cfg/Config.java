package edu.labIV.cfg;


import org.ini4j.Ini;

import java.io.*;

public class Config {

    private static Ini ini;
    private static boolean isInitialized;

    public static void init(){
        try {
            ini = new Ini(new File("config/config.devs"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        isInitialized = true;
    }

    public static String getString(ConfigSection section, ConfigKey key){
        if(!isInitialized)
            init();
        return ini.get(section.name(), key.getKey());
    }

    public static int getInt(ConfigSection section, ConfigKey key){
        return Integer.parseInt( getString(section, key) );
    }

}
