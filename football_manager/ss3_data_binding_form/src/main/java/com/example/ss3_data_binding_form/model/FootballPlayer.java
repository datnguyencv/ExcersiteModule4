package com.example.ss3_data_binding_form.model;

public class FootballPlayer {
    private Integer code_id;
    private String name;
    private String date_of_birth;
    private String experience;
    private String position;

    public FootballPlayer() {
    }

    public FootballPlayer(Integer code_id, String name, String date_of_birth, String experience, String position) {
        this.code_id = code_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.experience = experience;
        this.position = position;
    }

    public Integer getCode_id() {
        return code_id;
    }

    public void setCode_id(Integer code_id) {
        this.code_id = code_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
