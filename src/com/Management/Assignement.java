package com.Management;

import java.io.*;
import java.util.ArrayList;

public class Assignement {
    private String crsCode;
    private String assinName;
    private String assinContent;
    private File assin;
    private String assinCode;
    private int finalGrades;
    ArrayList<Solution> sols = new ArrayList();
    private int stdGrade;
    public Assignement(String crsCode, String assinName, String assinCode, int finalGrades,String assinContent) {
        this.crsCode = crsCode;
        this.assinName = assinName;
        this.assinCode = assinCode;
        this.assinContent=assinContent;
        this.finalGrades = finalGrades;
        this.assin = new File(assinContent);
    }
    public void setStdGrade(int stdGrade) {
        this.stdGrade = stdGrade;
    }
    public String getAssinCode() {
        return assinCode;
    }
    public String showInfo(){
        return "Assignement Name : "+assinName+" - Assignement Code : "+assinCode+" - Assignement Final Grade : "+finalGrades;
    }
    public void gradesReport(){
        for(Solution sol : sols){
            System.out.println(sol.gradeReport());
        }
    }
    public void listSolutions() throws IOException {
        int i=1;
        for(Solution sol : sols){
            System.out.println("("+ i++ +") "+"Student Name : "+ sol.getStudentName()+" - Grade : "+sol.getGrade());
            System.out.println("Student's Solution : ");
            System.out.println(sol.getContent());
        }
    }
    public void setComment(int postionInList,String comment){
        for(int i =0 ;i<sols.size();i++){
            if(i==postionInList){
                sols.get(i).setComment(comment);
            }
        }
    }
    public void setGrade(int postionInList,int grade){
        for(int i =0 ;i<sols.size();i++){
            if(i==postionInList){
                sols.get(i).setGrade(grade);
            }
        }
    }
    public void showSolInfo(int postionInList) throws IOException {
        for(int i =0 ;i<sols.size();i++){
            if(i==postionInList){
                System.out.println("Student Name : "+sols.get(i).getStudentName()+" - State : "+sols.get(i).getState());
                System.out.println("Solution : ");
                System.out.println(sols.get(i).getContent());
            }
        }
    }
    public int numOfSolutions(){
        return sols.size();
    }

    public int getStdGrade() {
        return stdGrade;
    }

    public String showContent() throws IOException {
        FileReader fr = new FileReader(assinContent);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        while((br.readLine())!=null){
            sb.append(br.readLine());
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }
    public void sumbitSolutionToAssignement(Solution sol){
        sols.add(sol);
    }
}
