package com.ensah.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


import com.ensah.core.dao.ContactDao;
import com.ensah.core.hk.Contact;

@Service
@Transactional
public class ContactServiceImpl implements IContactService{
	
	private ContactDao contactDao;

	@Autowired
	public ContactServiceImpl(ContactDao cDao) {

		contactDao = cDao;

	}

	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		contactDao.save(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub
		contactDao.save(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return contactDao.findAll();
	}

	@Override
	public void deleteContact(Long id) {
		// TODO Auto-generated method stub
		contactDao.deleteById(id);
	}

	@Override
	public Contact getContactById(Long id) {
		// TODO Auto-generated method stub
		return contactDao.findById(id).get();
	}

	@Override
	public List<Contact> getContactsByNom(String nom) {
		// TODO Auto-generated method stub
		return contactDao.findBynom(nom);
	}

	@Override
	public List<Contact> getContactsBypersonnalNumber(String personnalNumber) {
		// TODO Auto-generated method stub
		return contactDao.findBypersonnalNumber(personnalNumber);
	}

	@Override
	public List<Contact> getContactsByprofessionalNumber(String professionalNumber) {
		// TODO Auto-generated method stub
		return contactDao.findByprofessionalNumber(professionalNumber);
	}
	
	@Override
    public List<Contact> getAllContactsSortedByName() {
        return contactDao.findAll(Sort.by(Sort.Direction.ASC, "nom"));
    }


}
