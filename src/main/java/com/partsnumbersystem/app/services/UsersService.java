package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.Users;
import com.partsnumbersystem.app.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    public Users getUserById(int id) {
        return usersRepository.findOne(id);
    }

    public Users getUserByUserLogin(String userLogin){
        return usersRepository.getFirstByUserLogin(userLogin);
    }

    public void updateUserById(int id, Users users) {
        usersRepository.save(users);
    }

    public void save(Users users) {
        usersRepository.save(users);
    }

    public void deleteUser(int id) {
        usersRepository.delete(id);
    }

}
