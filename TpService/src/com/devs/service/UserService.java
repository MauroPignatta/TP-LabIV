package com.devs.service;

import edu.labIV.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserService extends Service{

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList(){
        List<User> userList = manager.getUserManager().getUserList();
        return Response.ok(gson.toJson(userList)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id){
        User user = manager.getUserManager().getUser(id);
        return Response.ok(gson.toJson(user)).build();
    }

}
