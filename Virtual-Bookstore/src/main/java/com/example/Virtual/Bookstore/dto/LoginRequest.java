package com.example.Virtual.Bookstore.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}