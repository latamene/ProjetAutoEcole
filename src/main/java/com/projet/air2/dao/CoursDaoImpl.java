package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 


import com.projet.air2.entities.Cours;
import com.projet.air2.entities.Ecole;

public class CoursDaoImpl implements CoursDao {

	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterCours(Cours cours, long idEcole) {
		Ecole ecole = en.find(Ecole.class, idEcole);
		cours.setEcole(ecole);
		en.persist(cours);
	}

	@Override
	public void deleteCours(Long idCours) {

		Cours cours = finCoursById(idCours);
		en.remove(cours);
	}

	@Override
	public void modifierCours(Cours cours) {

		en.merge(cours);
	}

	@Override
	public Cours finCoursById(Long id) {
		return en.find(Cours.class, id);
	}

	 
	@Override
	public Cours finCoursByNom(String nomCours) {

		Query req = en
				.createQuery("select c from Cours c where  c.nomCours like:x");
		req.setParameter("x", "%" + nomCours + "%");
		if (req.getResultList().size() != 0) {
			return (Cours) req.getResultList().get(0);
		} else
			return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cours> findAll() {

		Query req = en.createQuery("select c from Cours c");

		return req.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Cours> findAllByEcole(long idEcole) {
	 
		Query req = en.createQuery("select c from Cours c where  c.ecole.idecole="+idEcole);

		return req.getResultList(); 
	}

	
	
	

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}
	
	
}
