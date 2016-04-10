package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andron on 09.04.2016.
 */
public class Messages {



    private Gson gson;
    private List<Message> messages;



    public Messages(){
        this.gson = new GsonBuilder().create();
        this.messages = new ArrayList<>();
    }




    public void readFromFile(File fin){
        try {
            Scanner sc = new Scanner(fin);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            Pattern p = Pattern.compile("[{][^{]+[}]");
            Matcher m = p.matcher(sb);
            while (m.find()) {
                try {
                    messages.add(gson.fromJson(sb.substring(m.start(), m.end()), Message.class));
                } catch (JsonSyntaxException e) {}
            }
        } catch (FileNotFoundException e) {System.err.println("File \"" + fin + "\" not found");}
    }



    public void writeToFile(File fout) {
        try{
            PrintStream ps = new PrintStream(fout);
            ps.print("[");
            int counter = 0;
            for (Message message : messages) {
                ps.print(gson.toJson(message));
                counter++;
                if (counter < messages.size()) {
                    ps.println(",");
                }
            }
            ps.print("]");
        } catch (FileNotFoundException e) {System.err.println("File \"" + fout + "\" not found");}
    }



    public void addMessage(String author, String message) {
        messages.add(new Message(author, message));
    }



    public List<Message> getMessageHistory() {
        List<Message> copy = new ArrayList<>();
        copy.addAll(messages);
        Collections.sort(copy, new TimeStampComparator());
        return copy;

    }



    public void deleteMessage(String id) {
        for (Message message : messages) {
            if (message.getId().compareTo(id) == 0) {
                messages.remove(message);
                break;
            }
        }
    }




    public List<Message> getMessageByAuthor(String author) {
        List<Message> temp = new ArrayList<>();
        for (Message message : messages) {
            if (message.getAuthor().compareTo(author) == 0) {
                temp.add(message);
            }
        }
        return temp;
    }



    public List<Message> getMessageByKeyEntry(String keyWord){
        List<Message> temp = new ArrayList<>();
        for (Message message :messages) {
            if (message.getMessage().contains(keyWord)) {
                temp.add(message);
            }
        }
        return temp;
    }



    public List<Message> getMessageByKeyWord(String keyWord){
        List<Message> temp = new ArrayList<>();
        for (Message message :messages) {
            if (message.getMessage().compareTo(keyWord) == 0) {
                temp.add(message);
            }
        }
        return temp;
    }



    public void printMessages(List<Message> printItem) {
        for (Message message : printItem) {
            System.out.println(message.getMessage());
        }
    }



}
