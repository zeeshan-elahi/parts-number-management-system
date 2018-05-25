package com.partsnumbersystem.app.controllers;

import com.partsnumbersystem.app.entities.UserTypes;
import com.partsnumbersystem.app.entities.Users;
import com.partsnumbersystem.app.services.UserTypesService;
import com.partsnumbersystem.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Zeeshan on 23/08/2017.
 */
@Controller
public class LoginController {

    @Autowired
    UserDetailsContextMapper userDetailsContextMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserTypesService userTypesService;

    @RequestMapping("/login")
    public ModelAndView index(Model m, HttpServletRequest request){

        if(request.getParameter("blocked") != null){
            System.out.println("Blocked: " + request.getParameter("blocked"));
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("views/login");

        return model;
    }

    @RequestMapping("/processlogin")
    public String processLogin(Model m) {

        System.out.println("Login successful.");
        String redirect;

        //Map<String, Object> model
        //model = new HashMap<String, Object>();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof InetOrgPerson) {

            System.out.println("User Name: " + ((InetOrgPerson) principal).getUsername()
                    + " Uid: " + ((InetOrgPerson) principal).getUid()
                    + " DN: " + ((InetOrgPerson) principal).getDn()
                    + " Display Name: " + ((InetOrgPerson) principal).getDisplayName()
                    + " CN: " + ((InetOrgPerson) principal).getCn());

            //We will be using DN as user login
            Users users = usersService.getUserByUserLogin( ((InetOrgPerson) principal).getUsername() );

            if(users != null && users.getUserId() != 0){
                //Update user Login date if user already exist
                users.setDateLastLogin(Timestamp.from(new Date().toInstant()));
            }else{
                //Create new user Login
                users = new Users();
                users.setUserName((((InetOrgPerson) principal).getDisplayName() != null)? ((InetOrgPerson) principal).getDisplayName() : ((InetOrgPerson) principal).getUsername());
                users.setUserLogin(((InetOrgPerson) principal).getUsername());

                UserTypes userTypes = userTypesService.getUserTypeById(2);

                users.setUserTypeId(userTypes);
                users.setDateLastLogin(Timestamp.from(new Date().toInstant()));
                users.setDateCreated(Timestamp.from(new Date().toInstant()));
                users.setIsActive(1);
                users.setIsDeleted(0);
            }

            //Save or Update user
            usersService.save(users);
            if(users.getIsActive() == 1) {
                redirect = "/part/list";
            }else{
                redirect = "/accountblocked";
            }

        } else {
            //Invalid user force logout
            redirect = "/logout";
        }

        return "redirect:" + redirect;
    }

    @RequestMapping("/accountblocked")
    public ModelAndView accessDenied(Model m, HttpServletRequest request,
                                     HttpServletResponse response) {
        System.out.println("accessDenied() called");

        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request,
                    response, auth);

            ModelAndView model = new ModelAndView();
            model.setViewName("views/errors/accountBlocked");
            return model;

        }else{
            return new ModelAndView(new RedirectView("/login?accessDenied"));
        }
    }

    @RequestMapping(value = "/logout",
            method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        System.out.println("logout() called");
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request,
                    response, auth);
        }
        return "redirect:/login";
    }

}
