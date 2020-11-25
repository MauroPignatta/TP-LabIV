package com.devs.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.labIV.entity.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("post")
public class PostService extends Service {

    @POST
    @Path("new")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response post(String json){
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String id = jsonObject.get("id").getAsString();
        String text = jsonObject.get("text").getAsString().toLowerCase();
        JsonElement photo = jsonObject.get("photo");

        Post post = new Post(Integer.parseInt(id), text, "", LocalDateTime.now());
        boolean isPosted = manager.getPostManager().savePost(post);

        if(isPosted && !photo.isJsonNull() && !photo.getAsString().isEmpty()){
            getPostManager().updatePostPhoto(post, photo.getAsString());
        }
        return Response.ok().entity(isPosted).build();
    }

    @GET
    @Path("list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@PathParam("id") int id){
        List<Post> list = manager.getPostManager().getAllPost(id);
        return Response.ok().entity(gson.toJson(list)).build();
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete(String json){
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        int userId = jsonObject.get("userId").getAsInt();
        int postId = jsonObject.get("postId").getAsInt();
        boolean isDeleted = getPostManager().deletePost(userId, postId);
        return getOkResponse(isDeleted);
    }
}
