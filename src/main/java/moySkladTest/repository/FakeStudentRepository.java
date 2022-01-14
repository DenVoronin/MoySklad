package moySkladTest.repository;

import moySkladTest.entity.Student;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
public class FakeStudentRepository {
    private TreeMap<String, HashMap> students= new TreeMap<>();



    public void add(Student student) {
        students.put(student.getLastName(), student.getStudy());
    }

    public HashMap get(String lastName) {
        return  students.get(lastName);
    }

    public void delete(String lastName) {
        students.remove(lastName);
    }

    public TreeMap getAll() {

        return students;
    }


}
