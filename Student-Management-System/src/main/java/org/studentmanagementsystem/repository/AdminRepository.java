package org.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.studentmanagementsystem.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByEmail(String email);
}
