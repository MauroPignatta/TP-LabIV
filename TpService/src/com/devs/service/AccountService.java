package com.devs.service;

import com.devs.helper.ImageHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mauroPignatta.Base64Image;
import edu.labIV.entity.Account;
import edu.labIV.entity.User;
import edu.labIV.manager.AccountManager;
import sun.security.krb5.internal.crypto.RsaMd5CksumType;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("account")
public class AccountService extends Service {

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("email") String email){
        Account account = manager.getAccountManager().getAccount(email);
        return Response.ok(gson.toJson(account)).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String json){
        User user = null;
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        String email = jsonObject.get("email").getAsString().toLowerCase();
        String password = jsonObject.get("password").getAsString();

        boolean login = manager.logIn(email, password);

        if(login){
            user = manager.getUserManager().getUser(manager.getAccountManager().getAccount(email).getId());
        }
        return Response.ok().entity(gson.toJson(user)).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response register(String json){
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

        boolean isSignedIn;

        if(isSignedIn = manager.signIn(email, password, user)){

            if(!photo.isJsonNull() && !photo.getAsString().isEmpty()){
                Base64Image image = new Base64Image(jsonObject.get("photo").getAsString());
                int userId = manager.getAccountManager().getAccount(email).getId();
                user.setId(userId);
                user.setProfilePicturePath(ImageHelper.saveUserImage(userId, image));
                manager.getUserManager().updateUser(user);
            }
        }

        return Response.status(200).entity(isSignedIn).build();
    }

    @GET
    @Path("activate/{id}")
    public Response activate(@PathParam("id") int id) {
        boolean response = manager.activate(id);
        return Response.ok().entity(response).build();
    }

    @POST
    @Path("forgot")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response forgot(String json){
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String email = jsonObject.get("email").getAsString().toLowerCase();
        String newPassword = jsonObject.get("password").getAsString();

        AccountManager accountManager = manager.getAccountManager();
        boolean response = accountManager.changePassword(email, newPassword);

        return Response.ok().entity(response).build();
    }

    @GET
    @Path("logout/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("id") int id){
        boolean response = manager.logOut(manager.getAccountManager().getAccount(id).getEmail());
        return Response.ok().entity(response).build();
    }

}
