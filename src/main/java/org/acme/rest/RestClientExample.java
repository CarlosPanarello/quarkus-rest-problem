package org.acme.rest;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.UserDTO;
import org.acme.filter.ExceptionMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Collection;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://localhost:8080/user/")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RegisterProvider(ExceptionMapper.class)
public interface RestClientExample {

    @POST
    @Path("{id}")
    UserDTO addUser(@PathParam("id") Long id, UserDTO userDTO);

    @GET
    @Path("{id}")
    UserDTO getUser(@PathParam("id") Long id);

    @GET
    Collection<UserDTO> getAll();

}
