package com.hakke.homework.bim493_assignment3;

import java.io.Serializable;

/**
 * Created by lenovo on 7.12.2016.
 */

public class User implements Serializable{
    public String fullname, email,passwd;
    public int age;


    public User(){}

    public User(String fullname, int age, String email, String passwd)
    {
        this.fullname = fullname;
        this.age = age;
        this.email = email;
        this.passwd = passwd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

