package com.irtiza.aspier.dto;

import lombok.Getter;

import java.util.Hashtable;
import java.util.Map;


@Getter
public class ValidationErrorResponse extends ErrorResponse {
    Map<String, String> fields;

    public ValidationErrorResponse() {
        this.fields = new Hashtable<>();
    }
}
