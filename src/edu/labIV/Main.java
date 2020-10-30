package edu.labIV;


import edu.labIV.entity.Post;
import edu.labIV.manager.ManagerGod;

import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args) {
        ManagerGod managerGod = new ManagerGod();
        Post post = new Post(27, "hola", null, LocalDateTime.now());
        managerGod.getPostManager().savePost(post);


    }
}


