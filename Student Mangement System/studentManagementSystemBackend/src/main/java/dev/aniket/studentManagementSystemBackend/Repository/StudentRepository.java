package dev.aniket.studentManagementSystemBackend.Repository;

import dev.aniket.studentManagementSystemBackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmailAndPhoneNumber(String email, String phoneNumber);
}
