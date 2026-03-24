package com.example.myweb0;

public class Student {
    private final int sid;
    private final String sname;
    private final String gender;
    private final int classId;
    private final String classCaption;

    public Student(int sid, String sname, String gender, int classId, String classCaption) {
        this.sid = sid;
        this.sname = sname;
        this.gender = gender;
        this.classId = classId;
        this.classCaption = classCaption;
    }

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getGender() {
        return gender;
    }

    public int getClassId() {
        return classId;
    }

    public String getClassCaption() {
        return classCaption;
    }
}
