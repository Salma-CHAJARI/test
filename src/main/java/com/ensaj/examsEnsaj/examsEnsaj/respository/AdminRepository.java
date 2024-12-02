package com.ensaj.examsEnsaj.examsEnsaj.respository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    Admin findByEmail(String email);
}
