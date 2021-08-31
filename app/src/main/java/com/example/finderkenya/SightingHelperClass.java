package com.example.finderkenya;

public class SightingHelperClass {
    private String victimCondition,locationSighting,sightingId,dateSeen,fname,caseId;
    private String time;
    private String date;
    private String mobileNo;
    private String postImage;
    private String victimName;



    public SightingHelperClass(String victimName,String postImage,String mobileNo,String victimCondition,String locationSighting,String sightingId,String dateSeen,String time,String date,String fname,String caseId){

        this.victimCondition = victimCondition;
        this.locationSighting = locationSighting;
        this.sightingId = sightingId;
        this.dateSeen = dateSeen;
        this.time = time;
        this.date = date;
        this.fname= fname;
        this.caseId = caseId;
        this.mobileNo = mobileNo;
        this.postImage = postImage;
        this.victimName = victimName;

    }

    public SightingHelperClass(){

    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getVictimCondition() {
        return victimCondition;
    }

    public void setVictimCondition(String victimCondition) {
        this.victimCondition = victimCondition;
    }

    public String getLocationSighting() {
        return locationSighting;
    }

    public void setLocationSighting(String locationSighting) {
        this.locationSighting = locationSighting;
    }

    public String getSightingId() {
        return sightingId;
    }

    public void setSightingId(String sightingId) {
        this.sightingId = sightingId;
    }

    public String getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(String dateSeen) {
        this.dateSeen = dateSeen;
    }
}
