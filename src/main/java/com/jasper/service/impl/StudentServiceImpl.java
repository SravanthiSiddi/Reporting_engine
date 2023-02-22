package com.jasper.service.impl;

import com.jasper.bean.EmployeeData;
import com.jasper.bean.Student;
//import com.jasper.bean.Subject;
import com.jasper.entity.Employee;
import com.jasper.entity.Subject;
import com.jasper.repository.EmployeeRepository;
import com.jasper.repository.StudentRepository;
import com.jasper.repository.SubjectRepository;
import com.jasper.service.StudentService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public byte[] getReport() {
        byte[] reportBytes = null;
        try {
            String filePath = ResourceUtils.getFile("classpath:FirstReport.jrxml").getAbsolutePath() ;
            Map<String, Object> parameterMap = new HashMap();
            parameterMap.put("studentName", "Sravanthi Siddi");
            List<Student> studentList = new ArrayList<>();

            Student student = new Student();
            student.setId("1");
            student.setFirstName("Sravanthi");
            student.setLastName("siddi");
            student.setCity("Siddipet");
            student.setStreet("patelpura");

            Student student1 = new Student();
            student1.setId("2");
            student1.setFirstName("Pavitra");
            student1.setLastName("Shakthi");
            student1.setCity("Siddipet");
            student1.setStreet("patelpura");

            studentList.add(student);
            studentList.add(student1);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(studentList);
            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameterMap, dataSource);
            reportBytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            System.out.println("Error getting exporting the report");
        }
        return reportBytes;
    }

    public byte[] getDynamicReport(){
        byte[] reportBytes = null;
        try {
            String filePath = ResourceUtils.getFile("classpath:dynamicReport.jrxml").getAbsolutePath() ;

            List<Subject> subjectList = new ArrayList<>();

          /*  Subject sub1 = new Subject(1,"Java",80);
            Subject sub2 = new Subject(2,"Pega",70);
            Subject sub3 = new Subject(3,"Python",80);
            Subject sub4 = new Subject(4,"tableau",80);
            Subject sub5 = new Subject(5,"Data Science",80);
            Subject sub6 = new Subject(6,"AWS",80);

            subjectList.add(sub1);
            subjectList.add(sub2);
            subjectList.add(sub3);
            subjectList.add(sub4);
            subjectList.add(sub5);
            subjectList.add(sub6);*/

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(subjectList);

            Map<String, Object> parameterMap = new HashMap();
            parameterMap.put("studentName", "Sravanthi Siddi");
            parameterMap.put("tableData",dataSource);

            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameterMap, new JREmptyDataSource());
            reportBytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            System.out.println("Error getting exporting the dynamic report");
        }
        return reportBytes;
    }

    public byte[] getDynamicReportFromDBData(){
        byte[] reportBytes = null;
        try {
            String filePath = ResourceUtils.getFile("classpath:dynamicReport.jrxml").getAbsolutePath() ;

            List<Subject> subjectList = new ArrayList<>();
            subjectList = subjectRepository.findAll();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(subjectList);

            Map<String, Object> parameterMap = new HashMap();
            parameterMap.put("studentName", "Sravanthi Siddi");
            parameterMap.put("tableData",dataSource);

            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameterMap, new JREmptyDataSource());
            reportBytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            System.out.println("Error getting exporting the dynamic report");
        }
        return reportBytes;
    }

    public byte[] getStaticReport(){
        byte[] reportBytes = null;
        ModelMapper modelMapper = new ModelMapper();
        try {
            String filePath = ResourceUtils.getFile("classpath:Simple_Blue.jrxml").getAbsolutePath() ;

            List<Employee> empList = new ArrayList<>();
            empList = employeeRepository.findByEmpName();

            List<EmployeeData> employeeData = new ArrayList<>();

            employeeData = empList.stream().map(a->{
                EmployeeData empData = new EmployeeData();
                empData.setEmpId(a.getEmpId());
                empData.setEmpName(a.getEmpName());
                empData.setSalary(a.getSalary());
                empData.setCity(a.getAddress().getCity());
                empData.setState(a.getAddress().getState());
                empData.setCountry(a.getAddress().getCountry());
                empData.setAssetName(a.getAsset().getAssetName());
                empData.setAssetNumber(a.getAsset().getAssetNumber());
                empData.setHouseNumber(a.getAddress().getHouseNumber());
                return empData;
            }).collect(Collectors.toList());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeData);

            Map<String, Object> parameterMap = new HashMap();
            parameterMap.put("studentName", "Sravanthi Siddi");
            parameterMap.put("tableData",dataSource);

            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameterMap, new JREmptyDataSource());
            reportBytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            System.out.println("Error getting exporting the dynamic report");
        }
        return reportBytes;
    }
}
