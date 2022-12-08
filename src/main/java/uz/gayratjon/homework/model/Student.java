package uz.gayratjon.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {

    public Student() {
    }

    public Student(Long id, String name, String lastName, Integer age, Course course) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
    }

    private Long id;
    private String name;
//    @JsonProperty("last_Name")  // bu lastName ni last_Name qilib qo'yadi
    private String lastName;
    private Integer age;


    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
