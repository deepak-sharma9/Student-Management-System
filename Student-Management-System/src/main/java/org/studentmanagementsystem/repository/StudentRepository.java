package org.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.studentmanagementsystem.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
