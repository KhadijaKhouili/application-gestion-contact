package com.ensah.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ensah.core.hk.Groupe;

@Service
public interface IGroupeService {
	
	public void addGroupe(Groupe groupe);

	public void updateGroupe(Groupe groupe);

	public List<Groupe> getAllGroupes();

	public void deleteGroupe(Long id);

	public Groupe getGroupeById(Long id);
	
	public Groupe getGroupeByNom(String nom);
	


}
