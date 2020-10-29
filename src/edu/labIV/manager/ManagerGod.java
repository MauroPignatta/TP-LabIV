package edu.labIV.manager;

import com.sun.xml.internal.ws.config.metro.dev.FeatureReader;
import edu.labIV.entity.Account;
import edu.labIV.entity.Friend;
import edu.labIV.entity.User;
import edu.labIV.entity.UserStatus;

import java.util.ArrayList;
import java.util.List;

public class ManagerGod {
    //TODO cambiar nombre a la clase xd jaja lol mariano (profesor) te amo <3. por: Yago

    private AccountManager accountManager;
    private UserManager userManager;
    private FriendManager friendManager;

    public ManagerGod() {
        this.accountManager = new AccountManager();
        this.userManager = new UserManager();
        this.friendManager = new FriendManager();
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

    public List<User> getAddableUser(int userId){
        //TODO esta feito
        List<User> addableUserList = new ArrayList<>();
        List<Friend> friendList = friendManager.getAll(userId);
        List <Integer> friendIdList = new ArrayList<>();
        for(Friend friend : friendList){
            friendIdList.add(friend.getFriendId());
        }
        for(User user : userManager.getUserList()){
            if(userId != user.getId()){
                if(friendList.size() == 0 || !friendIdList.contains(user.getId())){
                    addableUserList.add(user);
                }
            }
        }
        return addableUserList;
    }


    /* Agrego para poder usar las funciones de todos los managers */

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public FriendManager getFriendManager(){
        return friendManager;
    }
}
