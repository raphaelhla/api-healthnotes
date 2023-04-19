package com.ufcg.apihealthnotes.services;

import com.ufcg.apihealthnotes.dto.SurgeryDTO;
import com.ufcg.apihealthnotes.entities.Surgery;
import com.ufcg.apihealthnotes.repositories.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurgeryService {

    @Autowired
    private SurgeryRepository surgeryRepository;
    public Surgery saveSurgery(SurgeryDTO surgeryDTO) {

    }
}
