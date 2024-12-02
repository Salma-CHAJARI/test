package com.ensaj.examsEnsaj.examsEnsaj.entites;



import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = "locals")
@Getter
@Setter
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private int idLocal;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session idSession;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "taille", nullable = false)
    private double taille;

    @Column(name = "type", nullable = false)
    private String type;
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Exam> exams;

}