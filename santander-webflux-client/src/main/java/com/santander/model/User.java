package com.santander.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private String id;
    private String ideUser;
    private Profile profile;
    private String name;
    private String lastName;
    private String age;
    private String email;
    private String phone;
    private String imageUri;
    private LocalDateTime fechaCreacion;
}
