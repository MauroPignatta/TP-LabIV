package edu.labIV.manager;

import edu.labIV.entity.Account;
import edu.labIV.entity.User;
import edu.labIV.entity.UserStatus;

public class ManagerGod {
    //TODO cambiar nombre a la clase xd jaja lol mariano (profesor) te amo <3. por: Yago

    private AccountManager accountManager;
    private UserManager userManager;

    public ManagerGod() {
        this.accountManager = new AccountManager();
        this.userManager = new UserManager();
    }

    public void logIn(String email, String password){
        if(accountManager.login(email, password)){
            Account account = accountManager.getAccount(email);
            User user = userManager.getUser(account.getId());
            user.setStatus(UserStatus.ONLINE);
            userManager.updateUser(user);
        }
    }

    public void logOut(String email){
        Account account = accountManager.getAccount(email);
        User user = userManager.getUser(account.getId());
        user.setStatus(UserStatus.OFFLINE);
        userManager.updateUser(user);
    }

    public void delete(String email){
        //TODO implementar trigger
        /*if(userManager.deleteUser(email)){
            accountManager.deleteAccount(email);
        }*/
    }

    public void signIn(String email, String password, User user){
        if(accountManager.signIn(email, password)){
            Account account = accountManager.getAccount(email);
            user.setId(account.getId());
            if(!userManager.saveUser(user)){
                accountManager.deleteAccount(user.getId());
            }
        }
    }

    /* Agrego para poder usar las funciones de todos los managers */

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
