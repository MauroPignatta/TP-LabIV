package com.devs.service;

import com.devs.helper.ImageHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mauroPignatta.Base64Image;
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

            Base64Image image = new Base64Image(jsonObject.get("photo").getAsString());
            post.setImagePath(ImageHelper.savePostImage(post.getUserId(), post.getPostId(), image));
            manager.getPostManager().updatePost(post);
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
}
