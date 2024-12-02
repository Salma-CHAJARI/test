package com.ensaj.examsEnsaj.examsEnsaj.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Getter
@Setter
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom_exam")
    private String nomExam;

    @Column(name = "nom_de_module")
    private String nomModule;

    @ManyToOne
    @JoinColumn(name = "id_department", nullable = false)
    private Departement department;

    @Column(name = "nbr_etudiants")
    private int nbrEtudiants;

    @Column(name = "date_exam")
    private LocalDate dateExam;

    @Column(name = "heure_exam")
    private String  heureExam;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Local local;

    @ManyToMany
    @JoinTable(
            name = "exam_enseignants",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id")
    )
    private List<Ensiegnent> enseignants;




}
