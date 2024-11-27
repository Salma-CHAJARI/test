package com.ensaj.examsEnsaj.examsEnsaj.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @JoinColumn(name = "id_department", referencedColumnName = "id", nullable = false)
    private Departement department;

    @Column(name = "nbr_etudiants")
    private int nbrEtudiants;

    @Column(name = "date_exam")
    private LocalDate dateExam;

    @Column(name = "heure_exam")
    private String  heureExam;

    @ManyToMany
    @JoinColumn(name = "id_enseignant", referencedColumnName = "id", nullable = false)
    private Ensiegnent enseignant;

    @OneToMany
    @JoinColumn(name = "local_id_local")
    private Local local;
}
