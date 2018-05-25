package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.UserTypes;
import com.partsnumbersystem.app.repositories.UserTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 04/09/2017.
 */
@Service
public class UserTypesService {
    @Autowired
    UserTypesRepository userTypesRepository;

    public List<UserTypes> getAllUsers() {
        List<UserTypes> users = new ArrayList<>();
        userTypesRepository.findAll().forEach(users::add);
        return users;
    }

    public UserTypes getUserTypeById(int id) {
        return userTypesRepository.findOne(id);
    }
}
