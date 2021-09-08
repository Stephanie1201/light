package edu.episen.si.ing1.pds.backend.server.controller;

import edu.episen.si.ing1.pds.backend.server.model.Crud;
import edu.episen.si.ing1.pds.backend.server.view.View;

import java.util.Scanner;

/*
A controller controls the flow of the data.
It controls the data flow into crud object and updates the view whenever data changes
 */
public class Controller {
    private Crud crud;
    private View view;

    public Controller (Crud crud, View view) {
        this.crud = crud;
        this.view = view;
    }
    public  void control(){
        view.viewShow();
        Scanner scanner = new Scanner((System.in));
        String response  = scanner.nextLine();
        String firstName, lastName, newFirstName, newLastName;
        while (!response.equals("q")){
            switch (response){
                case "s":
                    System.out.println(crud.select()); //n if we click to s; the application go to chech the select methode present in crud. this methode select firstName , ...
                    view.viewShow(); // and the methode showView of view classs
                    break;
                case "i":
                    System.out.println("Please, enter a firstname and lastname to insert a line \n");
                    System.out.println("Firstname : ");
                    firstName = scanner.nextLine();
                    System.out.println("\nLastname : ");
                    lastName = scanner.nextLine();
                    crud.insert(firstName, lastName);
                    view.viewShow();
                    break;
                case "d":
                    System.out.println("Please, enter a firstname and lastname to delete \n");
                    System.out.println("Firstname : ");
                    firstName = scanner.nextLine();
                    System.out.println("\nLastname : ");
                    lastName = scanner.nextLine();
                    crud.delete(firstName, lastName);
                    view.viewShow();
                    break;
                case "u":
                    System.out.println("Please, enter the firstname and lastname you want to change, then enter the new values: \n");
                    System.out.println("First Name : ");
                    firstName = scanner.nextLine();
                    System.out.println("\nLast Name : ");
                    lastName = scanner.nextLine();
                    System.out.println("\nNew First Name : ");
                    newFirstName = scanner.nextLine();
                    System.out.println("\nNew Last Name : ");
                    newLastName = scanner.nextLine();
                    crud.update(firstName, lastName, newFirstName, newLastName);
                    view.viewShow();
                    break;
                case "t":

                default:
                    view.viewShow();
                    break;
            }
            response = scanner.nextLine();
        }

    }
}
