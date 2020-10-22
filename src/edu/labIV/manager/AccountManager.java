package edu.labIV.manager;

import edu.labIV.logger.Logger;
import edu.labIV.entity.Account;
import edu.labIV.exception.AccountException;
import edu.labIV.exception.InactiveAccount;
import edu.labIV.exception.WrongPasswordExcepcion;
import edu.labIV.mapper.AccountMapper;
import edu.labIV.validator.AccountValidator;

public class AccountManager {

    private AccountValidator accountValidator;
    private AccountMapper accountMapper;
    private Logger logger;

    public AccountManager() {
        this.accountValidator = new AccountValidator();
        this.accountMapper = new AccountMapper();
        this.logger = Logger.getInstance();
    }

    public boolean login(String email, String password){
        boolean isConnected = false;
        Account account = accountMapper.get(email);
        if(account.getAvailableTries() == 0){
            //TODO mandar formulario para cambiar contraseña
        }
        try{
            accountValidator.validateAccount(account);
            accountValidator.validateIsActive(account);
            accountValidator.validateCorrectPassword(account.getPassword(), password);
            account.setAvailableTries(Account.TRIES);
            accountMapper.update(account);
            isConnected = true;
        }catch (InactiveAccount e) {
            logger.logError(e.getError());
        }catch (WrongPasswordExcepcion e){
            logger.logError(e.getError());
            account.setAvailableTries(account.getAvailableTries() - 1);
            accountMapper.update(account);
        }catch (AccountException e) {
            logger.logError(e.getError());
        }
        return isConnected;
}

   // public boolean logout(Account account){
   //     //TODO accede a la bd para cambiar el estado
   //     return true;
   // }

    public void signIn(String email, String password) {
        Account account = new Account(email, password, false, Account.TRIES);
        try{
            accountValidator.validateAccount(account);
            accountValidator.validateExistingAccount(accountMapper.get(account.getEmail()));
            accountMapper.save(account);
            //TODO mandar mail de activacion de cuenta
        } catch (AccountException e){
            logger.logError(e.getError());
        }
    }

    public void activateAccount(String email){
        Account account = accountMapper.get(email);
        try {
            accountValidator.validateAccount(account);
            account.setActive(true);
            accountMapper.update(account);
        } catch (AccountException e) {
            logger.logError(e.getError());
        }
    }

    public void changePassword(String email, String newPassword){
        Account account = accountMapper.get(email);
        try {
            accountValidator.validatePass(newPassword);
            accountValidator.validateAccount(account);
            account.setPassword(newPassword);
            account.setAvailableTries(Account.TRIES);
            accountMapper.update(account);
        } catch (AccountException e) {
            logger.logError(e.getError());
        }
    }

    public boolean deleteAccount(String email){
        return accountMapper.delete(email);
    }

    public Account getAccount(String email){
        Account account = accountMapper.get(email);
        return account;
    }
}