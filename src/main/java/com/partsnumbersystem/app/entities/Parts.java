package com.partsnumbersystem.app.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "parts")
public class Parts {
    private int partId;
    private int partNumber;
    private String partDescription;
    private String partRevisionNumber;
    private Timestamp dateCreated;
    private Users createdByUserId;
    private Timestamp dateUpdated;
    private Users updatedByUserId;
    private int isActive;
    private int isDeleted;
    private String partNumberFull;
    private String comment;
    private PartStatus partStatusId;
    private Projects projectId;
    private DocumentTypes documentTypeId;

    @Id
    @Column(name = "partId")
    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    @Basic
    @Column(name = "partNumber")
    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    @Basic
    @Column(name = "partDescription")
    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    @Basic
    @Column(name = "partRevisionNumber")
    public String getPartRevisionNumber() {
        return partRevisionNumber;
    }

    public void setPartRevisionNumber(String partRevisionNumber) {
        this.partRevisionNumber = partRevisionNumber;
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
    @Column(name = "partNumberFull")
    public String getPartNumberFull() {
        return partNumberFull;
    }

    public void setPartNumberFull(String partNumberFull) {
        this.partNumberFull = partNumberFull;
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
    @JoinColumn(name = "partStatusId", insertable = true, updatable = true, nullable = true)
    public PartStatus getPartStatusId() {
        return partStatusId;
    }

    public void setPartStatusId(PartStatus partStatusId) {
        this.partStatusId = partStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "projectId", insertable = true, updatable = true, nullable = true)
    public Projects getProjectId() {
        return projectId;
    }

    public void setProjectId(Projects projectId) {
        this.projectId = projectId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "documentTypeId", insertable = true, updatable = true, nullable = true)
    public DocumentTypes getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(DocumentTypes documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parts parts = (Parts) o;

        if (partId != parts.partId) return false;
        if (partNumber != parts.partNumber) return false;
        if (createdByUserId != parts.createdByUserId) return false;
        if (isActive != parts.isActive) return false;
        if (isDeleted != parts.isDeleted) return false;
        if (partStatusId != parts.partStatusId) return false;
        if (partDescription != null ? !partDescription.equals(parts.partDescription) : parts.partDescription != null)
            return false;
        if (partRevisionNumber != null ? !partRevisionNumber.equals(parts.partRevisionNumber) : parts.partRevisionNumber != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(parts.dateCreated) : parts.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(parts.dateUpdated) : parts.dateUpdated != null) return false;
        if (updatedByUserId != null ? !updatedByUserId.equals(parts.updatedByUserId) : parts.updatedByUserId != null)
            return false;
        if (partNumberFull != null ? !partNumberFull.equals(parts.partNumberFull) : parts.partNumberFull != null)
            return false;
        if (comment != null ? !comment.equals(parts.comment) : parts.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = partId;
        result = 31 * result + partNumber;
        result = 31 * result + (partDescription != null ? partDescription.hashCode() : 0);
        result = 31 * result + (partRevisionNumber != null ? partRevisionNumber.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + createdByUserId.getUserId();
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (updatedByUserId != null ? updatedByUserId.getUserId() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        result = 31 * result + (partNumberFull != null ? partNumberFull.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + partStatusId.getPartStatusId();
        return result;
    }
}
