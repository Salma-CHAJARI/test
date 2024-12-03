package com.ensaj.examsEnsaj.examsEnsaj.services;

import com.ensaj.examsEnsaj.examsEnsaj.entites.Exam;

import com.ensaj.examsEnsaj.examsEnsaj.respository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }
    public Exam getExamById(int id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    // Créer un nouvel examen
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam updateExam(int id, Exam examDetails) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            exam.setDateExam(examDetails.getDateExam());
            exam.setLocal(examDetails.getLocal());
            return examRepository.save(exam);
        } else {
            return null;
        }
    }

    public boolean deleteExam(int id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
