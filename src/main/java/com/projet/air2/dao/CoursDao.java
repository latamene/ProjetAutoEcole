package com.projet.air2.dao;

import java.util.List;

import com.projet.air2.entities.Cours;
import com.projet.air2.entities.Eleve;

public interface CoursDao {

	public void ajouterCours(Cours cours, long idEcole);

	public void deleteCours(Long idCours);

	public void modifierCours(Cours cours);

	public Cours finCoursById(Long id);

	public Cours finCoursByNom(String nomCours);

	public List<Cours> findAll();
	
	public List<Cours> findAllByEcole(long idEcole);

}
