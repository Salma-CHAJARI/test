package com.ensaj.examsEnsaj.examsEnsaj.entites;

import jakarta.persistence.*;

public class Ensiegnent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enseignant")
    private int idEnseignant;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "prenom", nullable = false)
    private String prenom;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "numero", nullable = false)
    private String numero;
    @Column(name = "disponibilite", nullable = false)
    private String disponibilite;

    @ManyToMany
    @JoinColumn(name = "id_departement", referencedColumnName = "id")
    private Departement departement;


}
