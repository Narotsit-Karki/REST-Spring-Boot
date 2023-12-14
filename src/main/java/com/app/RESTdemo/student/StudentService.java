package com.app.RESTdemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository _srp;

    @Autowired
    public StudentService(StudentRepository repository){
        this._srp = repository;
    }
    public List<Student> getAllStudent(){
        return this._srp.findAll();
    }

    public Optional<Student> getStudentById(Integer id) throws UserNotFoundException{
        Optional<Student> stud = this._srp.findById(id);
        if (stud.isPresent())
            return stud;

        throw new UserNotFoundException("ID: "+id+" Not found");
    }

    public Student addStudent(Student stu){

        Student newStud = this._srp.save(stu);
        return newStud;
    }

    public Boolean deleteStudent(Integer id)throws  UserNotFoundException{
        Optional<Student> stu = this.getStudentById(id);
        this._srp.deleteById(id);
        return true;
    }
}
