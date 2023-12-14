package com.app.RESTdemo.student;
import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    @Column(unique = true)
    private String rollno;
    private String fullname;
    private String phone;
    private String level;
    private String faculty;

    @Transient
    private String email;


    public Student(int sid, String rollno, String fullname, String phone, String level, String faculty) {
        this.sid = sid;
        this.rollno = rollno;
        this.fullname = fullname;
        this.phone = phone;
        this.level = level;
        this.faculty = faculty;
    }

    public Student(){}
    public Student(String rollno, String fullname, String phone, String level, String faculty) {
        this.rollno = rollno;
        this.fullname = fullname;
        this.phone = phone;
        this.level = level;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", rollno='" + rollno + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail(){
        return this.fullname+this.phone+"@gmail.com";
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}