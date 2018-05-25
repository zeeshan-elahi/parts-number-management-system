package com.partsnumbersystem.app.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zeeshan on 22/08/2017.
 */
@Entity
@Table(name = "parts_change_log")
public class PartsChangeLog {

    private int partChangeLogId;
    private Parts partId;
    private int partNumber;
    private String partDescription;
    private String partRevisionNumber;
    private int isActive;
    private int isDeleted;
    private String partNumberFull;
    private String comment;
    private PartStatus partStatusId;
    private Projects projectId;
    private DocumentTypes documentTypeId;

    private int oldPartNumber;
    private String oldPartDescription;
    private String oldPartRevisionNumber;
    private int oldIsActive;
    private int oldIsDeleted;
    private String oldPartNumberFull;
    private String oldComment;
    private PartStatus oldPartStatusId;
    private Projects oldProjectId;
    private DocumentTypes oldDocumentTypeId;

    private Timestamp dateUpdated;
    private Users updatedByUserId;

    public PartsChangeLog(){}

    @Id
    @Column(name = "partChangeLogId")
    public int getPartChangeLogId() {
        return partChangeLogId;
    }

    public void setPartChangeLogId(int partChangeLogId) {
        this.partChangeLogId = partChangeLogId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "partId", insertable = true, updatable = true, nullable = true)
    public Parts getPartId() {
        return partId;
    }

    public void setPartId(Parts partId) {
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

    @Basic
    @Column(name = "oldPartNumber")
    public int getOldPartNumber() {
        return oldPartNumber;
    }

    public void setOldPartNumber(int oldPartNumber) {
        this.oldPartNumber = oldPartNumber;
    }

    @Basic
    @Column(name = "oldPartDescription")
    public String getOldPartDescription() {
        return oldPartDescription;
    }

    public void setOldPartDescription(String oldPartDescription) {
        this.oldPartDescription = oldPartDescription;
    }

    @Basic
    @Column(name = "oldPartRevisionNumber")
    public String getOldPartRevisionNumber() {
        return oldPartRevisionNumber;
    }

    public void setOldPartRevisionNumber(String oldPartRevisionNumber) {
        this.oldPartRevisionNumber = oldPartRevisionNumber;
    }

    @Basic
    @Column(name = "oldIsActive")
    public int getOldIsActive() {
        return oldIsActive;
    }

    public void setOldIsActive(int oldIsActive) {
        this.oldIsActive = oldIsActive;
    }

    @Basic
    @Column(name = "oldIsDeleted")
    public int getOldIsDeleted() {
        return oldIsDeleted;
    }

    public void setOldIsDeleted(int oldIsDeleted) {
        this.oldIsDeleted = oldIsDeleted;
    }

    @Basic
    @Column(name = "oldPartNumberFull")
    public String getOldPartNumberFull() {
        return oldPartNumberFull;
    }

    public void setOldPartNumberFull(String oldPartNumberFull) {
        this.oldPartNumberFull = oldPartNumberFull;
    }

    @Basic
    @Column(name = "oldComment")
    public String getOldComment() {
        return oldComment;
    }

    public void setOldComment(String oldComment) {
        this.oldComment = oldComment;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "oldPartStatusId", insertable = true, updatable = true, nullable = true)
    public PartStatus getOldPartStatusId() {
        return oldPartStatusId;
    }

    public void setOldPartStatusId(PartStatus oldPartStatusId) {
        this.oldPartStatusId = oldPartStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "oldProjectId", insertable = true, updatable = true, nullable = true)
    public Projects getOldProjectId() {
        return oldProjectId;
    }

    public void setOldProjectId(Projects oldProjectId) {
        this.oldProjectId = oldProjectId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "oldDocumentTypeId", insertable = true, updatable = true, nullable = true)
    public DocumentTypes getOldDocumentTypeId() {
        return oldDocumentTypeId;
    }

    public void setOldDocumentTypeId(DocumentTypes oldDocumentTypeId) {
        this.oldDocumentTypeId = oldDocumentTypeId;
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
    @JoinColumn(name = "updatedByUserId", insertable = true, updatable = true, nullable = true)
    public Users getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Users updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartsChangeLog partsChangeLog = (PartsChangeLog) o;

        if (partId != partsChangeLog.partId) return false;
        if (partNumber != partsChangeLog.partNumber) return false;
        if (isActive != partsChangeLog.isActive) return false;
        if (isDeleted != partsChangeLog.isDeleted) return false;
        if (partStatusId != partsChangeLog.partStatusId) return false;
        if (partDescription != null ? !partDescription.equals(partsChangeLog.partDescription) : partsChangeLog.partDescription != null)
            return false;
        if (partRevisionNumber != null ? !partRevisionNumber.equals(partsChangeLog.partRevisionNumber) : partsChangeLog.partRevisionNumber != null)
            return false;
        if (partNumberFull != null ? !partNumberFull.equals(partsChangeLog.partNumberFull) : partsChangeLog.partNumberFull != null)
            return false;
        if (comment != null ? !comment.equals(partsChangeLog.comment) : partsChangeLog.comment != null) return false;

        if (oldPartNumber != partsChangeLog.oldPartNumber) return false;
        if (oldIsActive != partsChangeLog.oldIsActive) return false;
        if (oldIsDeleted != partsChangeLog.oldIsDeleted) return false;
        if (oldPartStatusId != partsChangeLog.oldPartStatusId) return false;
        if (oldPartDescription != null ? !oldPartDescription.equals(partsChangeLog.oldPartDescription) : partsChangeLog.oldPartDescription != null)
            return false;
        if (oldPartRevisionNumber != null ? !oldPartRevisionNumber.equals(partsChangeLog.oldPartRevisionNumber) : partsChangeLog.oldPartRevisionNumber != null)
            return false;
        if (oldPartNumberFull != null ? !oldPartNumberFull.equals(partsChangeLog.oldPartNumberFull) : partsChangeLog.oldPartNumberFull != null)
            return false;
        if (oldComment != null ? !oldComment.equals(partsChangeLog.oldComment) : partsChangeLog.oldComment != null) return false;

        if (dateUpdated != null ? !dateUpdated.equals(partsChangeLog.dateUpdated) : partsChangeLog.dateUpdated != null) return false;
        if (updatedByUserId != null ? !updatedByUserId.equals(partsChangeLog.updatedByUserId) : partsChangeLog.updatedByUserId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = partChangeLogId;
        result = 31 * result + partId.getPartId();

        result = 31 * result + partNumber;
        result = 31 * result + (partDescription != null ? partDescription.hashCode() : 0);
        result = 31 * result + (partRevisionNumber != null ? partRevisionNumber.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        result = 31 * result + (partNumberFull != null ? partNumberFull.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + partStatusId.getPartStatusId();

        result = 31 * result + oldPartNumber;
        result = 31 * result + (oldPartDescription != null ? oldPartDescription.hashCode() : 0);
        result = 31 * result + (oldPartRevisionNumber != null ? oldPartRevisionNumber.hashCode() : 0);
        result = 31 * result + oldIsActive;
        result = 31 * result + oldIsDeleted;
        result = 31 * result + (oldPartNumberFull != null ? oldPartNumberFull.hashCode() : 0);
        result = 31 * result + (oldComment != null ? oldComment.hashCode() : 0);
        result = 31 * result + oldPartStatusId.getPartStatusId();

        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (updatedByUserId != null ? updatedByUserId.getUserId() : 0);

        return result;
    }
}
