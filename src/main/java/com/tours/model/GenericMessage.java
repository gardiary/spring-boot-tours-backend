package com.tours.model;

/**
 * Created by gardiary on 10/12/18.
 */
public class GenericMessage {
    private String message;

    public GenericMessage() {
    }

    public GenericMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
