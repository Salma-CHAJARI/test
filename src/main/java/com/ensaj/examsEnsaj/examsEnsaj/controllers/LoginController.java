package com.ensaj.examsEnsaj.examsEnsaj.controllers;
import com.ensaj.examsEnsaj.examsEnsaj.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Admin;


@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(Admin admin, Model model) {
        // Récupérer l'admin par son nom d'utilisateur
        Admin existingAdmin = adminService.findByUsername(admin.getUsername());
        System.out.println(existingAdmin);


        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            // Authentification réussie
            return "redirect:/dashboard"; // Redirige vers une page sécurisée
        }

        // Erreur de connexion
        model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect !");
        return "login";
    }

    @PostMapping("/mailService")
    public String recoverPassword(String email, Model model) {
        Admin existingAdmin = adminService.findByEmail(email);
        if (existingAdmin!=null){
            return "redirect:/recupModepasse";
        }

        System.out.println("E-mail de récupération envoyé à : " + email);
        return "redirect:/?password-reset=success";
    }
}