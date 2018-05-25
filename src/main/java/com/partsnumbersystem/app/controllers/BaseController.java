package com.partsnumbersystem.app.controllers;

import com.partsnumbersystem.app.entities.Users;
import com.partsnumbersystem.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Zeeshan on 25/08/2017.
 */
@Controller
public class BaseController {

    @Autowired
    UsersService usersService;

    public Users getLoggedInUser(){

        Users user = new Users();

        try {
            //Get current logged in user
            System.out.println("Getting user object from Spring security context.");
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof InetOrgPerson) {
                System.out.println("Getting user details from database");
                //We will be using DN as user login
                user = usersService.getUserByUserLogin(((InetOrgPerson) principal).getUsername());
            }else{
                System.out.println("Invalid user");
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

        //user = usersService.getUserById(1);

        return user;
    }

    @ModelAttribute
    public void addToModel(Model model) {

        Users user = this.getLoggedInUser();

        model.addAttribute("loggedInUser", user);
    }

}
