package com.ensaj.examsEnsaj.examsEnsaj.services;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Session;
import com.ensaj.examsEnsaj.examsEnsaj.respository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(int id) {
        return sessionRepository.findById(id).orElse(null);
    }

    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session updateSession(int id, Session sessionDetails) {
        return sessionRepository.findById(id).map(existingSession -> {
            existingSession.setAdmin(sessionDetails.getAdmin());
            existingSession.setType(sessionDetails.getType());
            existingSession.setDateDebut(sessionDetails.getDateDebut());
            existingSession.setDateFin(sessionDetails.getDateFin());
            existingSession.setHeureMatinDebut(sessionDetails.getHeureMatinDebut());
            existingSession.setHeureMatinFin(sessionDetails.getHeureMatinFin());
            existingSession.setHeureSoirDebut(sessionDetails.getHeureSoirDebut());
            existingSession.setHeureSoirFin(sessionDetails.getHeureSoirFin());
            return sessionRepository.save(existingSession);
        }).orElse(null);
    }

    public boolean deleteSession(int id) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
