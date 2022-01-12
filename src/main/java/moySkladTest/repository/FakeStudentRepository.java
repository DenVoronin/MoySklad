package moySkladTest.repository;

import moySkladTest.entity.Student;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Component
public class FakeStudentRepository {
    private Map<String, HashMap> students= new HashMap<>();



    public void add(Student student) {
        students.put(student.getLastName(), student.getStudy());
    }

    public HashMap get(String lastName) {
        return  students.get(lastName);
    }

    public void delete(String lastName) {
        students.remove(lastName);
    }

    public Map getAll() {
        return students;
    }


}
