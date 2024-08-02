package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.UserDTO;
import org.acme.exception.MyRuntimeException;
import org.acme.rest.RestClientExample;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Collection;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    @Inject
    @RestClient
    RestClientExample restClientExample;


    public void add(UserDTO userDTO){
        userRepository.add(userDTO);
    }

    public UserDTO find(Long id){
        return userRepository.find(id);
    }

    public Collection<UserDTO> findAll(){
        return userRepository.findAll();
    }
    public void addRestClient(UserDTO userDTO){
        restClientExample.addUser(userDTO.id(),userDTO);
    }

    public UserDTO findRestClient(Long id){
        return restClientExample.getUser(id);
    }

    public Collection<UserDTO> findAllRestClient(){
        return restClientExample.getAll();
    }

}
