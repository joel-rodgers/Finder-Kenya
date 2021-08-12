package com.example.finderkenya;

public class UserHelperClass{

    String fname,uname,mail,mobileNo,pwd;

    public UserHelperClass() {
    }

    public UserHelperClass(String fname, String uname, String mail, String mobileNo, String pwd) {

        this.fname = fname;
        this.uname = uname;
        this.mail = mail;
        this.mobileNo = mobileNo;
        this.pwd = pwd;
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
}


