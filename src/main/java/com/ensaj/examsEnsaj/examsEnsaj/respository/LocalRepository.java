package com.ensaj.examsEnsaj.examsEnsaj.respository;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
    boolean existsByNomAndTailleAndType(String nom, double taille, String type);
}
