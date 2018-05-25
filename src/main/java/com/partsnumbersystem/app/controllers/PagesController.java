package com.partsnumbersystem.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zeeshan on 07/09/2017.
 */
@Controller
@RequestMapping("/page")
public class PagesController extends BaseController {

    @RequestMapping("/help")
    public ModelAndView helpPage(Model m, HttpServletRequest request){

        ModelAndView model = new ModelAndView();

        model.setViewName("views/pages/help");

        return model;
    }

}
