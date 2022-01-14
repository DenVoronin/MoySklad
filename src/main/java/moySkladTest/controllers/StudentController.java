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
    public String create(@RequestBody Student student) {
        Boolean check = true;
        for (Object stKey : student.getStudy().keySet()) {

            for (char c : student.getStudy().get(stKey).toString().toCharArray()) {
                if (
                        (Character.getNumericValue(c) <= 5 &&
                                Character.getNumericValue(c) >= 2) || c == ',') {


                } else {
                    check = false;
                    return "ERROR: send subjects and score like {\"math\":\"3,4,3\"} for example. "
                            + "min 2, max 5";
                }
            }
        }
        if (check) {
            studentService.addStudent(student);
        }
        return "OK";
    }


    @GetMapping(value = "/getall")
    @ApiOperation(value = "Get all students")
    public Map getAllStud() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/delete/{lastname}")
    @ApiOperation(value = "Delete student")
    public String del(@PathVariable("lastname") String lastName) {
        if (studentService.getAllStudents().keySet().contains(lastName)) {
            studentService.deleteStudent(lastName);
        } else {
            return "Incorrect student lastname";
        }
        return "OK";
    }

    @GetMapping(value = "/get/{lastname}")
    @ApiOperation(value = "Get student")
    public HashMap gett(@PathVariable("lastname") String lastName) {
        if (studentService.getAllStudents().keySet().toString().contains(lastName)) {
            return studentService.getStudent(lastName);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("ERROR", "Not find this student");
            return error;
        }
    }

    @GetMapping(value = "/get/subjects/{lastname}")
    @ApiOperation(value = "Get subjects for student")
    public ArrayList getsub(@PathVariable("lastname") String lastName) {
        if (studentService.getAllStudents().keySet().contains(lastName)) {
            return (ArrayList) studentService.subjects(lastName);
        }
        ArrayList<String> error = new ArrayList<>();
        error.add("ERROR: Not find this student");
        return error;
    }

    @GetMapping(value = "/get/score/{subject}/{lastname}")
    @ApiOperation(value = "Get score of subject for student")
    public HashMap getsubscore(@PathVariable("lastname") String lastName,
                               @PathVariable("subject") String subject) {
        try {
            if (studentService.getAllStudents().keySet().contains(lastName) ||
                    studentService.getStudent(lastName).get(lastName).toString().contains(subject)) {
                return studentService.score(subject, lastName);
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("ERROR", "Not find this student or subject");
                return error;
            }
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("ERROR", "Not find this student or subject");
            return error;
        }
    }

    @GetMapping(value = "/getallscore")
    @ApiOperation(value = "Get score for all students")
    public Map getAllStudScore() {
        return studentService.scoreList();
    }
}
