package com.Management;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student extends User{
    Scanner in = new Scanner(System.in);
    private String ID;
    private static Map<String,Course> rgsCourses = new HashMap<>();
    private static ArrayList<Solution> sols = new ArrayList();
    static Course crs = new Course();
    static Doctor doc = new Doctor();
    SystemMa sys = new SystemMa();

    public Student (String usrName,String fullName,String email,String password,String ID) {
        super(usrName,fullName,email,password);
        this.ID=ID;
    }
    public Student(){}
    public  void studentMenu(String usrName) throws Exception {
        String choice;
        System.out.println("Welcome "+usrName+" !!"+"\t\t\t\t\tYou are signed in as "+usrName);
        System.out.println("Your Menu :");
        System.out.println("\t\t\t (1) Register In a Course.");
        System.out.println("\t\t\t (2) List Your Courses.");
        System.out.println("\t\t\t (3) View a Course");
        System.out.println("\t\t\t (4) Grades Report.");
        System.out.println("\t\t\t (5) Log Out");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice = in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")){
                break;
            }
        }
        switch(choice){
            case "1":
                registerInCourse(usrName);
            case "2":
                listCourses(usrName);
            case "3":
                viewCourse(usrName);
            case "4":
                gradesReport(usrName);
            case "5":
                System.out.println("You Are Logging Out!!");
                sys.mainMenu();
        }
    }
    public boolean isStudent(User usr){
        for(Student std :Main.students.values()){
            if(usr.getUsrName().equals(std.getUsrName())){
                return true;
            }
        }
        return false;
    }
    //Done--------------------------------------=
    private void registerInCourse(String usrName) throws Exception {
        String crsCode,choice;
        System.out.println("Available Courses : ");
        listWholeCourses();
        while(true) {
            System.out.print("\t\tPlease Enter the Code of the Course u want to register IN : ");
            crsCode = in.next();
            if(isCourse(crsCode)){
                break;
            }else{
                System.out.println("\t\tWrong Course Code!\nTry Again.");
            }
        }
        Main.students.get(usrName).rgsCourses.put(crsCode,Main.courses.get(crsCode));
        System.out.println("\t\tYOU HAVE REGISTERED IN THE COURSE SUCCESSFULLY");
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                studentMenu(usrName);
        }

    }
    private void listWholeCourses(){
        for(Course crs : Main.courses.values()){
            System.out.println("Course Name : "+crs.getCrsName()+" - Course Code : "+crs.getCrsCode()+" - Number Of Assignements : "+crs.getNumOfAssins());
        }
    }
    private boolean isCourse(String crsCode){
        for(Course crs : Main.courses.values()){
            if(crs.getCrsCode().equals(crsCode)){
                return true;
            }
        }
        return false;
    }
    //Done------------------------------------=
    private void listCourses(String usrName) throws Exception {
        String choice;
                for(Course crs : Main.students.get(usrName).rgsCourses.values()){
                    System.out.println("Course Name : "+crs.getCrsName()+" Course Code : "+crs.getCrsCode()+" Number Of Assignements : "+crs.getNumOfAssins());
        }
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                studentMenu(usrName);
        }

    }
    //Done-----------------------------------=
    private void viewCourse(String usrName) throws Exception {
        String crsCode,choice;
        while(true) {
            System.out.print("\t\tCourse Code : ");
            crsCode = in.next();
            if(isCourse(crsCode)){
                break;
            }else{
                System.out.println("\t\tIvalid Course Code!\n\tTry Again.");
            }
        }
        System.out.println("Your Menu : ");
        System.out.println("\t\t\t (1) Course Summary.");
        System.out.println("\t\t\t (2) List Assignements.");
        System.out.println("\t\t\t (3) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice = in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")){
                break;
            }
        }
        switch(choice){
            case "1":
                courseSummary(usrName,crsCode);
            case "2":
                listAssignements(usrName,crsCode);
            case "3":
                Thread.sleep(500);
                studentMenu(usrName);
        }

    }
    private boolean isCourse(String usrName,String crsCode) {
                for (Course crs : Main.students.get(usrName).rgsCourses.values()) {
                    if (crs.getCrsCode().equals(crsCode)) {
                        return true;
                    }
        }
        return false;
    }
    //Done-------------------------------=
    private void courseSummary(String usrName,String crsCode) throws Exception {
        String choice;
        System.out.println("Name : "+Main.students.get(usrName).rgsCourses.get(crsCode).getCrsName()+" Code : "+crsCode+" Number Of Assignements : "+Main.students.get(usrName).rgsCourses.get(crsCode).getNumOfAssins()+" Final Grade : "+Main.students.get(usrName).rgsCourses.get(crsCode).getFinalGrade());
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                viewCourse(usrName);
        }
    }
    //Done-------------------------------------=
    private void listAssignements(String usrName,String crsCode) throws Exception {
        String choice,assinCode;
        System.out.println("Assignements : ");
        assignements(usrName,crsCode);
        while(true) {
            System.out.print("Assignement Code : ");
            assinCode = in.next();
            if(!isAssignement(usrName,crsCode,assinCode)){
                break;
            }else{
                System.out.println("\t\tInvalid Assignement Code!\n\t");
            }
        }
        System.out.println("Menu : ");
        System.out.println("\t\t\t (1) Show Content.");
        System.out.println("\t\t\t (2) Sumbit a Solution.");
        System.out.println("\t\t\t (3) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice = in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")){
                break;
            }
        }
        switch(choice) {
            case "1":
                showContent(usrName,crsCode,assinCode);
            case "2":
                sumbitSolution(usrName,crsCode,assinCode);
            case "3":
                Thread.sleep(500);
                viewCourse(usrName);
        }
    }
    private void assignements(String usrName,String crsCode){
        Main.students.get(usrName).rgsCourses.get(crsCode).listStdAssignements();
    }
    private boolean isAssignement(String usrName,String crsCode,String assinCode){
                for(Course crs : Main.students.get(usrName).rgsCourses.values()){
                    if(crs.getCrsCode().equals(crsCode)){
                        if(!crs.isAssignement(assinCode)){
                            return true;
                        }

                    }
                }
        return false;
    }
    //Done----------------------------------------=
    public void showContent(String usrName,String crsCode,String assinCode) throws Exception {
        String choice;
        Main.students.get(usrName).rgsCourses.get(crsCode).showAssignementContent(crsCode,assinCode);
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                listAssignements(usrName,crsCode);
        }
    }
    //Done---------------------------=
    private void sumbitSolution(String usrName,String crsCode,String assinCode) throws Exception {
        String choice,pathToSolution;
        while(true) {
            System.out.print("\t\t\tPath to ur Solution : ");
            pathToSolution = in.next();
            if(new File(pathToSolution).exists()){
                break;
            }else{
                System.out.println("\t\t File doesn't Exist!\n\tTry Again.");
            }
        }
        sumbitSolutionAll(usrName,crsCode,assinCode,pathToSolution);
        System.out.println("\t\t\t Solution Sumbitted Successfully!!");
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                listAssignements(usrName,crsCode);
        }
    }
    private void sumbitSolutionAll(String usrName,String crsCode,String assinCode,String pathToSolution){
        Main.students.get(usrName).rgsCourses.get(crsCode).sumbitSolToAssignement(crsCode,assinCode,new Solution(crsCode,assinCode,Main.students.get(usrName).getFullName(),pathToSolution));
        Main.students.get(usrName).sols.add(new Solution(crsCode,assinCode,Main.students.get(usrName).getFullName(),pathToSolution));
    }
    //Done-----------------------------------------------=
    public void gradesReport(String usrName) throws Exception {
        String choice;
                for(Course crs : Main.students.get(usrName).rgsCourses.values()){
                    System.out.println("Course Name : "+crs.getCrsName()+" Number Of Assignements : "+crs.getNumOfAssins()+" Your Grade : "+crs.getStdGrade()+"/"+crs.getFinalGrade());
                }
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                studentMenu(usrName);
        }
    }

}

