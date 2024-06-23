package dev.aniket.studentManagementSystemBackend.controller;

import dev.aniket.studentManagementSystemBackend.model.Student;
import dev.aniket.studentManagementSystemBackend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudentsFromDB(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.getStudentByIdFromDB(id), HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
//        System.out.println("Try to add the student: " + student);
        studentService.addStudentInDB(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        student.setId(id);
        studentService.updateStudentInDB(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudentFromDB(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
