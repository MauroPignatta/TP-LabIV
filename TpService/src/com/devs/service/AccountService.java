package com.devs.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.labIV.entity.Account;
import edu.labIV.entity.User;

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
        Account account = getAccountManager().getAccount(email);
        return getOkResponse(gson.toJson(account));
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

        if(login) {
            user = getUserManager().getUser(getAccountManager().getAccount(email).getId());
        }
        return getOkResponse(gson.toJson(user));
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

                int userId = getAccountManager().getAccount(email).getId();
                user.setId(userId);
                getUserManager().updatePhoto(user, photo.getAsString());
            }
        }

        return getOkResponse(isSignedIn);
    }

    @GET
    @Path("activate/{id}")
    public Response activate(@PathParam("id") int id) {
        boolean isActivated = manager.activate(id);
        return getOkResponse(isActivated);
    }

    @POST
    @Path("forgot")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response forgot(String json){
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String email = jsonObject.get("email").getAsString().toLowerCase();
        String newPassword = jsonObject.get("password").getAsString();

        boolean hasChanged = getAccountManager().changePassword(email, newPassword);

        return getOkResponse(hasChanged);
    }

    @GET
    @Path("logout/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("id") int id){
        boolean response = manager.logOut(getAccountManager().getAccount(id).getEmail());
        return getOkResponse(response);
    }

}
