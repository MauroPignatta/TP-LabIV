package com.devs.service;

import com.google.gson.Gson;
import edu.labIV.entity.User;
import edu.labIV.manager.BackEndManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserService {

    private BackEndManager manager;

    public UserService(){
        manager = new BackEndManager();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList(){
        Gson gson = new Gson();
        List<User> userList = manager.getUserManager().getUserList();

        return Response.ok(gson.toJson(userList)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") int id){
        Gson gson = new Gson();
        User user = manager.getUserManager().getUser(id);

        return gson.toJson(user);
    }

}
