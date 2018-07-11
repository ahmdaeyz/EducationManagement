package com.Management;

import java.util.HashMap;
import java.util.Map;
public class Main {
    static Map<String,User> users = new HashMap<>();
    static Map<String,Doctor> doctors = new HashMap<>();
    static Map<String,Student> students = new HashMap<>();
    static Map<String,Course> courses = new HashMap<>();
    public static void main(String[] args) throws Exception {
    SystemMa sys = new SystemMa();
    sys.mainMenu();

    }
}
