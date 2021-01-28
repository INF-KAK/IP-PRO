package application;

import application.Administrator.Administrator;
import application.Owner.Owner;
import application.User.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        FarmSystem farmSystem = new FarmSystem();
        farmSystem.createPasswordToFile();
        farmSystem.initMenu();

    }
}
