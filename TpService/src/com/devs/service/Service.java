package com.devs.service;

import edu.labIV.manager.BackEndManager;

public abstract class Service {

    protected BackEndManager manager;

    public Service() {
        manager = new BackEndManager();
    }
}
