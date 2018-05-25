package com.partsnumbersystem.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zeeshan on 05/09/2017.
 */
@Controller
@RequestMapping("/error")
public class CustomErrorsController extends BaseController{

    @RequestMapping("/403")
    public ModelAndView error403(Model m, HttpServletRequest request){

        ModelAndView model = new ModelAndView();

        model.setViewName("views/errors/403");

        return model;
    }
}
