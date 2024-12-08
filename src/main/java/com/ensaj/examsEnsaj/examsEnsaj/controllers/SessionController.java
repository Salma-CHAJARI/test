package com.ensaj.examsEnsaj.examsEnsaj.controllers;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Admin;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Session;
import com.ensaj.examsEnsaj.examsEnsaj.respository.SessionRepository;
import com.ensaj.examsEnsaj.examsEnsaj.services.AdminService;
import com.ensaj.examsEnsaj.examsEnsaj.services.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SessionController {


    private final AdminService adminService;
    private final SessionService sessionService;

    public SessionController(AdminService adminService, SessionService sessionService) {
        this.adminService = adminService;
        this.sessionService = sessionService;
    }

    @PostMapping("/sessions")
    public String ajouterSession(Session sessionEntity, Model model, HttpSession httpSession) {


        Admin admin = (Admin) httpSession.getAttribute("admin");
        if (admin != null) {
            sessionEntity.setAdmin(admin);
        }

        if (sessionService.createSession(sessionEntity) != null) {
            model.addAttribute("admin", sessionEntity);
            List<Session> sessions = sessionService.getAllSessions();
            model.addAttribute("sessions", sessions);
            model.addAttribute("successMessage", "La session a été ajoutée avec succès !");
            return "home";
        }


        model.addAttribute("error", "Échec de la création de la session.");
        return "home";
    }
    @GetMapping("/selectSession/{id}")
    public String selectSession(@PathVariable int id, HttpSession httpSession) {
        Session selectedSession = sessionService.getSessionById(id);

        if (selectedSession != null) {
            // Stocker la session sélectionnée dans la session HTTP
            httpSession.setAttribute("currentSession", selectedSession);
            return "redirect:/dashboard?id=" + id; // Redirige vers le tableau de bord
        }

        return "redirect:/home"; // Redirige vers la page d'accueil si la session n'est pas trouvée
    }
    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam int id, Model model) {

        Session session = sessionService.getSessionById(id);
        model.addAttribute("session", session);
        return "dashboard";
    }
    @GetMapping("/home")
    public String homePage(Model model) {

        Session csession = sessionService.getSessionById(1);
        model.addAttribute("csession", csession);
        return "home";
    }
}

