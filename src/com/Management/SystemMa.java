package com.Management;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SystemMa {
    static Doctor doc = new Doctor();
    static Student std = new Student();
    public String mainMenu() throws Exception{
        Scanner in = new Scanner(System.in);
        String choice;
        String usrName="";
        System.out.println("Welcome to the Education Management System !!");
        System.out.println("Menu : ");
        System.out.println("\t\t\t(1) Sign Up.");
        System.out.println("\t\t\t(2) Sign In.");
        System.out.println("\t\t\t(3) Exit.");
        while(true) {
            System.out.print("\t\tYour Choice : ");
            choice = in.next();
            if(choice.equals("1")||choice.equals("2")||choice.equals("3")){
                break;
            }else{
                System.out.println("Invalid Choice!!\nTry Again.");
            }
        }
        switch(choice){
            case "1":
                String user;
                while(true) {
                    System.out.print("Are u a Doctor ot a Student?\nDoctor or Student (Not case Sensitive) : ");
                    user=in.next();
                    if(user.equalsIgnoreCase("Student")||user.equalsIgnoreCase("Doctor")){
                        break;
                    }else{
                        System.out.println("Not Defined in the System!!\nTry Again.");
                    }
                }
                if(user.equalsIgnoreCase("Student")){
                    std.studentMenu(signUp(user));
                }else if(user.equalsIgnoreCase("Doctor")){
                    doc.doctorMenu(signUp(user));
                }
            case "2":
                usrName=signIn();
                if(usrKind(usrName).equals("Doctor")){
                    doc.doctorMenu(usrName);
                }else if(usrKind(usrName).equals("Student")){
                    std.studentMenu(usrName);
                }
            case "3":
                System.exit(0);
        }
        return usrName;

    }
    public String signUp(String user) throws Exception{
        Scanner in = new Scanner(System.in);
        String usrName="";
        if (user.equalsIgnoreCase("Student")) {
            String fullName, email, password, id;
            System.out.print("\n\t\t\tWelcome Student !!\n");
            System.out.print("\tFull Name : ");
            fullName = in.nextLine();
            while (true) {
                System.out.print("\tUser name : ");
                usrName = in.next();
                if (!availbleUsrName(usrName)) {
                    System.out.println("\tUnavialable user name!\n\t\tChoose another one.");
                } else {
                    break;
                }
            }
            System.out.print("\tPassword : ");
            password=in.next();
            while (true) {
                System.out.print("\tEmail : ");
                email = in.next();
                if (!validateEmail(email)) {
                    System.out.println("\tWrong E-mail format!\n\t\tTry Again.");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.print("\tID : ");
                id = in.next();
                if (validID(id)) {
                    System.out.println("\tInvalid Id!\n\t\tTry Again.");
                }else{
                    break;
                }
            }
            Student student = new Student(usrName,fullName,email,Encryptor.encrypt(password),id);
            Main.students.put(usrName,student);
            Main.users.put(usrName,student);
            System.out.println("REGISTERED");

        }else if(user.equalsIgnoreCase("Doctor")){
            String fullName, email, password;
            System.out.println("\t\t\tWelcome Doctor !!");
            System.out.print("\tFull Name : ");
            fullName = in.nextLine();
            while (true) {
                System.out.print("\tUser name : ");
                usrName = in.next();
                if (!availbleUsrName(usrName)) {
                    System.out.println("\t\t\tUnavialable user name!\n\t\t\t\tChoose another one.");
                } else {
                    break;
                }
            }
            System.out.print("\tPassword : ");
            password=in.next();
            while (true) {
                System.out.print("\tEmail : ");
                email = in.next();
                if (!validateEmail(email)) {
                    System.out.println("\t\t\tWrong E-mail format!\n\t\t\t\tTry Again.");
                } else {
                    break;
                }
            }
            Doctor doctor = new Doctor(usrName,fullName,email,Encryptor.encrypt(password));
            Main.doctors.put(usrName,doctor);
            Main.users.put(usrName,doctor);
            System.out.println("\t\t\tREGISTERED");

        }
        return usrName;
    }
    public String signIn() throws Exception{
        String usrName,password;
        Scanner in = new Scanner(System.in);
        System.out.println("\t\t\tYou are trying to sign in....");
        while(true) {
            System.out.print("\tUser name : ");
            usrName = in.next();
            if(usrExists(usrName)){
                break;
            }else{
                System.out.println("\t\t\tUser name Doesn't Exist!\n\t\t\tTry Again.");
            }
        }
        while(true) {
            boolean done = false;
            System.out.print("\tPassword : ");
            password = in.next();
                if(Main.users.get(usrName).getUsrName().equals(usrName)){
                    if(Encryptor.decrypt(Main.users.get(usrName).getPassword()).equals(password)){
                        done=true;
                        break;
                    }
            }
            if(done){
                System.out.println("\t\t\tYou are now signed in ("+usrName+")");
                break;
            }else{
                System.out.println("\t\t\tWrong Password!! \n\t\t\t\tTry Again.");
            }
        }
        return usrName;
    }

    public boolean validateEmail(String email){
        if(email.matches("\\w+@+[a-z]+.com")){
            return true;
        }
        return false;
    }
    public boolean availbleUsrName(String usrName){
        for(User usr : Main.users.values()){
            if(usr.getUsrName().equals(usrName)){
                return false;
            }
        }
        return true;
    }
    public boolean validID(String Id){
        if(!Id.matches("[0-9]")){
            return false;
        }
        return true;
    }
    public boolean usrExists(String usrName){
        for(User usr : Main.users.values()){
            if(usrName.equals(usr.getUsrName())){
                return true;
            }
        }
        return false;
    }
    public String usrKind(String usrName){
            if(doc.isDoctor(Main.users.get(usrName))){
                return "Doctor";
            }else if(std.isStudent(Main.users.get(usrName))){
                return "Student";
            }
        return null;
    }

}
