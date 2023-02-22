package com.jasper.contoller;

import com.jasper.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping("/report")
    public ResponseEntity<byte[]> getReport(){
        System.out.println("Inside get report method");
        byte[] reportBytes = studentService.getReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("fileName","Student.pdf");
        return new ResponseEntity<>(reportBytes,headers, HttpStatus.OK);
    }

    @GetMapping("/dynamic-report")
    public ResponseEntity<byte[]> getDynamicReport(){
        System.out.println("Inside get dynamic report method");
        byte[] reportBytes = studentService.getDynamicReportFromDBData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("fileName","Student.pdf");
        return new ResponseEntity<>(reportBytes,headers, HttpStatus.OK);
    }

    @GetMapping("/static-report")
    public ResponseEntity<byte[]> getStaticReport(){
        System.out.println("Inside get static report method");
        byte[] reportBytes = studentService.getStaticReport();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("fileName","Employee.pdf");
        return new ResponseEntity<>(reportBytes,headers, HttpStatus.OK);
    }
}
