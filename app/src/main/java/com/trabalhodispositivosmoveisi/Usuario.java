package com.trabalhodispositivosmoveisi;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by rafael on 27/05/2017.
 */

public class Usuario extends RealmObject{

    private static int newId = 1;

    private int id;
    private String name;
    private String email;
    private String password;
    private String birthday;
    private String gender;
    private float heigh;
    private float weigh;
    private int activityLevel;

    public Usuario(String name, String email, String password, String birthday, String gender, float heigh, float weigh, int activityLevel) {

        this.id = this.newId;
        this.newId++;

        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.heigh = heigh;
        this.weigh = weigh;
        this.activityLevel = activityLevel;
    }

    public Usuario() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeigh() {
        return heigh;
    }

    public void setHeigh(float heigh) {
        this.heigh = heigh;
    }

    public float getWeigh() {
        return weigh;
    }

    public void setWeigh(float weigh) {
        this.weigh = weigh;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public int getId() {
        return id;
    }
}
