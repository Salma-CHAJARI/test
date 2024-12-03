package com.ensaj.examsEnsaj.examsEnsaj.services;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Departement;
import com.ensaj.examsEnsaj.examsEnsaj.respository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }
    public Departement getDepartementById(int id) {
        return departementRepository.findById(id).orElse(null);
    }
    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }
    public Departement updateDepartement(int id, Departement departementDetails) {
        return departementRepository.findById(id).map(existingDepartement -> {
            existingDepartement.setNomDepartement(departementDetails.getNomDepartement());
            existingDepartement.setSession(departementDetails.getSession());
            return departementRepository.save(existingDepartement);
        }).orElse(null);
    }
    public boolean deleteDepartement(int id) {
        if (departementRepository.existsById(id)) {
            departementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
