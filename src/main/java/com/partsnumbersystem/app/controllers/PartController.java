package com.partsnumbersystem.app.controllers;

import com.partsnumbersystem.app.entities.*;
import com.partsnumbersystem.app.services.*;
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

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Zeeshan on 18/08/2017.
 */
@Controller
@RequestMapping("/part")
public class PartController extends BaseController {

    @Autowired
    private PartService partService;

    @Autowired
    private PartStatusService partStatusService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private PartsChangeLogService partsChangeLogService;

    private List<ProjectStatus> projectStatusCollection = new ArrayList<>();
    private List<DocumentTypeStatus> documentTypeStatusList = new ArrayList<>();


    @RequestMapping("/list")
    public ModelAndView index(Model m, HttpServletRequest request){

        ModelAndView model = new ModelAndView();

        //Populate Project Status Array
        projectStatusCollection.add(new ProjectStatus(4));

        //Populate Document Type Status Array
        documentTypeStatusList.add(new DocumentTypeStatus(3));

        m.addAttribute("projectsList", projectService.getAllProjectsByProjectStatusIdNotInAndIsActiveAndIsDeleted(projectStatusCollection,1,0));
        m.addAttribute("documentTypesList", documentTypeService.getAllDocumentTypesByTypeStatusIdNotInAndIsActiveAndIsDeleted(documentTypeStatusList, 1, 0));
        m.addAttribute("partStatusList", partStatusService.getAllActiveNotDeletedPartStatuses());
        m.addAttribute("partsList", partService.getAllActiveNotDeletedParts());

        if(request.getParameter("msg") != null){
            m.addAttribute("showMessage", request.getParameter("msg").toString());
        }

        model.setViewName("views/partsList");

        return model;
    }

    @RequestMapping(value = "/load/{id}", method = RequestMethod.GET)
    public ResponseEntity<Parts> load(@PathVariable int id){
        return new ResponseEntity<Parts>(partService.getPartById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded", produces = "application/json")
    @ResponseBody
    public String save(Parts requestObject){

        String result;

        try {

            Parts partObject = requestObject;

            PartsChangeLog partsChangeLogObject = new PartsChangeLog();

            //Copy values in request to change log
            partsChangeLogObject.setPartDescription(requestObject.getPartDescription());
            partsChangeLogObject.setComment(requestObject.getComment());
            partsChangeLogObject.setPartStatusId(requestObject.getPartStatusId());
            partsChangeLogObject.setProjectId(requestObject.getProjectId());
            partsChangeLogObject.setDocumentTypeId(requestObject.getDocumentTypeId());

            //Get logged in user
            Users userObj = this.getLoggedInUser();

            System.out.println("Part Id: " + partObject.getPartId());

            if (partObject.getPartId() != 0) {

                partObject = partService.getPartById(partObject.getPartId());

                //Copy values received from Database
                partsChangeLogObject.setOldPartNumber(partObject.getPartNumber());
                partsChangeLogObject.setOldPartNumberFull(partObject.getPartNumberFull());
                partsChangeLogObject.setOldPartDescription(partObject.getPartDescription());
                partsChangeLogObject.setOldComment(partObject.getComment());
                partsChangeLogObject.setOldPartRevisionNumber(partObject.getPartRevisionNumber());
                partsChangeLogObject.setOldPartStatusId(partObject.getPartStatusId());
                partsChangeLogObject.setOldProjectId(partObject.getProjectId());
                partsChangeLogObject.setOldDocumentTypeId(partObject.getDocumentTypeId());
                partsChangeLogObject.setOldIsActive(partObject.getIsActive());
                partsChangeLogObject.setOldIsDeleted(partObject.getIsDeleted());

                //Update required values
                partObject.setPartDescription(requestObject.getPartDescription());
                partObject.setComment(requestObject.getComment());
                partObject.setPartStatusId(requestObject.getPartStatusId());

                partObject.setUpdatedByUserId(userObj);
                partObject.setDateUpdated(Timestamp.from(new Date().toInstant()));

                //Also copy non-changed values in Change log
                partsChangeLogObject.setPartId(partObject);
                partsChangeLogObject.setPartNumber(partObject.getPartNumber());
                partsChangeLogObject.setPartDescription(partObject.getPartDescription());
                partsChangeLogObject.setPartNumberFull(partObject.getPartNumberFull());
                partsChangeLogObject.setPartRevisionNumber(partObject.getPartRevisionNumber());
                partsChangeLogObject.setProjectId(partObject.getProjectId());
                partsChangeLogObject.setDocumentTypeId(partObject.getDocumentTypeId());
                partsChangeLogObject.setIsActive(partObject.getIsActive());
                partsChangeLogObject.setIsDeleted(partObject.getIsDeleted());

                partsChangeLogObject.setUpdatedByUserId(partObject.getUpdatedByUserId());
                partsChangeLogObject.setDateUpdated(partObject.getDateUpdated());

                //Save Part Change Log
                partsChangeLogService.save(partsChangeLogObject);

            } else {

                //get current part number of given document type and project id.
                int maxPartNumber = partService.getMaxPartNumberByProjectIdAndDocumentTypeId(
                        partObject.getProjectId(), partObject.getDocumentTypeId());
                System.out.println("Max Part Number: " + maxPartNumber);
                //maxPartNumber = (maxPartNumber < 1000)? 1000 : maxPartNumber;

                partObject.setPartNumber(maxPartNumber + 1);

                System.out.println("Generated Part Number: " + partObject.getPartNumber());
                String partNumberFull = String.format("%04d", partObject.getProjectId().getProjectNumber()) + "-" +
                        partObject.getDocumentTypeId().getDocumentTypeNumber() + "-" +
                        String.format("%04d", partObject.getPartNumber());
                System.out.println("Generated Part Number Full: " + partNumberFull);

                partObject.setPartNumberFull(partNumberFull);

                partObject.setPartRevisionNumber("00");

                partObject.setCreatedByUserId(userObj);

                partObject.setDateCreated(Timestamp.from(new Date().toInstant()));
                partObject.setIsActive(1);
                partObject.setIsDeleted(0);
            }

            //Save Part
            partService.save(partObject);

            String successMessage = "New part has been saved successfully.";
            if(requestObject.getPartId() != 0){
               successMessage = "Part has been updated successfully.";
            }

            result = "{\"result\": \"Success\", \"partNumberFull\": \"" + partObject.getPartNumberFull() + "\"," +
                    "\"message\": \"" + successMessage + "\"}";

        }catch (Exception ex){
            result = "{ \"result\": \"Failure\", \"message\": \"An error has been occurred. Please try again.\"," +
                    " \"ErrorDetail\": \"" + ex.toString() + "\"}";
        }

        return result;
    }
    
}
