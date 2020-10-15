package edu.labIV;



import edu.labIV.manager.AccountManager;



public class Main {

    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        System.out.println(accountManager.signIn("mp@gmail.com", "Aa123456"));
    }

}
