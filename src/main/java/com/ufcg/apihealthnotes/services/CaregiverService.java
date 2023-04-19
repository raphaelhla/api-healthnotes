package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.CaregiverDTO;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.repositories.CaregiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    public Caregiver saveCaregiver(CaregiverDTO caregiver) {
        Caregiver newCaregiver = new Caregiver(caregiver.getCpf(), caregiver.getName());
        return this.caregiverRepository.save(newCaregiver);
    }

    public Caregiver findCaregiver(String caregiverCpf) {
        Caregiver caregiver = this.caregiverRepository.findById(caregiverCpf).get();
        return caregiver;
    }
}
