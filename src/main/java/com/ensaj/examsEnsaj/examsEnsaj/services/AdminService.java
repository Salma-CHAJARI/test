package com.ensaj.examsEnsaj.examsEnsaj.services;
import com.ensaj.examsEnsaj.examsEnsaj.respository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Admin;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    // Méthode pour trouver un admin par son nom d'utilisateur
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);

    }

}
