package com.jasper.bean;

import lombok.*;

@Data
@NonNull
@ToString
@Getter @Setter

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
}
