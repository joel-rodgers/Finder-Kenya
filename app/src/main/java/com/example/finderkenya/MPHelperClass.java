package com.example.finderkenya;

public class MPHelperClass {

    public boolean visibility;
    private String victimName;
    private String obNumber;
    private String victimLastSeen;
    private String victimDob;
    private String victimDescription;
    private String victimHome;
    private String victimContact1;
    private String victimContact2;
    private String caseStatus;
    static String postImage;
    private String time;
    private String date;
    private String fname;
   // private String imageUrl;



    public MPHelperClass(String victimName,String obNumber,String victimLastSeen,String victimDob,String victimDescription,String victimHome,String victimContact1,String victimContact2,String caseStatus,String postImage,String time,String date,String fname,String imageUrl){
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
        this.visibility= false;
       // this.imageUrl = imageURL;

    }
    public MPHelperClass(){

    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
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

    public static String getPostImage() {
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
