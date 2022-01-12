package moySkladTest.entity;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class Student {

    private String lastName;
    private HashMap<String, String> study=new HashMap<>();

    public Student() {
    }

    public Student(String lastName, HashMap study) {
        this.lastName = lastName;
        this.study=study;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudy(HashMap study) {
        this.study=study;
    }

    public HashMap getStudy() {
        return study;
    }

    public String getLastName() {
        return lastName;
    }


}

