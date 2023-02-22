package com.jasper.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeData {
    private long empId;
    private String empName;
    private double salary;
    private String houseNumber;
    private String city;
    private String state;
    private String country;
    private int assetNumber;
    private String assetName;
}
