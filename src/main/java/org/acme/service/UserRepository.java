package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.UserDTO;
import org.acme.exception.MyRuntimeException;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class UserRepository {

    ConcurrentHashMap<Long, UserDTO> users = new ConcurrentHashMap<>();

    public void add(UserDTO userDTO){
        if(userDTO.id() % 3 == 0){
            throw new MyRuntimeException(userDTO.id(),"Error to add user id:" + userDTO.id());
        }
        users.put(userDTO.id(), userDTO);
    }

    public boolean has(Long id){
        return users.containsKey(id);
    }

    public UserDTO find(Long id){

        return users.get(id);
    }

    public Collection<UserDTO> findAll(){
        return users.values();
    }
}
