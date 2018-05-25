package com.partsnumbersystem.app.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "projects")
public class Projects {
    private int projectId;
    private String projectDescription;
    private int projectNumber;
    private Timestamp dateCreated;
    private Users createdByUserId;
    private Timestamp dateUpdated;
    private Users updatedByUserId;
    private int isActive;
    private int isDeleted;
    private int isLegacyProject;
    private String projectCode;
    private String comment;
    private ProjectStatus projectStatusId;

    @Id
    @Column(name = "projectId")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "projectDescription")
    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Basic
    @Column(name = "projectNumber")
    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    @Basic
    @Column(name = "dateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdByUserId", insertable = true, updatable = true, nullable = false)
    public Users getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Users createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    @Basic
    @Column(name = "dateUpdated")
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "updatedByUserId", insertable = false, updatable = true, nullable = true)
    public Users getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Users updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    @Basic
    @Column(name = "isActive")
    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "isDeleted")
    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Basic
    @Column(name = "isLegacyProject")
    public int getIsLegacyProject() {
        return isLegacyProject;
    }

    public void setIsLegacyProject(int isLegacyProject) {
        this.isLegacyProject = isLegacyProject;
    }

    @Basic
    @Column(name = "projectCode")
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectStatusId", insertable = true, updatable = true, nullable = true)
    public ProjectStatus getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(ProjectStatus projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projects projects = (Projects) o;

        if (projectId != projects.projectId) return false;
        if (projectNumber != projects.projectNumber) return false;
        if (createdByUserId != projects.createdByUserId) return false;
        if (isActive != projects.isActive) return false;
        if (isDeleted != projects.isDeleted) return false;
        if (isLegacyProject != projects.isLegacyProject) return false;
        if (projectStatusId != projects.projectStatusId) return false;
        if (projectDescription != null ? !projectDescription.equals(projects.projectDescription) : projects.projectDescription != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(projects.dateCreated) : projects.dateCreated != null)
            return false;
        if (dateUpdated != null ? !dateUpdated.equals(projects.dateUpdated) : projects.dateUpdated != null)
            return false;
        if (updatedByUserId != null ? !updatedByUserId.equals(projects.updatedByUserId) : projects.updatedByUserId != null)
            return false;
        if (projectCode != null ? !projectCode.equals(projects.projectCode) : projects.projectCode != null)
            return false;
        if (comment != null ? !comment.equals(projects.comment) : projects.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + (projectDescription != null ? projectDescription.hashCode() : 0);
        result = 31 * result + projectNumber;
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + createdByUserId.getUserId();
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (updatedByUserId != null ? updatedByUserId.getUserId() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        result = 31 * result + isLegacyProject;
        result = 31 * result + (projectCode != null ? projectCode.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + projectStatusId.getProjectStatusId();
        return result;
    }
}
