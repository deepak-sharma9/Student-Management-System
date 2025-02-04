package org.studentmanagementsystem.services;

import org.studentmanagementsystem.entities.Admin;
import org.studentmanagementsystem.entities.Student;

import java.util.List;

public interface StudentService {

    Admin registerAdmin(Admin admin);

    Admin loginAdmin(String email, String password);

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);

}
