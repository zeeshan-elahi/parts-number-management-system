package com.partsnumbersystem.app.entities;

import javax.persistence.*;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "project_status")
public class ProjectStatus {
    private int projectStatusId;
    private String projectStatusName;
    private int isActive;
    private int isDeleted;

    public ProjectStatus(){}

    public ProjectStatus(int projectStatusId){
        this.projectStatusId = projectStatusId;
    }

    @Id
    @Column(name = "projectStatusId")
    public int getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(int projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Basic
    @Column(name = "projectStatusName")
    public String getProjectStatusName() {
        return projectStatusName;
    }

    public void setProjectStatusName(String projectStatusName) {
        this.projectStatusName = projectStatusName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectStatus that = (ProjectStatus) o;

        if (projectStatusId != that.projectStatusId) return false;
        if (isActive != that.isActive) return false;
        if (isDeleted != that.isDeleted) return false;
        if (projectStatusName != null ? !projectStatusName.equals(that.projectStatusName) : that.projectStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectStatusId;
        result = 31 * result + (projectStatusName != null ? projectStatusName.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        return result;
    }
}
