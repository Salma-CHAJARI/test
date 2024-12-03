package com.ensaj.examsEnsaj.examsEnsaj.respository;
import com.ensaj.examsEnsaj.examsEnsaj.entites.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
}

