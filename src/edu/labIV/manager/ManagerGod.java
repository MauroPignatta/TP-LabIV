package edu.labIV.manager;

import edu.labIV.entity.Account;
import edu.labIV.entity.Friend;
import edu.labIV.entity.User;
import edu.labIV.entity.UserStatus;
import edu.labIV.factory.manager.ManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class ManagerGod {
    //TODO cambiar nombre a la clase xd jaja lol mariano (profesor) te amo <3. por: Yago

    private final AccountManager accountManager;
    private final UserManager userManager;
    private final FriendManager friendManager;
    private final PostManager postManager;

    public ManagerGod() {
        ManagerFactory factory = new ManagerFactory();
        this.accountManager = factory.createAccountManager();
        this.userManager = factory.createUserManager();
        this.friendManager = factory.createFriendManager();
        this.postManager = factory.createPostManager();
    }

    public boolean logIn(String email, String encryptedPassword){
        boolean hasLoggedIn = false;
        if(hasLoggedIn = accountManager.login(email, encryptedPassword)){
            Account account = accountManager.getAccount(email);
            User user = userManager.getUser(account.getId());
            user.setStatus(UserStatus.ONLINE);
            userManager.updateUser(user);
        }
        return hasLoggedIn;
    }

    public void logOut(String email){
        //TODO: Hay que verificar que el mail exista sino rompe
        Account account = accountManager.getAccount(email);
        User user = userManager.getUser(account.getId());
        user.setStatus(UserStatus.OFFLINE);
        userManager.updateUser(user);
    }

    public void delete(String email){
        accountManager.deleteAccount(email);
    }

    public void delete(int id){
        accountManager.deleteAccount(id);
    }

    public boolean signIn(String email, String password, User user){
        boolean hasSignIn = true;
        if(accountManager.signIn(email, password)){
            Account account = accountManager.getAccount(email);
            user.setId(account.getId());
            if(!userManager.saveUser(user)){
                accountManager.deleteAccount(user.getId());
                hasSignIn = false;
            }
        }
        return hasSignIn;
    }

    public List<User> getAddableUserList(int userId){
        List<User> addableUserList = new ArrayList<>();
        List<Friend> friendList = friendManager.getAll(userId);
        List<Integer> friendIdList = getFriendsIds(friendList);

        for( User currentUser : userManager.getUserList()){
            if( userId != currentUser.getId()){
                boolean isFriend = friendIdList.contains(currentUser.getId());
                if(!isFriend){
                    addableUserList.add(currentUser);
                }
            }
        }

        return addableUserList;
    }

    private List<Integer> getFriendsIds(List<Friend> friendList){
        List<Integer> friendIdList = new ArrayList<>();
        if (friendList != null)
            for(Friend friend : friendList){
                friendIdList.add(friend.getFriendId());
            }
        return friendIdList;
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

    public PostManager getPostManager(){return postManager;}
}
