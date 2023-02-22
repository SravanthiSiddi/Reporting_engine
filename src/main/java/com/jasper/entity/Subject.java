package com.jasper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECT_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBJECT_ID")
    private Integer subjectId;
    @Column(name = "SUBJECT_NAME")
    private String subjectName;
    @Column(name = "MARKS")
    private long marks;


}
