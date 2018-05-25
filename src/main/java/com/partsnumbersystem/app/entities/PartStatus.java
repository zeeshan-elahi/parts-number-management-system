package com.partsnumbersystem.app.entities;

import javax.persistence.*;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "part_status")
public class PartStatus {
    private int partStatusId;
    private String partStatusName;
    private int isActive;
    private int isDeleted;

    @Id
    @Column(name = "partStatusId")
    public int getPartStatusId() {
        return partStatusId;
    }

    public void setPartStatusId(int partStatusId) {
        this.partStatusId = partStatusId;
    }

    @Basic
    @Column(name = "partStatusName")
    public String getPartStatusName() {
        return partStatusName;
    }

    public void setPartStatusName(String partStatusName) {
        this.partStatusName = partStatusName;
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

        PartStatus that = (PartStatus) o;

        if (partStatusId != that.partStatusId) return false;
        if (isActive != that.isActive) return false;
        if (isDeleted != that.isDeleted) return false;
        if (partStatusName != null ? !partStatusName.equals(that.partStatusName) : that.partStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = partStatusId;
        result = 31 * result + (partStatusName != null ? partStatusName.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        return result;
    }
}
