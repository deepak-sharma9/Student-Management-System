package org.studentmanagementsystem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.studentmanagementsystem.entities.Admin;
import org.studentmanagementsystem.entities.Student;
import org.studentmanagementsystem.services.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("admin",new Admin());
        return "register";
    }

    @PostMapping("/register")
    public String registerAdmin(@Valid @ModelAttribute Admin admin, BindingResult result, Model model){

        if (result.hasErrors()){
            return "register";
        }

        Admin isRegistered = studentService.registerAdmin(admin);

        if (isRegistered!=null){
            model.addAttribute("successMsg","User Registered successfully");
        }else {
            model.addAttribute("errorMsg","Registration Failed!!");
        }
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("admin",new Admin());
        return "login";
    }

    @PostMapping("/login")
    public String loginAdmin(@Valid @ModelAttribute Admin admin, BindingResult result, HttpSession session, Model model){
        Admin validAdmin=studentService.loginAdmin(admin.getEmail(),admin.getPassword());

        if (result.hasErrors()){
            return "login";
        }

        if (validAdmin!=null){
            session.setAttribute("session",validAdmin);
            return "redirect:/students";
        }else {
            model.addAttribute("errorMsg","Wrong email or password !!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if (session!=null){
            session.invalidate();
        }
        return "redirect:/login";
    }





    //Get All Students Rest API
    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        model.addAttribute("student",new Student());
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
     model.addAttribute("student",studentService.getStudentById(id));
     return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, Model model){

        //get student from database by id
        Student existingStudent=studentService.getStudentById(id);
        existingStudent.setId(student.getId());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        //save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    //Delete Student Handler
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
     studentService.deleteStudentById(id);
     return "redirect:/students";
    }

}
