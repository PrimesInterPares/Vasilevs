package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Andron on 09.04.2016.
 */
public class Menu {



    private Messages messages;
    private List<String> menu;



    public Menu() {
        messages = new Messages();
        menu = new ArrayList<>();
    }



    private void setMenuDate(){
        menu.add("1)Read from file.");
        menu.add("2)Write to file.");
        menu.add("3)Add message.");
        menu.add("4)View the history of messages in chronological order.");
        menu.add("5)Delete the message by ID.");
        menu.add("6)Search in the history of posts by author.");
        menu.add("7)Search in the history of posts by keyword.");
        menu.add("8)Search in the history of posts by key ffirst entry.");
        menu.add("0)Exit");
    }



    private void printInterface(){
        System.out.println();
        for (String s : menu) {
            System.out.println(s);
        }
    }



    public void comandSwitch() {
        while (true) {
            printInterface();
            try {
                Scanner sc = new Scanner(System.in);
                switch (Integer.parseInt(sc.next())) {
                    case 1:
                        readMessages();
                        break;
                    case 2:
                        writeMessages();
                        break;
                    case 3:
                        addMessage();
                        break;
                    case 4:
                        printMessageHistory();
                        break;
                    case 5:
                        deleteMessages();
                        break;
                    case 6:
                        printMessageByAuthor();
                        break;
                    case 7:
                        printMessageByKeyWord();
                        break;
                    case 8:
                        printMessageByKeyEntry();
                    case 0:
                        return;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {}
        }
    }



    private void readMessages() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter path to u'r File");
        messages.readFromFile(new File(sc.nextLine()));
        sc.close();
    }



    private void writeMessages() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter path to u'r File");
        messages.writeToFile(new File(sc.nextLine()));
        sc.close();
    }



    private void addMessage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter u'r username");
        String username = sc.next();
        System.out.println("Enter u'r message");
        sc.skip("[\r\n]+");
        messages.addMessage(username, sc.nextLine());
        sc.close();
    }



    public void printMessageHistory() {
        messages.printMessages(messages.getMessageHistory());
    }



    private void deleteMessages() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID");
        messages.deleteMessage(sc.next());
        sc.close();
    }



    public void printMessageByAuthor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter user name");
        messages.printMessages(messages.getMessageByAuthor(sc.next()));
        sc.close();
    }



    public void printMessageByKeyWord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter key word");
        messages.printMessages(messages.getMessageByKeyWord(sc.nextLine()));
        sc.close();
    }



    public void printMessageByKeyEntry() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter key word");
        messages.printMessages(messages.getMessageByKeyEntry(sc.nextLine()));
        sc.close();
    }



    public void run(){
        setMenuDate();
        comandSwitch();
    }
}
