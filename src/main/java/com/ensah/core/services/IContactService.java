package com.ensah.core.services;

import java.util.List;

import com.ensah.core.hk.Contact;

public interface IContactService {
	
	public void addContact(Contact contact);

	public void updateContact(Contact contact);

	public List<Contact> getAllContacts();

	public void deleteContact(Long id);

	public Contact getContactById(Long id);
	
	public List<Contact> getContactsByNom(String nom);
	
	public List<Contact> getContactsBypersonnalNumber(String personnalNumber);
	
	public List<Contact> getContactsByprofessionalNumber(String professionalNumber);
	
	public List<Contact> getAllContactsSortedByName() ;
	

}
