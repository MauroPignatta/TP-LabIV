package edu.labIV.manager;

import edu.labIV.factory.entity.AccountFactory;
import edu.labIV.logger.Logger;
import edu.labIV.entity.Account;
import edu.labIV.exception.AccountException;
import edu.labIV.exception.InactiveAccount;
import edu.labIV.exception.WrongPasswordExcepcion;
import edu.labIV.mapper.AccountMapper;
import edu.labIV.util.PasswordEncryptor;
import edu.labIV.validator.AccountValidator;

public class AccountManager {

    private final AccountValidator accountValidator;
    private final AccountMapper accountMapper;
    private final Logger logger;

    public AccountManager(AccountValidator accountValidator, AccountMapper accountMapper, Logger logger) {
        this.accountValidator = accountValidator;
        this.accountMapper = accountMapper;
        this.logger = logger;
    }

    public boolean login(String email, String encryptedPassword){
        boolean isConnected = false;
        PasswordEncryptor encryptor = new PasswordEncryptor();
        Account account = accountMapper.get(email);
        if(account.getAvailableTries() == 0){
            //TODO mandar formulario para cambiar contraseña
        } else {
            try{
                accountValidator.validateAccount(account);
                accountValidator.validateIsActive(account);
                String password = encryptor.decodePassword(encryptedPassword);
                accountValidator.validateCorrectPassword(password, account.getPassword());
                account.setAvailableTries(Account.TRIES);
                isConnected = accountMapper.update(account);
            }catch (InactiveAccount e) {
                logger.logError(e.getError());
            }catch (WrongPasswordExcepcion e){
                account.setAvailableTries(account.getAvailableTries() - 1);
                accountMapper.update(account);
                logger.logError(e.getError());
            }catch (AccountException e) {
                logger.logError(e.getError());
            }
        }
        return isConnected;
    }

    public boolean signIn(String email, String password) {
        boolean isSigned = false;
        AccountFactory factory = new AccountFactory();
        Account account = factory.createNewAccount(email, password);
        PasswordEncryptor encryptor = new PasswordEncryptor();
        try{
            accountValidator.validateAccount(account);
            accountValidator.validateExistingAccount(accountMapper.get(account.getEmail()));
            String securePassword = encryptor.generateSecurePassword(password);
            account.setPassword(securePassword);
            isSigned = accountMapper.save(account);
            //TODO mandar mail de activacion de cuenta
        } catch (AccountException e){
            logger.logError(e.getError());
        }
        return isSigned;
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
        PasswordEncryptor encryptor = new PasswordEncryptor();
        try {
            accountValidator.validatePass(newPassword);
            accountValidator.validateAccount(account);
            String securedPassword = encryptor.generateSecurePassword(newPassword);
            account.setPassword(securedPassword);
            account.setAvailableTries(Account.TRIES);
            accountMapper.update(account);
        } catch (AccountException e) {
            logger.logError(e.getError());
        }
    }

    public boolean deleteAccount(String email){
        return accountMapper.delete(email);
    }

    public boolean deleteAccount(int id){
        return accountMapper.delete(id);
    }

    public Account getAccount(String email){
        return accountMapper.get(email);
    }

    public Account getAccount(int id){
        return accountMapper.get(id);
    }
}