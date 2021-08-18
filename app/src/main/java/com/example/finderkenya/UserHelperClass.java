package com.example.finderkenya;

public class UserHelperClass{

    private String userId,fname,uname,mail,mobileNo,pwd;
    private String imageURL;

    public UserHelperClass() {
    }

    public UserHelperClass(String userId,String fname, String uname, String mail, String mobileNo, String pwd, String imageURL) {

        this.userId = userId;
        this.fname = fname;
        this.uname = uname;
        this.mail = mail;
        this.mobileNo = mobileNo;
        this.pwd = pwd;
        this.imageURL = imageURL;
    }



    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        this.userId= userId;
    }

    public String getFname() { return fname; }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}


