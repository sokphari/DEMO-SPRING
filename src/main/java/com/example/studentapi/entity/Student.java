package com.example.studentapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Student {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String email;
    private String password;

    //constructure default
    public Student(){

    }

    //constructure with parameter
    public Student(String name,String gender,String email, String password){
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }
    public long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    } 
    //getter and setter name
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    // getter and setter email
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    //getter and setter password
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    //getter and setter gender
    public String getGender(){
        return  this.gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

}
