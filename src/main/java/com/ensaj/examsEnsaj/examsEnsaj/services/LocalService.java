package com.ensaj.examsEnsaj.examsEnsaj.services;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Local;
import com.ensaj.examsEnsaj.examsEnsaj.respository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;
    public List<Local> getAllLocals() {
        return localRepository.findAll();
    }
    public Local getLocalById(int id) {
        Optional<Local> local = localRepository.findById(id);
        return local.orElse(null);
    }
    public Local createLocal(Local local) {
        return localRepository.save(local);
    }

    public Local updateLocal(int id, Local localDetails) {
        Optional<Local> optionalLocal = localRepository.findById(id);
        if (optionalLocal.isPresent()) {
            Local local = optionalLocal.get();
            local.setNom(localDetails.getNom());
            local.setTaille(localDetails.getTaille());
            local.setType(localDetails.getType());
            local.setIdSession(localDetails.getIdSession());
            return localRepository.save(local);
        } else {
            return null;
        }
    }

    public boolean deleteLocal(int id) {
        if (localRepository.existsById(id)) {
            localRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
