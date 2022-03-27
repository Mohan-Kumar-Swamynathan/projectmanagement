package com.project.management.projectmanagement.model;

import lombok.Data;

@Data
public class CommonResponseWrapper<T> {
    private int code;
    private String message;
    private T data;

    public CommonResponseWrapper(int value, String reasonPhrase) {
        this.code = value;
        this.message = reasonPhrase;
    }
}
