package com.partsnumbersystem.app.controllers;

import com.partsnumbersystem.app.entities.Projects;
import com.partsnumbersystem.app.entities.Users;
import com.partsnumbersystem.app.repositories.ProjectRepository;
import com.partsnumbersystem.app.services.ProjectService;
import com.partsnumbersystem.app.services.ProjectStatusService;
import com.partsnumbersystem.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Zeeshan on 18/08/2017.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectStatusService projectStatusService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/list")
    public ModelAndView index(Model m, HttpServletRequest request){

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            ModelAndView model = new ModelAndView();

            m.addAttribute("projectStatusList", projectStatusService.getAllActiveNotDeletedProjectStatuses());
            m.addAttribute("projectsList", projectService.getAllActiveNotDeletedProjects());

            if(request.getParameter("msg") != null){
                m.addAttribute("showMessage", request.getParameter("msg").toString());
            }

            model.setViewName("views/projectsList");

            return model;

        }else{
            return new ModelAndView(new RedirectView("/error/403"));
        }
    }

    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    public ResponseEntity<Projects> load(@PathVariable int id){
        return new ResponseEntity<Projects>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/isavailable", method = RequestMethod.GET)
    @ResponseBody
    public String isAvailable(HttpServletRequest request){

        String result = "true";

        if(request.getParameter("projectNumber") != null && request.getParameter("projectNumber") != "0"){
            if(projectService.countByProjectNumber(Integer.parseInt(request.getParameter("projectNumber"))) > 0){
                result = "false";
            }
        }else if(request.getParameter("projectCode") != null && request.getParameter("projectCode") != ""){
            if(projectService.countByProjectIdNotAndProjectCode(Integer.parseInt(request.getParameter("projectId")), request.getParameter("projectCode") ) > 0){
                result = "false";
            }
        }

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded", produces = "application/json")
    @ResponseBody
    public String save(Projects requestObject){

        String result;

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            try {

                Projects projectObject = requestObject;

                System.out.println("Project Id: " + projectObject.getProjectId());

                if (projectObject.getProjectId() != 0) {

                    projectObject = projectService.getProjectById(projectObject.getProjectId());

                    //Update required values
                    projectObject.setProjectCode(requestObject.getProjectCode().toUpperCase());
                    projectObject.setProjectDescription(requestObject.getProjectDescription());
                    projectObject.setComment(requestObject.getComment());
                    projectObject.setProjectStatusId(requestObject.getProjectStatusId());

                    projectObject.setUpdatedByUserId(userObj);
                    projectObject.setDateUpdated(Timestamp.from(new Date().toInstant()));

                } else {

                    //Check if not a legacy project
                    if(requestObject.getIsLegacyProject() == 0){
                        System.out.println("Non Legacy Project");
                        //Get current max project number
                        int maxProjectNumber = projectService.getMaxProjectNumber();
                        maxProjectNumber = (maxProjectNumber < 999)? 999 : maxProjectNumber;
                        projectObject.setProjectNumber(maxProjectNumber + 1);
                        System.out.println("Generated Number: " + projectObject.getProjectNumber());
                    }

                    projectObject.setProjectCode(projectObject.getProjectCode().toUpperCase());

                    projectObject.setCreatedByUserId(userObj);

                    projectObject.setDateCreated(Timestamp.from(new Date().toInstant()));
                    projectObject.setIsActive(1);
                    projectObject.setIsDeleted(0);
                }

                projectService.save(projectObject);

                String successMessage = "New Project has been saved successfully.";
                //Check if a legacy project
                if(requestObject.getIsLegacyProject() != 0){
                    successMessage = "Legacy Project has been saved successfully.";
                }

                if(requestObject.getProjectId() != 0){
                   successMessage = "Project has been updated successfully.";
                }

                result = "{\"result\": \"Success\", \"projectNumber\": \"" + String.format("%04d", projectObject.getProjectNumber()) + "\"," +
                        "\"message\": \"" + successMessage + "\"}";

            }catch (Exception ex){
                result = "{ \"result\": \"Failure\", \"message\": \"An error has occurred please try again.\"," +
                        " \"ErrorDetail\": \"" + ex.toString() + "\"}";
            }

        }else{
            result = "{\"result\": \"Failure\", \"message\": \"Only Administrators can save Project.\"," +
                    " \"ErrorDetail\": \"Only Administrators can save Project.\"}";
        }

        return result;
    }
    
}
