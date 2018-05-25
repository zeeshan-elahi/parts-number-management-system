package com.partsnumbersystem.app.services;

import com.partsnumbersystem.app.entities.DocumentTypes;
import com.partsnumbersystem.app.entities.Parts;
import com.partsnumbersystem.app.entities.Projects;
import com.partsnumbersystem.app.models.CustomReportModel;
import com.partsnumbersystem.app.repositories.CustomPartsRepository;
import com.partsnumbersystem.app.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private CustomPartsRepository customPartsRepository;

    public List<Parts> getAllParts() {
        List<Parts> parts = new ArrayList<>();
        partRepository.findAll().forEach(parts::add);
        return parts;
    }

    public List<Parts> getAllActiveNotDeletedParts() {
        List<Parts> parts = new ArrayList<>();
        partRepository.findByIsActiveAndIsDeleted(1,0).forEach(parts::add);
        return parts;
    }

    public int getMaxPartNumberByProjectIdAndDocumentTypeId(Projects projectId, DocumentTypes documentTypeId){
        Parts partObj = partRepository.findTopByProjectIdAndDocumentTypeIdOrderByPartNumberDesc(projectId, documentTypeId);

        return (partObj != null)? partObj.getPartNumber() : 0;
    }

    public Parts getPartById(int id) {
        return partRepository.findOne(id);
    }

    public void updatePartById(int id, Parts part) {
        partRepository.save(part);
    }

    public void save(Parts part) {
        partRepository.save(part);
    }

    public void deletePart(int id) {
        partRepository.delete(id);
    }

    public List<Parts> getMatchedParts(CustomReportModel customReportModel){

        List<Parts> parts = new ArrayList<>();

        //Create Search Query
        String searchQuery = "Where p.isActive = 1 AND p.isDeleted = 0";

        /* Keyword Lookup */
        if(customReportModel.getKeyword() != null && customReportModel.getKeyword() != ""){

            String keyword = customReportModel.getKeyword();

            if(customReportModel.getKeywordSearchTokens() != null && customReportModel.getKeywordSearchTokens().size() > 0){
                searchQuery = searchQuery + " AND (";
                for(int i = 0; i < customReportModel.getKeywordSearchTokens().size(); i++){
                    String orOperator = (i > 0)? " OR " : "";
                    switch (customReportModel.getKeywordSearchTokens().get(i)){
                        case "partNumber":
                            searchQuery = searchQuery + orOperator + "p.partNumberFull LIKE '%" + keyword + "%'";
                            break;
                        case "partDescription":
                            searchQuery = searchQuery + orOperator + "p.partDescription LIKE '%" + keyword + "%'";
                            break;
                        case "partComment":
                            searchQuery = searchQuery + orOperator + "p.comment LIKE '%" + keyword + "%'";
                            break;
                        case "projectCode":
                            searchQuery = searchQuery + orOperator + "project.projectCode LIKE '%" + keyword + "%'";
                            break;
                        case "projectDescription":
                            searchQuery = searchQuery + orOperator + "project.projectDescription LIKE '%" + keyword + "%'";
                            break;
                        case "projectComment":
                            searchQuery = searchQuery + orOperator + "project.comment LIKE '%" + keyword + "%'";
                            break;
                        case "documentTypeDescription":
                            searchQuery = searchQuery + orOperator + "documentType.documentTypeDescription LIKE '%" + keyword + "%'";
                            break;
                        case "documentTypeComment":
                            searchQuery = searchQuery + orOperator + "documentType.comment LIKE '%" + keyword + "%'";
                            break;
                    }
                }
                searchQuery = searchQuery + ") ";
            }

        }
        /* Keyword Lookup */

        /* Part Number Lookup */
        if(customReportModel.getPartNumber() != null && customReportModel.getPartNumber() != ""){
            searchQuery = searchQuery + " AND p.partNumberFull LIKE '%" + customReportModel.getPartNumber() + "%'";
        }
        /* Part Number Lookup */

        /* Part Status Lookup */
        if(customReportModel.getPartStatus() != null && customReportModel.getPartStatus().size() > 0){
            searchQuery = searchQuery + " AND p.partStatusId IN (" + String.join(", ", customReportModel.getPartStatus()) + ")";
        }
        /* Part Status Lookup */

        /* Project ID Lookup */
        if(customReportModel.getProjectNumber() != null && customReportModel.getProjectNumber().size() > 0){
            searchQuery = searchQuery + " AND p.projectId IN (" + String.join(", ", customReportModel.getProjectNumber()) + ")";
        }
        /* Project ID Lookup */

        /* Project Status Lookup */
        if(customReportModel.getProjectStatus() != null && customReportModel.getProjectStatus().size() > 0){
            searchQuery = searchQuery + " AND project.projectStatusId IN (" + String.join(", ", customReportModel.getProjectStatus()) + ")";
        }
        /* Project Status Lookup */

        /* Document Type ID Lookup */
        if(customReportModel.getDocumentTypeNumber() != null && customReportModel.getDocumentTypeNumber().size() > 0){
            searchQuery = searchQuery + " AND p.documentTypeId IN (" + String.join(", ", customReportModel.getDocumentTypeNumber()) + ")";
        }
        /* Document Type ID Lookup */

        /* Document Type Status Lookup */
        if(customReportModel.getDocumentTypeStatus() != null && customReportModel.getDocumentTypeStatus().size() > 0){
            searchQuery = searchQuery + " AND documentType.typeStatusId IN (" + String.join(", ", customReportModel.getDocumentTypeStatus()) + ")";
        }
        /* Document Type Status Lookup */

        String query = "Select p From Parts p " +
                    " INNER JOIN p.projectId project " +
                    " INNER JOIN p.documentTypeId documentType " +
                    searchQuery;

        customPartsRepository.getMatchedParts(query).forEach(parts::add);
        return parts;
    }

}
