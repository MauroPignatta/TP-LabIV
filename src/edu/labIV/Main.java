package edu.labIV;

import edu.labIV.dao.PostDao;

public class Main {

    public static void main(String[] args) {

        PostDao postDao = new PostDao();
        int n = postDao.getPostId(103);
        System.out.println(n);
    }
}


