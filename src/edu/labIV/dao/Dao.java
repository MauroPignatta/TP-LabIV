package edu.labIV.dao;

import edu.labIV.db.DataBaseConnection;

public abstract class Dao <T> {

    protected DataBaseConnection db;

    public Dao() {
        this.db = DataBaseConnection.getInstance();
    }

    abstract boolean save(T entity);

    abstract boolean update(int id, T entity);

    abstract boolean delete(int id);

    abstract T get(int id);
}


