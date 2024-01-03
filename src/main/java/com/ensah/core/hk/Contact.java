package com.ensah.core.hk;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContact;
	
    @ManyToMany(mappedBy = "contacts")
    private List<Groupe> groupes=new ArrayList<Groupe>();
	
	@NotBlank(message = "This field is required")
	private String prenom;

	@NotBlank(message = "This field is required")
	private String nom;
	
	@NotBlank(message = "This field is required")
	@Pattern(regexp = "^[0-9]{10}", message= "The Number  must be  followed by  10 digits")
	private String professionalNumber;
	
	@NotBlank(message = "This field is required")
	@Pattern(regexp = "^[0-9]{10}", message= "The Number  must be  followed by  10 digits")
	private String personnalNumber;
	
	@NotBlank(message = "This field is required")
	private String address;
	
	@Email(message = "Enter a valid email")
	@NotBlank(message = "This field is required")
	private String Personnalemail;
	
	@Email(message = "Enter a valid email")
	@NotBlank(message = "This field is required")
	private String Professionalemail;
	
	@NotBlank(message = "This field is required")
	private String gender;
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Contact)) {
			return false;
		}
		return idContact != null && idContact.equals(((Contact) o).getIdContact());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public Long getIdContact() {
		return idContact;
	}

	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getProfessionalNumber() {
		return professionalNumber;
	}

	public void setProfessionalNumber(String professionalNumber) {
		this.professionalNumber = professionalNumber;
	}

	public String getPersonnalNumber() {
		return personnalNumber;
	}

	public void setPersonnalNumber(String personnalNumber) {
		this.personnalNumber = personnalNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPersonnalemail() {
		return Personnalemail;
	}

	public void setPersonnalemail(String personnalemail) {
		Personnalemail = personnalemail;
	}

	public String getProfessionalemail() {
		return Professionalemail;
	}

	public void setProfessionalemail(String professionalemail) {
		Professionalemail = professionalemail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "contact [idContact=" + idContact + ", prenom=" + prenom + ", nom=" + nom + ", professionalNumber="
				+ professionalNumber + ", personnalNumber=" + personnalNumber + ", address=" + address
				+ ", Personnalemail=" + Personnalemail + ", Professionalemail=" + Professionalemail + ", gender="
				+ gender + "]";
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}
}
