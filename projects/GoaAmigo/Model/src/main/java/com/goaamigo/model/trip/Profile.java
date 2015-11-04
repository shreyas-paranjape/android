package com.goaamigo.model.trip;

public class Profile {
    private String profileName;
    private String profileEmail;
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
}
