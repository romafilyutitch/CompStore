package com.bsac.CompStore.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime dateTime;
    private String message;

    public ErrorDetails() {
    }

    public ErrorDetails(LocalDateTime dateTime, String message) {
        this.dateTime = dateTime;
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
