package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.ExamDTO;
import com.ufcg.apihealthnotes.entities.Exam;
import com.ufcg.apihealthnotes.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public Exam saveExam(Exam exam) {
        return this.examRepository.save(exam);
    }

}
