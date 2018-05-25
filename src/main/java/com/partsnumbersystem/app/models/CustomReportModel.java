package com.partsnumbersystem.app.models;

import com.partsnumbersystem.app.entities.DocumentTypes;
import com.partsnumbersystem.app.entities.PartStatus;
import com.partsnumbersystem.app.entities.Projects;

import java.util.List;

/**
 * Created by Zeeshan on 31/08/2017.
 */
public class CustomReportModel {

    private List<String> selectedColumns;
    private String keyword;
    private List<String> keywordSearchTokens;
    private String partNumber;
    private List<String> partStatus;
    private List<String> projectNumber;
    private List<String> projectStatus;
    private List<String> documentTypeNumber;
    private List<String> documentTypeStatus;

    public List<String> getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(List<String> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getKeywordSearchTokens() {
        return keywordSearchTokens;
    }

    public void setKeywordSearchTokens(List<String> keywordSearchTokens) {
        this.keywordSearchTokens = keywordSearchTokens;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public List<String> getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(List<String> partStatus) {
        this.partStatus = partStatus;
    }

    public List<String> getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(List<String> projectNumber) {
        this.projectNumber = projectNumber;
    }

    public List<String> getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(List<String> projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<String> getDocumentTypeNumber() {
        return documentTypeNumber;
    }

    public void setDocumentTypeNumber(List<String> documentTypeNumber) {
        this.documentTypeNumber = documentTypeNumber;
    }

    public List<String> getDocumentTypeStatus() {
        return documentTypeStatus;
    }

    public void setDocumentTypeStatus(List<String> documentTypeStatus) {
        this.documentTypeStatus = documentTypeStatus;
    }
}
