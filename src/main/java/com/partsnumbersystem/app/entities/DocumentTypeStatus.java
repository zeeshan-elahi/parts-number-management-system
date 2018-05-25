package com.partsnumbersystem.app.entities;

import javax.persistence.*;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "document_type_status")
public class DocumentTypeStatus {
    private int typeStatusId;
    private String typeStatusName;
    private int isActive;
    private int isDeleted;

    public DocumentTypeStatus(){}

    public DocumentTypeStatus(int typeStatusId){
        this.typeStatusId = typeStatusId;
    }

    @Id
    @Column(name = "typeStatusId")
    public int getTypeStatusId() {
        return typeStatusId;
    }

    public void setTypeStatusId(int typeStatusId) {
        this.typeStatusId = typeStatusId;
    }

    @Basic
    @Column(name = "typeStatusName")
    public String getTypeStatusName() {
        return typeStatusName;
    }

    public void setTypeStatusName(String typeStatusName) {
        this.typeStatusName = typeStatusName;
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

        DocumentTypeStatus that = (DocumentTypeStatus) o;

        if (typeStatusId != that.typeStatusId) return false;
        if (isActive != that.isActive) return false;
        if (isDeleted != that.isDeleted) return false;
        if (typeStatusName != null ? !typeStatusName.equals(that.typeStatusName) : that.typeStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeStatusId;
        result = 31 * result + (typeStatusName != null ? typeStatusName.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        return result;
    }
}
