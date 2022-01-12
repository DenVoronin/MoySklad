package moySkladTest.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import moySkladTest.entity.Student;
import moySkladTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Api(value = "API", description = "Students endpoints")
public class StudentController {
    private StudentService studentService;

    @Autowired
    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "Add student")
    public void create(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping(value = "/getall")
    @ApiOperation(value = "Get all students")
    public Map getAllStud() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/delete/{lastname}")
    @ApiOperation(value = "Delete student")
    public void del(@PathVariable("lastname") String lastName) {
        studentService.deleteStudent(lastName);
    }

    @GetMapping(value = "/get/{lastname}")
    @ApiOperation(value = "Get student")
    public HashMap gett(@PathVariable("lastname") String lastName) {
        return studentService.getStudent(lastName);
    }

    @GetMapping(value = "/get/subjects/{lastname}")
    @ApiOperation(value = "Get subjects for student")
    public ArrayList getsub(@PathVariable("lastname") String lastName) {
        return (ArrayList) studentService.subjects(lastName);
    }

    @GetMapping(value = "/get/score/{subject}/{lastname}")
    @ApiOperation(value = "Get score of subject for student")
    public HashMap getsubscore(@PathVariable("lastname") String lastName,
                          @PathVariable("subject") String subject) {
        return studentService.score(subject, lastName);
    }
}
