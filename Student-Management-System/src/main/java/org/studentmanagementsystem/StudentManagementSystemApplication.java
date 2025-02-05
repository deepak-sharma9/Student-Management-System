package org.studentmanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.studentmanagementsystem.entities.Student;
import org.studentmanagementsystem.repository.StudentRepository;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception{

//        Student student2=new Student(null,"Ritesh", "Agarwal", "ritesh@gmail.com");
//        studentRepository.save(student2);
//
//        Student student3=new Student(null,"Manoj", "Singh", "manoj@gmail.com");
//        studentRepository.save(student3);
    }

}
