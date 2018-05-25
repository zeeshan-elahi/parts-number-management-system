package com.partsnumbersystem.app.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "document_types")
public class DocumentTypes {
    private int documentTypeId;
    private String documentTypeDescription;
    private String documentTypeNumber;
    private Timestamp dateCreated;
    private Users createdByUserId;
    private Timestamp dateUpdated;
    private Users updatedByUserId;
    private int isActive;
    private int isDeleted;
    private String comment;
    private DocumentTypeStatus typeStatusId;

    public DocumentTypes(){}

    public DocumentTypes(int documentTypeId,
            String documentTypeDescription,
            String documentTypeNumber,
            String comment,
            DocumentTypeStatus typeStatusId,
            Timestamp dateCreated,
            Users createdByUserId){
        this.documentTypeId = documentTypeId;
        this.documentTypeDescription = documentTypeDescription;
        this.documentTypeNumber = documentTypeNumber;
        this.comment = comment;
        this.typeStatusId = typeStatusId;
        this.dateCreated = dateCreated;
        this.createdByUserId = createdByUserId;
    }

    @Id
    @Column(name = "documentTypeId")
    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    @Basic
    @Column(name = "documentTypeDescription")
    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    @Basic
    @Column(name = "documentTypeNumber")
    public String getDocumentTypeNumber() {
        return documentTypeNumber;
    }

    public void setDocumentTypeNumber(String documentTypeNumber) {
        this.documentTypeNumber = documentTypeNumber;
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
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "typeStatusId", insertable = true, updatable = true, nullable = true)
    public DocumentTypeStatus getTypeStatusId() {
        return typeStatusId;
    }

    public void setTypeStatusId(DocumentTypeStatus typeStatusId) {
        this.typeStatusId = typeStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentTypes that = (DocumentTypes) o;

        if (documentTypeId != that.documentTypeId) return false;
        if (createdByUserId != that.createdByUserId) return false;
        if (isActive != that.isActive) return false;
        if (isDeleted != that.isDeleted) return false;
        if (typeStatusId != that.typeStatusId) return false;
        if (documentTypeDescription != null ? !documentTypeDescription.equals(that.documentTypeDescription) : that.documentTypeDescription != null)
            return false;
        if (documentTypeNumber != null ? !documentTypeNumber.equals(that.documentTypeNumber) : that.documentTypeNumber != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;
        if (updatedByUserId != null ? !updatedByUserId.equals(that.updatedByUserId) : that.updatedByUserId != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = documentTypeId;
        result = 31 * result + (documentTypeDescription != null ? documentTypeDescription.hashCode() : 0);
        result = 31 * result + (documentTypeNumber != null ? documentTypeNumber.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + createdByUserId.getUserId();
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (updatedByUserId != null ? updatedByUserId.getUserId() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + typeStatusId.getTypeStatusId();
        return result;
    }
}
