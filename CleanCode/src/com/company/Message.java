package com.company;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Andron on 09.04.2016.
 */
public class Message {



    private String id;
    private String message;
    private String author;
    private long timestamp;



    public Message(String message, String author) {
        this.id = idGenerator();
        this.message = message;
        this.author = author;
        this.timestamp = new Date().getTime();
    }



    private String idGenerator() {
        UUID id = UUID.randomUUID();
        return id.toString();
    }



    public String getId() {
        return id;
    }



    public String getMessage() {
        return message;
    }



    public String getAuthor() {
        return author;
    }



    public long getTimestamp() {
        return timestamp;
    }



}
