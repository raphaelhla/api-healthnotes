package com.ufcg.apihealthnotes.dto;

import java.util.List;

public class PatientDTO {

    private String caregiverCpf;
    private String cpf;
    private String name;
    private String age;
    private List<MedicineDTO> medicines;
    private List<VaccineDTO> vaccines;
    private List<SurgeryDTO> surgeries;
    private List<ExamDTO> exams;

    public PatientDTO(String caregiverCpf, String cpf, String name, String age,
                      List<MedicineDTO> medicines, List<VaccineDTO> vaccines, List<SurgeryDTO> surgeries, List<ExamDTO> exams) {

        this.caregiverCpf = caregiverCpf;
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.medicines = medicines;
        this.vaccines = vaccines;
        this.surgeries = surgeries;
        this.exams = exams;
    }

    public String getCaregiverCpf() {
        return caregiverCpf;
    }

    public void setCaregiverCpf(String caregiverCpf) {
        this.caregiverCpf = caregiverCpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<MedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineDTO> medicines) {
        this.medicines = medicines;
    }

    public List<VaccineDTO> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<VaccineDTO> vaccines) {
        this.vaccines = vaccines;
    }

    public List<SurgeryDTO> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(List<SurgeryDTO> surgeries) {
        this.surgeries = surgeries;
    }

    public List<ExamDTO> getExams() {
        return exams;
    }

    public void setExams(List<ExamDTO> exams) {
        this.exams = exams;
    }
}
