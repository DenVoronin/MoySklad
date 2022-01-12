package moySkladTest.service;

import moySkladTest.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import moySkladTest.repository.FakeStudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private FakeStudentRepository fakeStudentRepository;

    @Autowired
    StudentService(FakeStudentRepository fakeStudentRepository) {
        this.fakeStudentRepository = fakeStudentRepository;
    }

    public void addStudent(Student student) {
        fakeStudentRepository.add(student);
    }

    public void deleteStudent(String lastName) {
        fakeStudentRepository.delete(lastName);
    }

    public HashMap getStudent(String lastName) {
        return fakeStudentRepository.get(lastName);
    }

    public Map getAllStudents() {
        return fakeStudentRepository.getAll();
    }

    public List<String> subjects(String lastName) {
        List<String> subjects =
                new ArrayList<>(
                        fakeStudentRepository
                                .get(lastName)
                                .keySet()
                                .stream()
                                .toList());

        return subjects;
    }

    public HashMap score(String subject, String lastName) {
        Map<String, String> score = new HashMap<>();
        float sum = 0;
        String raw = fakeStudentRepository.get(lastName).get(subject).toString().replaceAll(",", "");
        for (int i = 0; i < raw.length(); i++) {
            sum = sum + Character.getNumericValue(raw.charAt(i));
        }
        sum = sum / raw.length();
        score.put(subject, String.format("%.1f", sum));
        return (HashMap) score;
    }
}
