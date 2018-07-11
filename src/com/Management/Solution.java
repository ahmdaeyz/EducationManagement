package com.Management;

import java.io.*;

public class Solution {
    private String crsCode;
    private String assignCode;
    private String studentName;
    private String content;
    private File sol;
    private int grade=0;
    private String state="Not Delivered Yet";
    private String comment;

    public Solution(String crsCode, String assignCode, String studentName, String content) {
        this.crsCode = crsCode;
        this.assignCode = assignCode;
        this.studentName = studentName;
        this.sol = new File(content);
        this.state="Delivered";
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    public String gradeReport(){
        return "Student Name : "+studentName+" - Solution State"+state+" - Grade : "+grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getContent() throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(this.sol);
        BufferedReader br = new BufferedReader(fr);
        while((br.readLine())!=null){
            sb.append(br.readLine());
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }
}
