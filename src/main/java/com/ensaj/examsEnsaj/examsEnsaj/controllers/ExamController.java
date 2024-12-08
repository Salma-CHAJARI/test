package com.ensaj.examsEnsaj.examsEnsaj.controllers;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Exam;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Local;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Session;
import com.ensaj.examsEnsaj.examsEnsaj.services.ExamService;
import com.ensaj.examsEnsaj.examsEnsaj.services.LocalService;
import com.ensaj.examsEnsaj.examsEnsaj.services.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private SessionService sessionService;
    @Autowired
    private LocalService localService;

    @GetMapping("/locaux")
    @ResponseBody
    public List<Local> getLocaux() {
        return localService.getAllLocaux();
    }


    @GetMapping("/exam/{sessionId}")
    public String examensPage(@PathVariable int sessionId, Model model) {
        // Récupérer la session
        Session session = sessionService.getSessionById(sessionId);
        List<Local> locaux = localService.getAllLocaux();
        model.addAttribute("locaux", locaux);
        model.addAttribute("session", session);
        if (session == null) {
            model.addAttribute("errorMessage", "Session not found");
            return "error";
        }

        if (session.getDateDebut() == null || session.getDateFin() == null) {
            model.addAttribute("errorMessage", "Dates de la session non définies");
            return "error";
        }

        try {
            List<String> dates = generateDatesBetween(session.getDateDebut().toString(), session.getDateFin().toString());
            List<String> creneaux = generateCreneaux(session);

            if (dates == null || dates.isEmpty() || creneaux == null || creneaux.isEmpty()) {
                model.addAttribute("errorMessage", "Dates ou créneaux non disponibles");
                return "error";
            }

            model.addAttribute("session", session);
            model.addAttribute("dates", dates);
            model.addAttribute("creneaux", creneaux);
            System.out.println("Dates: " + dates);
            System.out.println("Créneaux: " + creneaux);
        } catch (ParseException e) {
            model.addAttribute("errorMessage", "Erreur lors du traitement des dates");
            return "error";
        }

        return "exams";
    }
    @PostMapping("/addExam")
    public String addExam(@RequestParam String dateExamen,
                          @RequestParam String heureExamen,
                          @RequestParam String module,
                          @RequestParam String option,
                          @RequestParam String responsableModule,
                          @RequestParam int nombreEtudiants,
                          @RequestParam List<Integer> locauxExamenIds,
                          @RequestParam(required = false) Integer sessionId,  // Use Integer to handle possible null values
                          Model model) {
        // Ensure that sessionId is correctly mapped as an integer
        Session session = sessionService.getSessionById(sessionId);
        if (sessionId == null) {
            model.addAttribute("errorMessage", "Session ID is missing");
            return "error";
        }

        // Récupérer les locaux associés aux IDs donnés
        List<Local> locaux = localService.getLocauxByIds(locauxExamenIds);

        // Créer un nouvel objet Examen
        Exam exam = new Exam();
        exam.setDateExamen(dateExamen);
        exam.setHeureExamen(heureExamen);
        exam.setModule(module);
        exam.setOption(option);
        exam.setResponsableModule(responsableModule);
        exam.setNombreEtudiants(nombreEtudiants);
        exam.setLocaux(locaux);  // Associer les locaux à l'examen
        exam.setSession(session); // Associer la session

        // Sauvegarder l'examen dans la base de données
        examService.creerExam(exam);

        // Rediriger vers la page des examens
        return "redirect:/exams";
    }



    @GetMapping("/deleteExam/{id}")
    public String deleteExam(@PathVariable int id, HttpSession httpSession) {

        Session currentSession = (Session) httpSession.getAttribute("currentSession");

        examService.deleteExam(id);

        return "redirect:/exam/" + currentSession.getIdSession();
    }

    public List<String> generateDatesBetween(String dateDebut, String dateFin) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date debut = sdf.parse(dateDebut);
        Date fin = sdf.parse(dateFin);

        List<String> dates = new ArrayList<>();

        dates.add(sdf.format(debut));

        while (debut.before(fin)) {
            debut = new Date(debut.getTime() + (1000 * 60 * 60 * 24));
            dates.add(sdf.format(debut));
        }

        if (!debut.equals(fin)) {
            dates.add(sdf.format(fin));
        }

        return dates;
    }
    private List<String> generateCreneaux(Session session) {
        List<String> creneaux = new ArrayList<>();

        if (session.getHeureMatinDebut() != null && session.getHeureMatinFin() != null) {
            creneaux.addAll(createCreneaux(session.getHeureMatinDebut(), session.getHeureMatinFin()));
        }

        if (session.getHeureSoirDebut() != null && session.getHeureSoirFin() != null) {
            creneaux.addAll(createCreneaux(session.getHeureSoirDebut(), session.getHeureSoirFin()));
        }

        return creneaux;
    }

    private List<String> createCreneaux(String heureDebut, String heureFin) {
        List<String> creneaux = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date debut = sdf.parse(heureDebut);
            Date fin = sdf.parse(heureFin);

            long duration = 2 * 60 * 60 * 1000;

            Date currentStart = debut;

            while (currentStart.before(fin)) {

                Date currentEnd = new Date(currentStart.getTime() + duration);

                if (currentEnd.after(fin)) {
                    currentEnd = fin;
                }

                creneaux.add(sdf.format(currentStart) + " - " + sdf.format(currentEnd));

                currentStart = new Date(currentEnd.getTime() + 5 * 60 * 1000);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return creneaux;
    }

}
