package edu.labIV.manager;

import edu.labIV.entity.Account;
import edu.labIV.exceptions.AccountException;
import edu.labIV.exceptions.ExistingAccountException;
import edu.labIV.exceptions.InvalidAccountException;
import edu.labIV.exceptions.InvalidPasswordException;
import edu.labIV.mapper.AccountMapper;
import edu.labIV.validator.AccountValidator;

public class AccountManager {

    private AccountValidator accountValidator;
    private AccountMapper accountMapper;

    public AccountManager() {
        this.accountValidator = new AccountValidator();
        this.accountMapper = new AccountMapper();
    }

    public boolean login(String email, String password){
        //TODO contador de intentos fallidos
        //TODO Cambiar el estado a conectado.
        Account account = accountMapper.get(email);
        if(account != null && account.isActive()){
            return password.equals(account.getPassword());
        }
        return false;
    }

   // public boolean logout(Account account){
   //     //TODO accede a la bd para cambiar el estado
   //     return true;
   // }

    public boolean signIn(String email, String password) {
        Account account = new Account(email, password, false);
        boolean saved = false;
        try{
            accountValidator.validateAccount(account);
            saved = accountMapper.save(account);
            //TODO mandar mail de activacion de cuenta
        } catch (AccountException e){
            e.printStackTrace();
        }

        return saved;
    }

    public boolean activateAccount(String email){
        Account account = accountMapper.get(email);
        if(account != null){
            return accountMapper.update(account.getEmail(), account.getPassword(), true);
        }
        return false;
    }

    public boolean changePassword(String email, String newPassword){
        Account account = accountMapper.get(email);
        boolean pwChanged = false;
        try {
            accountValidator.validatePass(newPassword);
            if(account != null){
                pwChanged = accountMapper.update(email, newPassword, account.isActive());
            }
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        }

        return pwChanged;
    }

    public boolean deleteAccount(String email){
        return accountMapper.delete(email);
    }

}