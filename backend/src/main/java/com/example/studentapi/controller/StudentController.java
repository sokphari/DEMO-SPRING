package com.example.studentapi.controller;

import com.example.studentapi.entity.Student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentapi.repository.StudentRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController 
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository; 
    
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    //calling annotation @Mapping 
    @GetMapping
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    
    //find student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return studentRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Request data student in body
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setId(null);
        Student saveStudent = studentRepository.save(student);     
        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudent);
    }
    //delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        if(!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //update student by id
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
        @PathVariable Long id, 
        @RequestBody Student updatedStudent
    ){
        return studentRepository.findById(id)
        .map(student -> {
            student.setName(updatedStudent.getName());
            student.setGender(updatedStudent.getGender());
            student.setEmail(updatedStudent.getEmail());
            student.setPassword(updatedStudent.getPassword());
            return ResponseEntity.ok(studentRepository.save(student));
        }).
        orElseGet(() -> ResponseEntity.notFound().build());
        
    }
    
    

}