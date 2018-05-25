package com.partsnumbersystem.app.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Zeeshan on 20/08/2017.
 */
@Entity
@Table(name = "users")
public class Users {
    private int userId;
    private String userName;
    private String userLogin;
    private UserTypes userTypeId;
    private Timestamp dateLastLogin;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private int isActive;
    private int isDeleted;

    public Users(){}

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userLogin")
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userTypeId", insertable = true, updatable = true, nullable = true)
    public UserTypes getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserTypes userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Basic
    @Column(name = "dateLastLogin")
    public Timestamp getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Timestamp dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    @Basic
    @Column(name = "dateCreated")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "dateUpdated")
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
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

        Users users = (Users) o;

        if (userId != users.userId) return false;
        if (userTypeId != users.userTypeId) return false;
        if (isActive != users.isActive) return false;
        if (isDeleted != users.isDeleted) return false;
        if (userName != null ? !userName.equals(users.userName) : users.userName != null) return false;
        if (userLogin != null ? !userLogin.equals(users.userLogin) : users.userLogin != null) return false;
        if (dateCreated != null ? !dateCreated.equals(users.dateCreated) : users.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(users.dateUpdated) : users.dateUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + userTypeId.getUserTypeId();
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + isActive;
        result = 31 * result + isDeleted;
        return result;
    }
}
