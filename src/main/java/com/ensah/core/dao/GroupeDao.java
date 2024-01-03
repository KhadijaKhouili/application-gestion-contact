package com.ensah.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.hk.Groupe;

public interface GroupeDao extends JpaRepository<Groupe, Long>{
	
	Groupe findBynomGroupe(String nom);

}
