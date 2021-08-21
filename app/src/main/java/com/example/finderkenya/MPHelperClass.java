package com.example.finderkenya;

public class MPHelperClass {

    private String victimName,obNumber,victimLastSeen,victimDob,victimDescription,victimHome,victimContact1,victimContact2,caseStatus,postImage,time,date,fname;


    public MPHelperClass(String victimName,String obNumber,String victimLastSeen,String victimDob,String victimDescription,String victimHome,String victimContact1,String victimContact2,String caseStatus,String postImage,String time,String date,String fname){
        this.victimName = victimName;
        this.obNumber = obNumber;
        this.victimLastSeen = victimLastSeen;
        this.victimDob = victimDob;
        this.victimDescription = victimDescription;
        this.victimHome = victimHome;
        this.victimContact1 = victimContact1;
        this.victimContact2 = victimContact2;
        this.caseStatus = caseStatus;
        this.postImage = postImage;
        this.time = time;
        this.date = date;
        this.fname= fname;

    }
    public MPHelperClass(){

    }



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public String getObNumber() {
        return obNumber;
    }

    public void setObNumber(String obNumber) {
        this.obNumber = obNumber;
    }

    public String getVictimLastSeen() {
        return victimLastSeen;
    }

    public void setVictimLastSeen(String victimLastSeen) {
        this.victimLastSeen = victimLastSeen;
    }

    public String getVictimDob() {
        return victimDob;
    }

    public void setVictimDob(String victimDob) {
        this.victimDob = victimDob;
    }

    public String getVictimDescription() {
        return victimDescription;
    }

    public void setVictimDescription(String victimDescription) {
        this.victimDescription = victimDescription;
    }

    public String getVictimHome() {
        return victimHome;
    }

    public void setVictimHome(String victimHome) {
        this.victimHome = victimHome;
    }

    public String getVictimContact1() {
        return victimContact1;
    }

    public void setVictimContact1(String victimContact1) {
        this.victimContact1 = victimContact1;
    }

    public String getVictimContact2() {
        return victimContact2;
    }

    public void setVictimContact2(String victimContact2) {
        this.victimContact2 = victimContact2;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
