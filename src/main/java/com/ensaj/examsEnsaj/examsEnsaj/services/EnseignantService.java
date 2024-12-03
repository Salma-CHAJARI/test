package com.ensaj.examsEnsaj.examsEnsaj.services;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Ensiegnent;
import com.ensaj.examsEnsaj.examsEnsaj.respository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    public List<Ensiegnent> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    public Ensiegnent getEnseignantById(int id) {
        return enseignantRepository.findById(id).orElse(null);
    }
    public Ensiegnent createEnseignant(Ensiegnent enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // Mettre à jour un enseignant existant
    public Ensiegnent updateEnseignant(int id, Ensiegnent enseignantDetails) {
        return enseignantRepository.findById(id).map(existingEnseignant -> {
            existingEnseignant.setNom(enseignantDetails.getNom());
            existingEnseignant.setPrenom(enseignantDetails.getPrenom());
            existingEnseignant.setEmail(enseignantDetails.getEmail());
            existingEnseignant.setNumero(enseignantDetails.getNumero());
            existingEnseignant.setDisponibilite(enseignantDetails.getDisponibilite());
            existingEnseignant.setDepartement(enseignantDetails.getDepartement());
            return enseignantRepository.save(existingEnseignant);
        }).orElse(null);
    }
    public boolean deleteEnseignant(int id) {
        if (enseignantRepository.existsById(id)) {
            enseignantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
