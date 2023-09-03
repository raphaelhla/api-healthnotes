package com.ufcg.apihealthnotes.entities.caregiver;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ufcg.apihealthnotes.dto.CaregiverRegisterDTO;
import com.ufcg.apihealthnotes.dto.CaregiverUpdateDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "caregiver")
@Getter
@Setter
@EqualsAndHashCode(of = "cpf")
@AllArgsConstructor
//@NoArgsConstructor
@JsonIgnoreProperties(value = { "accountNonLocked", "credentialsNonExpired", "accountNonExpired", "enabled",
		"authorities", "username" })
public class Caregiver implements UserDetails {

	private static final long serialVersionUID = 1L;

//	@Column(name = "caregiver_cpf")
	@Id
	private String cpf;

	@Column(nullable = false, length = 255)
	private String email;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(nullable = false, length = 20)
	private String lastname;

//    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL)
//    private Map<LocalDate, Calendar> calendar;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "caregivers", fetch = FetchType.EAGER)
//    private Set<Patient> patients = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CaregiverPatient> caregiverPatients;

//	@JsonIgnore
//	@OneToOne(mappedBy = "caregiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private CaregiverWeeklySchedule weeklySchedule;

	public Caregiver() {
		this.caregiverPatients = new HashSet<>();
//        this.weeklySchedule = new CaregiverWeeklySchedule(this);
	}

	public Caregiver(CaregiverRegisterDTO dadosCadastro) {
		this.cpf = dadosCadastro.cpf();
		this.email = dadosCadastro.email();
		this.password = dadosCadastro.password();
		this.name = dadosCadastro.name();
		this.lastname = dadosCadastro.lastname();
		
		this.caregiverPatients = new HashSet<>();
//        this.weeklySchedule = new CaregiverWeeklySchedule(this);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getNumberPatients() {
		return caregiverPatients.size();
	}

	public void addCaregiverPatient(CaregiverPatient caregiverPatient) {
		this.caregiverPatients.add(caregiverPatient);
	}

	public void updateFromDTO(CaregiverUpdateDTO caregiverUpdateDTO) {
//		this.email = caregiverUpdateDTO.email();
		this.name = caregiverUpdateDTO.name();
		this.lastname = caregiverUpdateDTO.lastname();
	}
}
