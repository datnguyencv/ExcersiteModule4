package com.example.football_management.dto;

import com.example.football_management.model.Teams;
import com.example.football_management.utils.annotation.YearOldValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FootballPlayerDTO {
    private Integer id;
    @Size(min = 5, max = 100, message = "Only from 5 to 100 characters")
    @NotBlank(message = "Enter Name please")
    @Pattern(regexp = "^(?!.*\\d)[\\p{Lu}][\\p{Ll}]*([\\s][\\p{Lu}][\\p{Ll}]*)*$|^([\\p{Lu}][\\p{Ll}]*)$", message = "Name Player need format. Ex: Nguyen Van A, Nguyễn Văn A")
    private String name;
    @YearOldValid
    private String dateOfBirth;
    @Min(value = 1, message = "Only positive integer greater than 0")
    @NotBlank(message = "Please enter experience. Ex: 10")
    private String experience;
    @Size(max = 45)
    @NotBlank(message = "Please enter position")
    @Pattern(regexp = "^(Tiền đạo|Tiền vệ|Hậu vệ|Trung vệ|Thủ môn)$", message = "Name only format. Ex:Tiền đạo|Tiền vệ|Hậu vệ|Trung vệ or Thủ môn)")
    private String position;
    private String img;
    private boolean status;
    private Teams teams;

    public FootballPlayerDTO() {
    }

    public FootballPlayerDTO(Integer id, String name, String dateOfBirth, String experience, String position, String img, boolean status, Teams teams) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
        this.position = position;
        this.img = img;
        this.status = status;
        this.teams = teams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }
}
