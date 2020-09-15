package edu.labIV;

import edu.labIV.db.DataBaseConnection;

public class Main {

    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = DataBaseConnection.getInstance();
        String query = "SELECT * FROM test";
        dataBaseConnection.query(query);
    }

}
