package com.partsnumbersystem.app.controllers;

import com.partsnumbersystem.app.entities.UserTypes;
import com.partsnumbersystem.app.entities.Users;
import com.partsnumbersystem.app.services.UserTypesService;
import com.partsnumbersystem.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zeeshan on 04/09/2017.
 */
@Controller
@RequestMapping("/manage")
public class ManagementController extends BaseController {

    @Autowired
    UsersService usersService;

    @Autowired
    UserTypesService userTypesService;

    @RequestMapping("/users")
    public ModelAndView manageUsers(Model m, HttpServletRequest request){

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            ModelAndView model = new ModelAndView();

            m.addAttribute("usersList", usersService.getAllUsers());

            model.setViewName("views/usersList");

            return model;
        }else{
            return new ModelAndView(new RedirectView("/error/403"));
        }
    }

    @RequestMapping(value = "/changeusertype", method = RequestMethod.GET)
    @ResponseBody
    public String changeUserType(HttpServletRequest request){

        String result;

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            try {

                if (request.getParameter("userId") != null && request.getParameter("userId") != "") {

                    Users user = usersService.getUserById(Integer.parseInt(request.getParameter("userId")));

                    if (request.getParameter("newUserTypeId") != null && request.getParameter("newUserTypeId") != "") {

                        UserTypes userType = userTypesService.getUserTypeById(Integer.parseInt(request.getParameter("newUserTypeId")));
                        user.setUserTypeId(userType);
                    }
                    usersService.save(user);

                    result = "{\"result\": \"Success\"}";
                } else {
                    result = "{\"result\": \"Failure\", \"message\": \"Incorrect information have been received.\"," +
                            " \"ErrorDetail\": \"Incorrect information have been received.\"}";
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                result = "{ \"result\": \"Failure\", \"message\": \"An error has occurred please try again.\"," +
                        " \"ErrorDetail\": \"" + ex.toString() + "\"}";
            }
        }else{
            result = "{\"result\": \"Failure\", \"message\": \"Only Administrators can change user type.\"," +
                            " \"ErrorDetail\": \"Only Administrators can change user type.\"}";
        }

        return result;
    }

    @RequestMapping(value = "/changeacountstatus", method = RequestMethod.GET)
    @ResponseBody
    public String changeAccountStatus(HttpServletRequest request){

        String result;

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            try {

                if (request.getParameter("userId") != null && request.getParameter("userId") != "") {

                    Users user = usersService.getUserById(Integer.parseInt(request.getParameter("userId")));

                    if (request.getParameter("newAccountStatus") != null && request.getParameter("newAccountStatus") != "") {
                        user.setIsActive(Integer.parseInt(request.getParameter("newAccountStatus")));
                    }
                    usersService.save(user);

                    result = "{\"result\": \"Success\"}";
                } else {
                    result = "{\"result\": \"Failure\", \"message\": \"Incorrect information have been received.\"," +
                            " \"ErrorDetail\": \"Incorrect information have been received.\"}";
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                result = "{ \"result\": \"Failure\", \"message\": \"An error has occurred please try again.\"," +
                        " \"ErrorDetail\": \"" + ex.toString() + "\"}";
            }
        }else{
            result = "{\"result\": \"Failure\", \"message\": \"Only Administrators can change account status.\"," +
                            " \"ErrorDetail\": \"Only Administrators can change account status.\"}";
        }

        return result;
    }
}
