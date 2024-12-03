package com.ensaj.examsEnsaj.examsEnsaj.respository;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Ensiegnent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Ensiegnent, Integer> {
}
