package com.ensah.core.dao;

import com.ensah.core.hk.Contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ContactDao extends JpaRepository<Contact, Long>{
	
	List<Contact> findBynom(String nom);
	List<Contact> findBypersonnalNumber(String personnalNumber);
	List<Contact> findByprofessionalNumber(String professionalNumber);
}
