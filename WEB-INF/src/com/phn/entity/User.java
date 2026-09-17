package com.phn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_user")
public class User {
    private int id;
    private String username;
    private String usernickname;
    private String userpassword;
    private String userphone;
    private String userqq;
    private int userstatus;
    private int reward_points;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 16)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(length = 30)
    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    @Column(length = 16)
    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Column(length = 20)
    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    @Column(length = 20)
    public String getUserqq() {
        return userqq;
    }

    public void setUserqq(String userqq) {
        this.userqq = userqq;
    }

    public int getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(int userstatus) {
        this.userstatus = userstatus;
    }

    public int getReward_points() {
        return reward_points;
    }

    public void setReward_points(int reward_points) {
        this.reward_points = reward_points;
    }
}
