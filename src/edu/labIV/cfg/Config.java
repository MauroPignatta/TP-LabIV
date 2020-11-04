package edu.labIV.cfg;


import org.ini4j.Ini;

import java.io.*;

public class Config {

    private static Config instance;

    private Ini ini;

    private Config() {
        try {
            ini = new Ini(new File("config/config.devs"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance(){
        if(instance == null)
            instance = new Config();

        return instance;
    }

    public String getString(ConfigSection section, ConfigKey key){
        return ini.get(section.name(), key.getKey());
    }

    public int getInt(ConfigSection section, ConfigKey key){
        return Integer.parseInt(getString(section, key) );
    }

}
