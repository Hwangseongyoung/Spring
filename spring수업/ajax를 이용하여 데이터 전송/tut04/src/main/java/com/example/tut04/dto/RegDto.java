package com.example.tut04.dto;

import lombok.Data;

@Data
public class RegDto {
    private String email;
    private String passwd;

    @Override
    public String toString() {
        return "RegDto{" +
                "email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
