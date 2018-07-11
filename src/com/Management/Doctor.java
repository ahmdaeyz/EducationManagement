package com.Management;

import java.io.File;
import java.util.*;

public class Doctor extends User {
    SystemMa sys = new SystemMa();
    Scanner in = new Scanner(System.in);
    Map<String,Course> courses = new HashMap<>();
    public Doctor(String usrName,String fullName,String email,String password) {
        super(usrName,fullName,email,password);
    }
    public Doctor(){}
    //Done---------------------------------=
    public  void doctorMenu(String usrName)throws Exception{
        String choice;
        System.out.println("Welcome "+usrName+" !!"+"\t\t\t\t\tYou are signed in as "+usrName);
        System.out.println("Your Menu :");
        System.out.println("\t\t\t (1) Create a Course.");
        System.out.println("\t\t\t (2) View a Course.");
        System.out.println("\t\t\t (3) List Your Courses");
        System.out.println("\t\t\t (4) Log out");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice = in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")){
                break;
            }
        }
        switch(choice){
            case "1":
                createCourse(usrName);
            case "2":
                viewCourse(usrName);
            case "3":
                listCourses(usrName);
            case "4":
                System.out.println("You Are Logging Out!!");
                Thread.sleep(500);
                sys.mainMenu();
        }
    }
    public void listCourses(String usrName) throws Exception {
        String choice;
        System.out.println("Your Courses : ");
        for(Course crs : Main.doctors.get(usrName).courses.values()){
            System.out.println("Name : "+crs.getCrsName()+" - Course Code : "+crs.getCrsCode()+" - Number Of Assignements : "+crs.getNumOfAssins());
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
                doctorMenu(usrName);
        }

    }
    public boolean isDoctor(User usr){
        for(Doctor  doc : Main.doctors.values() ){
            if(doc.getUsrName().equals(usr.getUsrName())){
                return true;
            }
        }
        return false;
    }
    private String getFullName(String usrName){
        return Main.users.get(usrName).getFullName();
    }
    //Done-------------------------------=
    public void createCourse(String usrName)throws Exception{
        String crsName,crsCode,taughBy,choice;
        int numOfHours,finalGrade;
        System.out.print("\t\tCourse Name : ");
        crsName=in.next();
        System.out.print("\t\tCourse Code : ");
        crsCode=in.next();
        taughBy=getFullName(usrName);
        System.out.print("\t\tNumber Of Hours : ");
        numOfHours=in.nextInt();
        System.out.print("\t\tFinal Grade : ");
        finalGrade=in.nextInt();
        addACourse(usrName,crsCode,new Course(crsName,crsCode,taughBy,numOfHours,finalGrade));
        System.out.println("\t\tCourse Created Successfully!!");
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
                doctorMenu(usrName);
        }
    }
    private void addACourse(String usrName,String crsCode,Course crs){
                Main.doctors.get(usrName).courses.put(crsCode,crs);
                Main.courses.put(crsCode,crs);
    }
    //Done---------------------------=
    public void viewCourse(String usrName) throws Exception {
        String crsCode,choice;
        while(true) {
            System.out.print("\n\tCourse Code : ");
            crsCode = in.next();
            if(isCourse(crsCode)){
                break;
            }else{
                System.out.println("\t\tInvalid Course Code\n\tTry Again");
            }
        }
        System.out.println("Your Menu : ");
        System.out.println("\t\t\t(1) Create An Assignement.");
        System.out.println("\t\t\t(2) View An Assignement.");
        System.out.println("\t\t\t(3) Back.");
        while(true) {
            System.out.print("\n\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")){
                break;
            }else{
                System.out.println("\t\t\t\tInvalid Choice!");
            }
        }
        switch(choice){
            case "1":
                createAssignement(usrName,crsCode);
            case "2":
                viewAssignement(usrName,crsCode);
            case "3":
                Thread.sleep(500);
                doctorMenu(usrName);
        }
    }
    //Not Done-----------------------------------------------=
    public void createAssignement(String usrName,String crsCode) throws Exception {
        String assinName,assinCode,choice,assinContent;
        int finalGrades;
        System.out.print("\t\tAssignement Name : ");
        assinName=in.next();
        System.out.print("\t\tAssignement Code : ");
        assinCode=in.next();
        while(true) {
            System.out.print("\t\tAssignement Final Grade : ");
            finalGrades = in.nextInt();
                    if(finalGrades>getCourseFinalGrade(crsCode)){
                        System.out.println("\t\tAssignement Grade can't be greater than the Course Final Grade\n\tPlease review ur input.");
                    }else {
                        break ;
                    }
        }
        while(true) {
            System.out.print("\t\tAssignement Content\n\t(Please Do Provide the path to the .txt file.) : ");
            assinContent = in.next();
            if(new File(assinContent).isFile()&&new File(assinContent).exists()){
                break;
            }else{
                System.out.println("\t\tNo Such File!\n\tPlease Review the path u Entered.");
            }
        }
        Main.courses.get(crsCode).assignements.put(assinCode,new Assignement(crsCode,assinName,assinCode,finalGrades,assinContent));
        System.out.println("\t\tAssignement Created Successfully!!");
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\t\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                System.out.println("\t\t\tYou are returning to the previuos Menu (View Course)...");
                Thread.sleep(500);
                viewCourse(usrName);
        }
    }
    public boolean isCourse(String crsCode){
        for(Course crs : Main.courses.values())
            if(crs.getCrsCode().equals(crsCode)){
                return true;
            }
        return false;
    }
    public void listAssignements(String crsCode){
        for(Assignement assin : Main.courses.get(crsCode).assignements.values()){
            System.out.println(assin.showInfo());
        }
    }
    public boolean isAssignement(String crsCode,String assinCode){
            if(!Main.courses.get(crsCode).isAssignement(assinCode)){
                return true;
            }
        return false;
    }
    //Done-----------------------------------=
    public void showAssignementInfo(String usrName,String crsCode,String assinCode) throws Exception {
        String choice;
        Main.courses.get(crsCode).showAssignementInfo(crsCode,assinCode);
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
                viewAssignement(usrName,crsCode);
        }
    }
    //Done-----------------------------=
    public void gradesReport(String usrName,String  crsCode,String assinCode) throws Exception {
        String choice;
        Main.doctors.get(usrName).courses.get(crsCode).gradesReport(crsCode,assinCode);
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
                viewAssignement(usrName,crsCode);
        }

    }
    //Done----------------------------=
    public void viewAssignement(String usrName,String crsCode) throws Exception {
        System.out.println("\t\tAvailable Assignements for this course : ");
        listAssignements(crsCode);
        String assinCode,choice;
        while(true) {
            System.out.print("\t\t\tAssignement Code : ");
            assinCode = in.next();
            if(!isAssignement(crsCode,assinCode)){
                break;
            }else{
                System.out.println("\t\tInvalid Assigement Code!\n\tTry Again.");
            }
        }
        System.out.println("Your Menu : ");
        System.out.println("\t\t\t(1) Show Info.");
        System.out.println("\t\t\t(2) Show Grades Report.");
        System.out.println("\t\t\t(3) List Solutions.");
        System.out.println("\t\t\t(4) View Solution.");
        System.out.println("\t\t\t(5) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")){
                break;
            }else{
                System.out.println("\t\t\t\tInvalid Choice!");
            }
        }
        switch(choice){
            case "1":
                showAssignementInfo(usrName,crsCode,assinCode);
            case "2":
                gradesReport(usrName,crsCode,assinCode);
            case "3":
                listSolutions(usrName,crsCode,assinCode);
            case "4":
                System.out.println("\t\t\tPlease List Solutions first if u haven't already.");
                viewSolution(usrName,crsCode,assinCode);
            case "5":
                Thread.sleep(500);
                viewCourse(usrName);
        }
    //Done---------------------------------=
    }
    public void listSolutions(String usrName,String crsCode,String assinCode) throws Exception {
        String choice;
        System.out.println("A list of Student's Solutions ans their grades");
        Main.courses.get(crsCode).listSolution(crsCode,assinCode);
        Thread.sleep(500);
        System.out.println("\t\t\t(1) Back.");
        while(true) {
            System.out.print("\n\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")){
                break;
            }
        }
        switch(choice){
            case "1":
                Thread.sleep(500);
                viewAssignement(usrName,crsCode);
        }
    }
    //Done--------------------------=
    public void viewSolution(String usrName,String crsCode,String assinCode) throws Exception {
        int positionInList;
        String choice;
        while(true) {
            System.out.print("\t\tSolution Position In List : ");
            positionInList = in.nextInt();
            if(positionInList-1>numOfSolutions(crsCode,assinCode)){
                System.out.println("\t\tNot in the List!\n\tTry Again.");
            }else{
                break;
            }
        }
        System.out.println("Your Menu : ");
        System.out.println("\t\t\t(1) Show Info.");
        System.out.println("\t\t\t(2) Set Grade.");
        System.out.println("\t\t\t(3) Set Comment.");
        System.out.println("\t\t\t(4) Back.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice=in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")){
                break;
            }else{
                System.out.println("\t\t\t\tInvalid Choice!");
            }
        }
        switch(choice){
            case "1":
                showSolInfo(usrName,crsCode,assinCode,positionInList-1);
            case "2":
                setGrade(usrName,crsCode,assinCode,positionInList-1);
            case "3":
                setComment(usrName,crsCode,assinCode,positionInList-1);
            case "4":
                viewAssignement(usrName,crsCode);
        }

    }
    public int numOfSolutions(String crsCode,String assinName){
        return Main.courses.get(crsCode).numOfSolutions(crsCode,assinName);
    }
    //Done---------------------------=
    public void setComment(String usrName,String crsCode,String assinCode,int positionInList) throws Exception {
        String choice,comment;
        System.out.print("\t\tYour Comment : ");
        comment=in.nextLine();
        Main.courses.get(crsCode).setComment(crsCode,positionInList,comment,assinCode);
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
                viewSolution(usrName,crsCode,assinCode);
        }
    }
    //Done----------------------------------=
    public void setGrade(String usrName,String crsCode,String assinCode,int positionInList) throws Exception {
        String choice;
        int grade;
        System.out.print("The Grade : ");
        grade=in.nextInt();
        Main.courses.get(crsCode).setGrade(crsCode,positionInList,grade,assinCode);
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
                viewSolution(usrName,crsCode,assinCode);
        }
    }
    //Done-----------------------------------=
    public void showSolInfo(String usrName,String crsCode,String assinCode,int positionInList) throws Exception {
        String choice;
        Main.courses.get(crsCode).showSolInfo(crsCode,positionInList,assinCode);
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
                viewSolution(usrName,crsCode,assinCode);
        }
    }
    public int getCourseFinalGrade(String crsCode){
        return Main.courses.get(crsCode).getFinalGrade();
    }
}
