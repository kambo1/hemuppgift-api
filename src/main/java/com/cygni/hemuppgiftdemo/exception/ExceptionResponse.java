package com.cygni.hemuppgiftdemo.exception;


import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {

    private HttpStatus http_status;
    private int status_code;
    private Date date;
    private String message;
    private String details;

    public ExceptionResponse(HttpStatus http_status, int status_code, Date date, String message, String details) {
        this.http_status = http_status;
        this.status_code = status_code;
        this.date = date;
        this.message = message;
        this.details = details;

    }

    public HttpStatus getHttp_status() {
        return http_status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
