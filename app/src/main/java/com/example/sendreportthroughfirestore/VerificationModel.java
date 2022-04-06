package com.example.sendreportthroughfirestore;

public class VerificationModel {

    String Email;
    String Licence;
    String Name;
    String image;
    public  VerificationModel()
    {

    }

    public VerificationModel(String email, String licence, String name, String image) {
        Email = email;
        Licence = licence;
        Name = name;
        this.image = image;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLicence() {
        return Licence;
    }

    public void setLicence(String licence) {
        Licence = licence;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
