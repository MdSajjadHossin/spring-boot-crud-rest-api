package com.springboot.demo.rest;

import com.springboot.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private  List<Student> theStudents;
    // define @PostConstruct to load the student data ... only once!

    @PostConstruct
    private void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Md Sajjad", "Hossin"));
        theStudents.add(new Student("Md Sajid", "Hossain"));
        theStudents.add(new Student("Md Maruful", "Islam"));
    }


    // define endpoint for "/student" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudent(){


        return theStudents;
    }

    // define endpoint for "/students/{studentId} - return at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // just index into the list ... keep it simple for now


        // check the studentId again list size
        if(studentId >= theStudents.size() || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }

    // Add an exception handler using @ExceptionHandler


}


