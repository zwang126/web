/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web1;

/**
 *
 * @author Zan_Wang
 */
public class userBean {

    private int userId;
    private String username;
    private String password;
    private String email;
    private int grade;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
   /*
    public userBean(int userId, String username, String password, String email, int grade) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.grade = grade;
    }
*/
}
