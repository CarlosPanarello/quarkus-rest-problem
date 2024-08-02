package org.acme.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.dto.UserDTO;
import org.acme.service.UserService;

@Path("/user")
@ApplicationScoped
public class RestResource {

    @Inject
    UserService userService;

    @POST
    @Path("/{id}")
    public Response addUser(@PathParam("id") Long id, UserDTO userDTO){
        userService.add(userDTO);
        return Response.ok(userDTO).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id){
        return Response.ok(userService.find(id)).build();
    }

    @GET
    public Response getAllUser(){
        return Response.ok(userService.findAll()).build();
    }

    @POST
    @Path("restclient/{id}")
    public Response addUserRestClient(@PathParam("id") Long id, UserDTO userDTO){
        userService.addRestClient(userDTO);
        return Response.ok(userDTO).build();
    }

    @GET
    @Path("restclient/{id}")
    public Response getUserRestClient(@PathParam("id") Long id){
        return Response.ok(userService.findRestClient(id)).build();
    }

    @GET
    @Path("restclient")
    public Response getAllUserRestClient(){
        return Response.ok(userService.findAllRestClient()).build();
    }
}
