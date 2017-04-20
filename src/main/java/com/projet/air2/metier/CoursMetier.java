package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.entities.Cours;
 

public interface CoursMetier {
	public void ajouterCours(Cours cours, long idGerant);

	public void deleteCours(Long idCours);

	public void modifierCours(Cours cours);

	public Cours finCoursById(Long id);

	public Cours finCoursByNom(String nomCours);

	public List<Cours> findAll();

	public List<Cours> findAllByEcole(long idEcole);
}
