package com.ensaj.examsEnsaj.examsEnsaj.entites;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Session;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Data
@Table(name = "departements")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departement")
    private int idDepartement;
    @ManyToOne
    @JoinColumn(name = "id_session", referencedColumnName = "id_session", nullable = false)
    private Session session;
    @Column(name = "nom_departement", nullable = false)
    private String nomDepartement;

}
