package com.projet.air2.metier;

import java.util.List;

 

import org.springframework.transaction.annotation.Transactional;

 


import com.projet.air2.dao.CoursDao;
import com.projet.air2.entities.Cours;
@Transactional
public class CoursMetierImpl implements CoursMetier {
	
	
	
	private CoursDao coursDao;
	
	@Override
	public void ajouterCours(Cours cours, long idGerant) {
		coursDao.ajouterCours(cours,idGerant);

	}

	@Override
	public void deleteCours(Long idCours) {
		coursDao.deleteCours(idCours);
	}

	@Override
	public void modifierCours(Cours cours) {
		
		coursDao.modifierCours(cours);
	}

	@Override
	public Cours finCoursById(Long id) {
 		return coursDao.finCoursById(id);
	}

	@Override
	public  Cours finCoursByNom(String nomCours){
 		return coursDao.finCoursByNom(nomCours);
	}

	@Override
	public List<Cours> findAll() {
 		return coursDao.findAll();
	}
	 

	public CoursDao getCoursDao() {
		return coursDao;
	}

	public void setCoursDao(CoursDao coursDao) {
		this.coursDao = coursDao;
	}

	@Override
	public List<Cours> findAllByEcole(long idEcole) {
		 
		return coursDao.findAllByEcole(idEcole);
	}

	

}
