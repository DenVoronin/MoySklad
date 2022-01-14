package moySkladTest.service;

import moySkladTest.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import moySkladTest.repository.FakeStudentRepository;

import java.util.*;

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

    public TreeMap scoreList() {
        TreeMap<String, HashMap> studentsScoreList = new TreeMap<>();
        //This is deep clone to avoid change parameters in FakeStudentRepository.students
        fakeStudentRepository.getAll().forEach((k, v) ->
        {
            HashMap<String, String> map0 = new HashMap<>();
            for (Object key : fakeStudentRepository.get((String) k).keySet()) {
                map0.put(key.toString(), fakeStudentRepository.get((String) k).get(key).toString());
            }
            studentsScoreList.put((String) k, map0);
        });

//This is finding of average score for all subjects of any student
        studentsScoreList.forEach(
                (k, v) ->
                        v.forEach((k1, v1) ->
                                {
                                    String str = v1.toString().replaceAll(",", "");
                                    float sum = 0;
                                    for (int i = 0; i < str.length(); i++) {
                                        sum = sum + Character.getNumericValue(str.charAt(i));
                                    }
                                    sum = sum / str.length();
                                    v.put(k1, String.format("%.1f", sum));
                                }

                        ));

        return studentsScoreList;

    }
}
