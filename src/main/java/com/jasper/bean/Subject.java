package com.jasper.bean;

import lombok.*;

@Data
@NonNull
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Subject {
    private Integer subjectId;
    private String subjectName;
    private long marks;
}
