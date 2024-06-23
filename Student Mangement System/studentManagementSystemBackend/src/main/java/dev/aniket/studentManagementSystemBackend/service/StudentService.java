package dev.aniket.studentManagementSystemBackend.service;

import dev.aniket.studentManagementSystemBackend.Repository.StudentRepository;
import dev.aniket.studentManagementSystemBackend.model.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private Logger LOGGER = LoggerFactory.getLogger(Student.class);

    private final StudentRepository studentRepository;

    public List<Student> getAllStudentsFromDB() {
        LOGGER.info("All Student in DB is return");
        return studentRepository.findAll(Sort.by(Sort.Order.by("name")));
    }

    public Student getStudentByIdFromDB(Integer id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isEmpty()) {
            LOGGER.error("This Student ID is not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Student ID is not exist");
        }
        return existingStudent.get();
    }

    public void addStudentInDB(Student student) {
        Student afterAddStudent = studentRepository.save(student);
        if (afterAddStudent == null) {
            LOGGER.error("This student info is not added");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This student is not add to the database");
        }
    }

    public void updateStudentInDB(Student student) {
        Optional<Student> existingStudent = studentRepository.findById(student.getId());

        if (existingStudent.isEmpty()) {
            LOGGER.error("This Student info is not matching in the DB");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This student is not available in DB");
        }

        //now update the student
        studentRepository.save(student);
    }

    public void deleteStudentFromDB(Integer id) {
        if (getStudentByIdFromDB(id) == null) {
            LOGGER.error("This Student id is not present for delete");
            return;
        }
        studentRepository.deleteById(id);
    }
}
