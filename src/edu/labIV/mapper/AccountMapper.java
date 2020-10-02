package edu.labIV.mapper;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.labIV.dao.AccountDao;
import edu.labIV.entity.Account;

public class AccountMapper {

    private AccountDao accountDao;

    public AccountMapper() {
        this.accountDao = new AccountDao();
    }

    public boolean save(Account account){
        return accountDao.save(account);
    }

    public boolean delete(String email){
        int id = accountDao.getIdFromEmail(email);
        return  accountDao.delete(id);
    }

    /**
     *
     * @param email
     * @return puede devolver null si la cuenta no existe
     */
    public Account get(String email){
        int id = accountDao.getIdFromEmail(email);
        return  accountDao.get(id);
    }

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
