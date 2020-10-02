package edu.labIV.dao;

public interface Dao <T> {

    boolean save(T entity);

    boolean update(int id, T entity);

    boolean delete(int id);

    T get(int id);
}
