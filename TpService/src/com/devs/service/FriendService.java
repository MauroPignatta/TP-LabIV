package com.devs.service;


import edu.labIV.entity.Friend;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("friend")
public class FriendService extends Service{

    @GET
    @Path("list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendList(@PathParam("id") int id){
        List<Friend> friends = manager.getFriendManager().getFriendList(id);
        return Response.ok().entity(friends).build();
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFriend(String json){
        //TODO: Implementar
        return getOkResponse();
    }


}
