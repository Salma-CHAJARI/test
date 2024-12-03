package com.ensaj.examsEnsaj.examsEnsaj.respository;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
