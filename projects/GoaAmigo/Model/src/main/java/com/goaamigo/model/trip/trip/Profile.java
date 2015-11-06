package com.goaamigo.model.trip.trip;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Profile implements Serializable {

    private long id;
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "profile_email")
    private String profileEmail;
    @Column(name = "profile_image")
    private int profileImage;

    public Profile() {
    }

    public Profile(String profileName, String profileEmail, int profileImage) {
        this.profileName = profileName;
        this.profileEmail = profileEmail;
        this.profileImage = profileImage;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
