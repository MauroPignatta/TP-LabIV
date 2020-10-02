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
        Account account = accountMapper.get(email);
        if(account != null &&  account.isActive()){
            return password.equals(account.getPassword());
        }
        return false;
    }

//    public boolean logout(Account account){
//
//        //TODO accede a la bd para cambiar el estado
//        return true;
//    }

    public boolean signIn(String email, String password){
        //TODO verificar que la cuenta no exista
        Account account = new Account(email, password, false);
        boolean valid = accountValidator.validateAccount(account);

//        if(valid){
//            if(!accountMapper.save(account)){
//                System.out.println("usuario existente");
//                //TODO mandar mensaje usuario ya existente
//            }else{
//                System.out.println("usuario registrado");
//                //TODO mandar mail de activacion de cuenta
//            }
//         }else{
//            System.out.println("error de datos");
//            //TODO datos incorrectos
//        }
        return valid && accountMapper.save(account);
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
