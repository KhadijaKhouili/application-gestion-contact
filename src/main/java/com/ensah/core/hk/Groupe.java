package com.ensah.core.hk;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


import java.util.*;

@Entity
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGroupe;
	
	@NotBlank(message = "This field is required")
	private String nomGroupe;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "groupe_contact",joinColumns = @JoinColumn(name = "groupe_id"),inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private Set<Contact> contacts =new HashSet<Contact>();
	
    public Groupe() {
    
    }
	
	@Override
	public String toString() {
		return "Groupe [idGroupe=" + idGroupe + ", nom=" + nomGroupe + ", groupes=" + contacts + "]";
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
		for (Contact c : contacts) {
			c.getGroupes().add(this);
		}
	}
	
	public void addContact(Contact c) {
		contacts.add(c);
		c.getGroupes().add(this);
	}
	
	public void removeContact(Contact c) {
		contacts.remove(c);
		c.getGroupes().remove(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Groupe)) {
			return false;
		}
		return idGroupe != null && idGroupe.equals(((Groupe) o).getIdGroupe());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public Long getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Long idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
}
