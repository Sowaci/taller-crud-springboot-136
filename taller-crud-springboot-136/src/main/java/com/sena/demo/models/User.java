package com.sena.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {

    private String id;
    private String name;
    private String email;
    private Integer age;
    private String phone;

}