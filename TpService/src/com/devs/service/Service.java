package com.devs.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.labIV.manager.BackEndManager;

public abstract class Service {

    protected BackEndManager manager;
    protected Gson gson;

    public Service() {
        manager = new BackEndManager();
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    }
}
