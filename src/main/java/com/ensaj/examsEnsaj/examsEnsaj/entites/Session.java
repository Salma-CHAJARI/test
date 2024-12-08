package com.ensaj.examsEnsaj.examsEnsaj.entites;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private int idSession;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Admin admin;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "date_debut", nullable = false)
    private String dateDebut;

    @Column(name = "date_fin", nullable = false)
    private String dateFin;

    @Column(name = "heure_matin_debut")
    private String heureMatinDebut;

    @Column(name = "heure_matin_fin")
    private String heureMatinFin;

    @Column(name = "heure_soir_debut")
    private String heureSoirDebut;

    @Column(name = "heure_soir_fin")
    private String heureSoirFin;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Exam> exams;
}
