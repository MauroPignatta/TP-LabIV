package edu.labIV.mapper;

import edu.labIV.dao.AccountDao;
import edu.labIV.entity.Account;

public class AccountMapper {

    private AccountDao accountDao;

    public AccountMapper() {
        this.accountDao = new AccountDao();
    }

    /** Persiste una cuenta nueva en la base de datos.
     * @param account Cuanta a almacenar
     * @return - True Si la cuenta fue almacenada correctamente.
     *         - False Si falla al momento de guardar la cuenta.
     */
    public boolean save(Account account){
        boolean saved = false;
        if (account != null){
            saved = accountDao.save(account);
        }
        return saved;
    }

    /** Elimina una cuenta de la base de datos.
     * @param email e-mail de la cuenta a eliminar.
     * @return - True si la cuenta fue eliminada exitosamente 
     *         - False en caso no haya ninguna cuanta vinculada con el email.
     */
    public boolean delete(String email){
        int id = accountDao.getIdFromEmail(email);
        return  accountDao.delete(id);
    }

    /** Obtiene una cuenta.
     * @param email e-mail de la cuenta.
     * @return Cuenta obtenida de la base de datos.
     *         En caso que no haya cuentas vinculadas al email, devuelve null.
     */
    public Account get(String email){
        int id = accountDao.getIdFromEmail(email);
        return  accountDao.get(id);
    }

    /** Actualiza los datos de una cuenta.
     * @param email e-mail de la cuenta a actualizar. 
     * @param password contraseña.
     * @param active indica si la cuenta esta activada o no.
     * @return - True si alguno de los datos se modifico correctamente.
     *         - False En caso que ningun dato haya sido modificado o que no se encuentren
     *         la cuenta no exista.
     */
    public boolean update(String email, String password, boolean active){
        boolean updated = false;

        int id = accountDao.getIdFromEmail(email);
        Account oldAccount = accountDao.get(id);

        Account newAccount = new Account(email, password, active);

        if(!oldAccount.compare(newAccount)) {
            updated = accountDao.update(id, newAccount);
        }

        return updated;
    }
}
