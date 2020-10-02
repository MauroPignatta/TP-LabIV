package edu.labIV.manager;

import edu.labIV.entity.Account;
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
        if(account != null &&  account.isActive()){
            return password.equals(account.getPassword());
        }
        return false;
    }

   // public boolean logout(Account account){
   //     //TODO accede a la bd para cambiar el estado
   //     return true;
   // }

    public boolean signIn(String email, String password){
        Account account = new Account(email, password, false);
        boolean valid = accountValidator.validateAccount(account);
        boolean saved = false;

       if(valid){
           if(accountMapper.get(email) == null){
                saved = accountMapper.save(account);
                //TODO mandar mail de activacion de cuenta
           }else{
                //TODO mandar mensaje usuario ya existente
           }
        }

        return valid && saved;
    }

    public boolean activateAccount(String email){
        Account account = accountMapper.get(email);
        if(account != null){
            return accountMapper.update(account.getEmail(), account.getPassword(), true);
        }
        return false;
    }

    public boolean changePassword(String email, String password){
        Account account = accountMapper.get(email);
        if(accountValidator.validatePass(password)){
            if(account != null){
                return accountMapper.update(email, password, account.isActive());
            }
        }
        return false;
    }

    public boolean deleteAccount(String email){
        return accountMapper.delete(email);
    }

}