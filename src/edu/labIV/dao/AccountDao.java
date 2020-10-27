package edu.labIV.dao;

import edu.labIV.entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao extends Dao<Account> {

    //TODO: Cuidado con los parametros que nos pasan. No comprobamos que sean null.

    private static final String ACC_TABLE = "account";
    private static final String ACC_ID = "account_id";
    private static final String ACC_EMAIL = "email";
    private static final String ACC_PASSWORD = "password";
    private static final String ACC_ACTIVE = "active";
    private static final String ACC_TRIES = "available_tries";

    /** Persiste el dato en la base de datos.
     * @param entity Cuenta a persistir.
     * @return Booleano indicando si el dato se guardo correctamente.
     */
    @Override
    public boolean save(Account entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + ACC_TABLE + "("+ ACC_EMAIL +", "+ ACC_PASSWORD +", "+ ACC_ACTIVE +", "+ ACC_TRIES +") VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setBoolean(3, entity.isActive());
            statement.setInt(4, entity.getAvailableTries());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    /** Actualiza todos los campos de account.
     * @param id id de la cuenta a actualizar.
     * @param entity entidad account con los datos a reemplazar.
     * @return Devuelve true si se actualiza un dato correctamente o false en caso de que falle el update.
     */
    @Override
    public boolean update(int id, Account entity) {
        boolean executed = false;
        String sql = "UPDATE " + ACC_TABLE + " SET " +  ACC_PASSWORD + " = ?," +
                ACC_ACTIVE + " = ?," + ACC_TRIES + " = ?" +
                " WHERE " + ACC_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getPassword());
            statement.setBoolean(2, entity.isActive());
            statement.setInt(3, entity.getAvailableTries());
            statement.setInt(4, id);
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    private boolean updatePassword(int id, String password){
        boolean executed = false;
        String sql = "UPDATE " + ACC_TABLE + " SET " + ACC_PASSWORD + " = ? WHERE " + ACC_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, password);
            statement.setInt(2, id);
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    private  boolean updateActive(int id, Boolean active){
        boolean executed = false;
        String sql = "UPDATE " + ACC_TABLE + " SET " + ACC_ACTIVE + " = ? WHERE " + ACC_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setBoolean(1, active);
            statement.setInt(2, id);
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    private boolean updateTries(int id, int tries){
        boolean executed = false;
        String sql = "UPDATE " + ACC_TABLE + " SET " + ACC_TRIES + " = ? WHERE " + ACC_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setInt(1, tries);
            statement.setInt(2, id);
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }


    /** Elimina una cuanta de la base de datos
     *  @param id id de la cuenta a buscar
     *  @return True si se elimino correcamente, false si no se encuentra el id.
     */
    @Override
    public boolean delete(int id) {
        boolean executed = false;
        String sql = "DELETE FROM " + ACC_TABLE + " WHERE " + ACC_ID + " = " + id;
        try{
            Statement statement = db.createStatement();
            executed = statement.executeUpdate(sql) == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    /** Obtiene una cuenta de la base de datos
     *  @param id id de la cuenta a buscar
     *  @return Devuelve la cuenta. En caso de no haber cuenta vinculada al id devuelve null.
     */
    @Override
    public Account get(int id) {
        Account account = null;
        String sql = "SELECT * FROM "+ACC_TABLE+" WHERE " + ACC_ID + " = '" + id + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                account = new Account();
                account.setId(id);
                account.setEmail(resultSet.getString(ACC_EMAIL));
                account.setPassword(resultSet.getString(ACC_PASSWORD));
                account.setActive(resultSet.getBoolean(ACC_ACTIVE));
                account.setAvailableTries(resultSet.getInt(ACC_TRIES));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return account;
    }

    /** Obtiene el id de una cuenta
     *  @param email email de la cuenta a buscar el id
     *  @return id de la cuenta. En caso de no haber id vinculado al email devuelve 0.
     */
    public int getIdFromEmail(String email){
        int id = 0;
        String sql = "SELECT " + ACC_ID + " FROM " + ACC_TABLE + " WHERE " + ACC_EMAIL + " = '" + email + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                id = resultSet.getInt(ACC_ID);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return id;
    }
}
