package com.partsnumbersystem.app.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.partsnumbersystem.app.entities.DocumentTypes;
import com.partsnumbersystem.app.entities.Users;
import com.partsnumbersystem.app.services.DocumentTypeService;
import com.partsnumbersystem.app.services.DocumentTypeStatusService;
import com.partsnumbersystem.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
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
@RequestMapping("/documenttype")
public class DocumentTypeController extends BaseController{

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DocumentTypeStatusService documentTypeStatusService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/list")
    public ModelAndView index(Model m, HttpServletRequest request){

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            ModelAndView model = new ModelAndView();

            m.addAttribute("documentTypeStatusList", documentTypeStatusService.getAllActiveNotDeletedDocumentTypeStatuses());
            m.addAttribute("documentTypesList", documentTypeService.getAllActiveNotDeletedDocumentTypes());

            if(request.getParameter("msg") != null){
                m.addAttribute("showMessage", request.getParameter("msg").toString());
            }

            model.setViewName("views/documentTypesList");

            return model;

        }else{
            return new ModelAndView(new RedirectView("/error/403"));
        }
    }

    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    public ResponseEntity<DocumentTypes> load(@PathVariable int id){
        return new ResponseEntity<DocumentTypes>(documentTypeService.getDocumentTypeById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/isavailable", method = RequestMethod.GET)
    @ResponseBody
    public String isAvailable(HttpServletRequest request){

        String result = "true";

        if(request.getParameter("documentTypeNumber") != null && request.getParameter("documentTypeNumber") != ""){
            if(documentTypeService.countByDocumentTypeNumber(request.getParameter("documentTypeNumber")) > 0){
                result = "false";
            }
        }

        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded", produces = "application/json")
    @ResponseBody
    public String save(DocumentTypes requestObject){

        String result;

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            try {

                DocumentTypes documentTypeObject = requestObject;

                System.out.println("Document Type Id: " + documentTypeObject.getDocumentTypeId());

                if (documentTypeObject.getDocumentTypeId() != 0) {

                    documentTypeObject = documentTypeService.getDocumentTypeById(documentTypeObject.getDocumentTypeId());

                    //Update required values
                    documentTypeObject.setDocumentTypeDescription(requestObject.getDocumentTypeDescription());
                    documentTypeObject.setComment(requestObject.getComment());
                    documentTypeObject.setTypeStatusId(requestObject.getTypeStatusId());

                    documentTypeObject.setUpdatedByUserId(userObj);
                    documentTypeObject.setDateUpdated(Timestamp.from(new Date().toInstant()));

                } else {

                    //Transform Document Type Number to Upper case only.
                    documentTypeObject.setDocumentTypeNumber(requestObject.getDocumentTypeNumber().toUpperCase());

                    documentTypeObject.setCreatedByUserId(userObj);

                    documentTypeObject.setDateCreated(Timestamp.from(new Date().toInstant()));
                    documentTypeObject.setIsActive(1);
                    documentTypeObject.setIsDeleted(0);
                }


                documentTypeService.save(documentTypeObject);

                String successMessage = "New Document Type has been saved successfully.";
                if(requestObject.getDocumentTypeId() != 0){
                   successMessage = "Document Type has been updated successfully.";
                }

                result = "{\"result\": \"Success\", " +
                        "\"message\": \"" + successMessage + "\"}";

            }catch (Exception ex){
                ex.printStackTrace();
                result = "{ \"result\": \"Failure\", \"message\": \"An error has occurred please try again.\"," +
                        " \"ErrorDetail\": \"" + ex.toString() + "\"}";
            }

        }else{
            result = "{\"result\": \"Failure\", \"message\": \"Only Administrators can save Document Type.\"," +
                    " \"ErrorDetail\": \"Only Administrators can save Document Type.\"}";
        }

        return result;
    }

}
