package com.ensah.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.core.dao.GroupeDao;
import com.ensah.core.hk.Groupe;

@Service
@Transactional
public class GroupeServiceImpl implements IGroupeService{
	
	private GroupeDao groupeDao;

	@Autowired
	public GroupeServiceImpl(GroupeDao gDao) {

		groupeDao = gDao;

	}

	@Override
	public void addGroupe(Groupe groupe) {
		// TODO Auto-generated method stub
		groupeDao.save(groupe);
	}

	@Override
	public void updateGroupe(Groupe groupe) {
		// TODO Auto-generated method stub
		groupeDao.save(groupe);
		
	}

	@Override
	public List<Groupe> getAllGroupes() {
		// TODO Auto-generated method stub
		return groupeDao.findAll();
	}

	@Override
	public void deleteGroupe(Long id) {
		// TODO Auto-generated method stub
		groupeDao.deleteById(id);
	}

	@Override
	public Groupe getGroupeById(Long id) {
		// TODO Auto-generated method stub
		return groupeDao.findById(id).get();
	}

	@Override
	public Groupe getGroupeByNom(String nom) {
		// TODO Auto-generated method stub
		return groupeDao.findBynomGroupe(nom);
	}

}
