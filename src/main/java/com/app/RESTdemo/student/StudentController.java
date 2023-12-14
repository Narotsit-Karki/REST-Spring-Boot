package com.app.RESTdemo.student;

import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService _sal;

    @Autowired
    public StudentController(StudentService service){
        this._sal = service;
    }
    @GetMapping("/all")
    public List<Student> getAllStudents(){
      return this._sal.getAllStudent();
    }

    @GetMapping("/{id}")
    public Optional<Student> findStudentById(@PathVariable Integer id) throws UserNotFoundException{

        return this._sal.getStudentById(id);
    }

    @GetMapping("/register")
    public Student registerStudent(@RequestBody Student stu){
        return this._sal.addStudent(stu);
    }

    @GetMapping("/delete/{id}")
    public Boolean removeStudent(@PathVariable Integer id)throws UserNotFoundException{
        return this._sal.deleteStudent(id);
    }

}
