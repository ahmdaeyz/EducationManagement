package com.Management;

import sun.misc.ASCIICaseInsensitiveComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String crsName;
    private String crsCode;
    private String taughtBy;
    private int numOfHours;
    private int finalGrade;
    private int stdGrade;
    Map<String,Assignement> assignements= new HashMap<>();
    private int numOfAssins;
    public Course(String crsName,String crsCode,String taughtBy,int numOfHours,int finalGrade){
        this.crsName=crsName;
        this.crsCode=crsCode;
        this.taughtBy=taughtBy;
        this.numOfHours=numOfHours;
        this.finalGrade=finalGrade;
    }
    public Course(){}

    public String getCrsName() {
        return crsName;
    }
    public String getCrsCode() {
        return crsCode;
    }
    public int getNumOfAssins() {
        numOfAssins=assignements.size();
        return numOfAssins;
    }
    public int getFinalGrade() {
        return finalGrade;
    }
    public void listAssignements(){
        for(Assignement assin : assignements.values()){
            System.out.println("-"+assin.getAssinCode());
        }
    }
    public boolean isAssignement(String assinCode){
        for(Assignement assin : assignements.values()){
            if(assin.getAssinCode().equals(assinCode)){
                return true;
            }
        }
        return false;
    }
    public void showAssignementInfo(String crsCode,String assinCode){
                System.out.println(Main.courses.get(crsCode).assignements.get(assinCode).showInfo());
    }
    public void gradesReport(String crsCode,String assinCode){
        Main.courses.get(crsCode).assignements.get(assinCode).gradesReport();
    }
    public void listSolution(String crsCode,String assinCode) throws IOException {
        Main.courses.get(crsCode).assignements.get(assinCode).listSolutions();
    }
    public int numOfSolutions(String crsCode,String assinCode){
                return Main.courses.get(crsCode).assignements.get(assinCode).numOfSolutions();
    }
    public void setComment(String crsCode,int positionInList,String comment,String assinCode){
        Main.courses.get(crsCode).assignements.get(assinCode).setComment(positionInList,comment);
    }
    public void setGrade(String crsCode,int positionInList,int grade,String assinCode){
        Main.courses.get(crsCode).assignements.get(assinCode).setGrade(positionInList,grade);
    }
    public void showSolInfo(String crsCode,int positionInList,String assinCode) throws IOException {
        Main.courses.get(crsCode).assignements.get(assinCode).showSolInfo(positionInList);
    }
    public void listStdAssignements(){
        for(Assignement assin : assignements.values()){
            System.out.println(assin.showInfo());
        }
    }
    public void showAssignementContent(String crsCode,String assinCode) throws IOException {
                System.out.println(Main.courses.get(crsCode).assignements.get(assinCode).showContent());
    }
    public void sumbitSolToAssignement(String crsCode,String assinCode,Solution sol){
        Main.courses.get(crsCode).assignements.get(assinCode).sumbitSolutionToAssignement(sol);
    }
    public int studentGrade(){
        for(Assignement assin : assignements.values()){
            stdGrade+=assin.getStdGrade();
        }
        return stdGrade;
    }
    public int getStdGrade(){
        return studentGrade();
    }
}

