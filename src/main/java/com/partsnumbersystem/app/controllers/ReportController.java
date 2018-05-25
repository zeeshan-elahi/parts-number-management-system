package com.partsnumbersystem.app.controllers;

import com.partsnumbersystem.app.entities.*;
import com.partsnumbersystem.app.models.CustomReportModel;
import com.partsnumbersystem.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

/**
 * Created by Zeeshan on 18/08/2017.
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private PartsChangeLogService partsChangeLogService;

    @Autowired
    private PartService partService;

    @Autowired
    private PartStatusService partStatusService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectStatusService projectStatusService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DocumentTypeStatusService documentTypeStatusService;

    private Map<String, String> selectColumnsMap;
    private Map<String, String> keywordSearchTokensMap;
    private Map<String, String> partStatusMap;
    private Map<String, String> projectsMap;
    private Map<String, String> projectStatusesMap;
    private Map<String, String> documentTypesMap;
    private Map<String, String> documentTypeStatusesMap;

    @RequestMapping("/parts/changelog")
    public ModelAndView partsChangeLogReport(Model m){

        //Get logged in user
        Users userObj = this.getLoggedInUser();

        if(userObj.getUserTypeId().getUserTypeId() == 1) {

            ModelAndView model = new ModelAndView();

            m.addAttribute("partsChangeLogList", partsChangeLogService.getAllPartsChangeLog());
            model.setViewName("views/reports/partsChangeLog");

            return model;

        }else{
            return new ModelAndView(new RedirectView("/error/403"));
        }
    }

    @RequestMapping(value = "/parts/customreport", method = RequestMethod.GET)
    public ModelAndView partsCustomReportGet(Model m){

        ModelAndView model = new ModelAndView();

        CustomReportModel customReportModel = new CustomReportModel();
        
        this.populateFormReferenceData(model);

        m.addAttribute("hideDataGrid", true);
        //Pass Pre-selected Columns
        //Set and Pass Pre-selected Columns
        List<String> preSelectedColumns = new ArrayList();
        preSelectedColumns.add("partNumber");
        preSelectedColumns.add("partDescription");
        preSelectedColumns.add("partComment");
        preSelectedColumns.add("partStatus");
        customReportModel.setSelectedColumns(preSelectedColumns);
        m.addAttribute("selectedColumns", customReportModel.getSelectedColumns());

        m.addAttribute("partsList", partService.getMatchedParts(customReportModel));

        model.addObject("customReport", customReportModel);
        model.setViewName("views/reports/partsCustomReport");

        return model;
    }

    @RequestMapping(value = "/parts/customreport", method = RequestMethod.POST)
    public ModelAndView partsCustomReportPost(@ModelAttribute("customReport") CustomReportModel customReportModel, Model m){

        ModelAndView model = new ModelAndView();

        m.addAttribute("partsList", partService.getMatchedParts(customReportModel));

        this.populateFormReferenceData(model);

        model.addObject("customReport", customReportModel);

        m.addAttribute("hideDataGrid", false);
        m.addAttribute("selectedColumns", customReportModel.getSelectedColumns());

        model.setViewName("views/reports/partsCustomReport");

        return model;
    }

    private void populateFormReferenceData(ModelAndView model){

        selectColumnsMap = new LinkedHashMap<String, String>();
        selectColumnsMap.put("partNumber", "Part Number");
        selectColumnsMap.put("partDescription", "Part Description");
        selectColumnsMap.put("partComment", "Part Comment");
        selectColumnsMap.put("partStatus", "Part Status");
        selectColumnsMap.put("projectNumber", "Project Number");
        selectColumnsMap.put("projectCode", "Project Code");
        selectColumnsMap.put("projectDescription", "Project Description");
        selectColumnsMap.put("projectComment", "Project Comment");
        selectColumnsMap.put("projectStatus", "Project Status");
        selectColumnsMap.put("documentTypeNumber", "Document Type");
        selectColumnsMap.put("documentTypeDescription", "Document Type Description");
        selectColumnsMap.put("documentTypeComment", "Document Type Comment");
        selectColumnsMap.put("documentTypeStatus", "Document Type Status");
        selectColumnsMap.put("createdBy", "Created By");
        selectColumnsMap.put("dateCreated", "Date Created");
        selectColumnsMap.put("updatedBy", "Updated By");
        selectColumnsMap.put("dateUpdated", "Date Updated");
        model.addObject("selectColumnsMap", selectColumnsMap);

        keywordSearchTokensMap = new LinkedHashMap<String, String>();
        //keywordSearchTokensMap.put("partNumber", "Part Number");
        keywordSearchTokensMap.put("partDescription", "Part Description");
        keywordSearchTokensMap.put("partComment", "Part Comment");
        keywordSearchTokensMap.put("projectCode", "Project Code");
        keywordSearchTokensMap.put("projectDescription", "Project Description");
        keywordSearchTokensMap.put("projectComment", "Project Comment");
        keywordSearchTokensMap.put("documentTypeDescription", "Document Type Description");
        keywordSearchTokensMap.put("documentTypeComment", "Document Type Comment");
        model.addObject("keywordSearchTokensMap", keywordSearchTokensMap);

        List<PartStatus> partStatusList = partStatusService.getAllActiveNotDeletedPartStatuses();
        partStatusMap = new LinkedHashMap<String, String>();
        for(PartStatus partStatus : partStatusList){
            partStatusMap.put(String.valueOf(partStatus.getPartStatusId()), partStatus.getPartStatusName());
        }
        model.addObject("partStatusMap", partStatusMap);

        List<Projects> projectsList = projectService.getAllActiveNotDeletedProjects();
        projectsMap = new LinkedHashMap<String, String>();
        for(Projects projects: projectsList){
            projectsMap.put(String.valueOf(projects.getProjectId()), String.format("%04d", projects.getProjectNumber()) + " (" + projects.getProjectCode() + ")");
        }
        model.addObject("projectsMap", projectsMap);

        List<ProjectStatus> projectStatusList = projectStatusService.getAllActiveNotDeletedProjectStatuses();
        projectStatusesMap = new LinkedHashMap<String, String>();
        for(ProjectStatus projectStatus: projectStatusList){
            projectStatusesMap.put(String.valueOf(projectStatus.getProjectStatusId()), projectStatus.getProjectStatusName());
        }
        model.addObject("projectStatusesMap", projectStatusesMap);

        List<DocumentTypes> documentTypesList = documentTypeService.getAllActiveNotDeletedDocumentTypes();
        documentTypesMap = new LinkedHashMap<String, String>();
        for(DocumentTypes documentTypes : documentTypesList){
            documentTypesMap.put(String.valueOf(documentTypes.getDocumentTypeId()), documentTypes.getDocumentTypeNumber().toString());
        }
        model.addObject("documentTypesMap", documentTypesMap);

        List<DocumentTypeStatus> documentTypeStatusList = documentTypeStatusService.getAllActiveNotDeletedDocumentTypeStatuses();
        documentTypeStatusesMap = new LinkedHashMap<String, String>();
        for(DocumentTypeStatus documentTypeStatus: documentTypeStatusList){
            documentTypeStatusesMap.put(String.valueOf(documentTypeStatus.getTypeStatusId()), documentTypeStatus.getTypeStatusName());
        }
        model.addObject("documentTypeStatusesMap", documentTypeStatusesMap);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        /*binder.registerCustomEditor(Set.class, "documentTypeNumber", new CustomCollectionEditor(Set.class) {
            protected Object convertElement(Object element) {
                if (element instanceof DocumentTypes) {
                    return element;
                }
                if (element instanceof String) {
                    DocumentTypes documentTypes = documentTypesMap.get(element);
                    return documentTypes;
                }
                return null;
            }
        });*/
    }

}
