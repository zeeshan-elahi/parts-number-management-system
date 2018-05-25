package com.partsnumbersystem.app.entities;

import javax.persistence.*;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "user_types")
public class UserTypes {
    private int userTypeId;
    private String userTypeName;
    private int isActive;
    private int isDeleted;

    @Id
    @Column(name = "userTypeId")
    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Basic
    @Column(name = "userTypeName")
    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
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

        UserTypes userTypes = (UserTypes) o;

        if (userTypeId != userTypes.userTypeId) return false;
        if (isActive != userTypes.isActive) return false;
        if (isDeleted != userTypes.isDeleted) return false;
        if (userTypeName != null ? !userTypeName.equals(userTypes.userTypeName) : userTypes.userTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userTypeId;
        result = 31 * result + (userTypeName != null ? userTypeName.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        return result;
    }
}
