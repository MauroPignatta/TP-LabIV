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

    public void login(String email, String password){

        if(accountManager.login(email, password)){
            userManager.updateStatus(email, UserStatus.ONLINE);
        }

    }

    public void delete(String email){
        //TODO implementar trigger
        /*if(userManager.deleteUser(email)){
            accountManager.deleteAccount(email);
        }*/
    }

    public void signIn(String email, String password, User user){
        accountManager.signIn(email, password);
        Account account = accountManager.getAccount(email);
        user.setId(account.getId());
        userManager.saveUser(user);
    }

}
