package com.devs.service;

import com.google.gson.*;
import com.mauroPignatta.Base64Image;
import com.devs.helper.ImageHelper;
import edu.labIV.entity.Account;
import edu.labIV.entity.User;
import edu.labIV.manager.ManagerGod;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.time.LocalDate;

@Path("account")
public class AccountService {

    private final ManagerGod managerGod;

    public AccountService(){
        managerGod = new ManagerGod();
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("email") String email){
        Gson gson = new Gson();
        Account account = managerGod.getAccountManager().getAccount(email);

        return Response.ok(gson.toJson(account)).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response register(String json){
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        String email = jsonObject.get("email").getAsString().toLowerCase();
        String password = jsonObject.get("password").getAsString();
        String name = jsonObject.get("name").getAsString();
        String lastName =  jsonObject.get("lastName").getAsString();
        int year = jsonObject.get("year").getAsInt();
        int month = jsonObject.get("month").getAsInt();
        int day = jsonObject.get("day").getAsInt();
        User user = new User(name, lastName, LocalDate.of(year, month, day));
        JsonElement photo = jsonObject.get("photo");

        boolean isSignedIn = false;

        if(isSignedIn = managerGod.signIn(email, password, user)){

            if(!photo.isJsonNull() && !photo.getAsString().isEmpty()){
                Base64Image image = new Base64Image(jsonObject.get("photo").getAsString());
                int userId = managerGod.getAccountManager().getAccount(email).getId();
                user.setId(userId);
                user.setProfilePicturePath(ImageHelper.saveImage(userId, image));
                managerGod.getUserManager().updateUser(user);
            }
        }

        return Response.status(200).entity(isSignedIn).build();
    }

    @GET
    @Path("activate/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response activate(@PathParam("id") int id) throws IOException {
        boolean active = managerGod.activate(id);
        return Response.ok(active).build();

    }


}
