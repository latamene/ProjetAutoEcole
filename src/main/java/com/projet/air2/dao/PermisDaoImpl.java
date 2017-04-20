package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Permis;
 

public class PermisDaoImpl implements PermisDao {
	@PersistenceContext
	private EntityManager en;
	
	
	
	@Override
	public void ajouterPermis(Permis prermis, long idEcole) {
 
		Ecole ecole= en.find(Ecole.class, idEcole);
		prermis.setEcole(ecole);;
		
		en.persist(prermis);
	}

	@Override
	public void deletePermis(Long idPrermis) {
		Permis prermis = finPermisById(idPrermis);
		en.remove(prermis);
	}

	@Override
	public void modifierPermis(Permis prermis) {
 
		en.merge(prermis);
		
	}

	@Override
	public Permis finPermisById(Long id) {
		
		
 		return en.find(Permis.class, id);
	}

	@Override
	public Permis finPermisByNom(String nom) {
		
		Query req=en.createQuery("select p from permis p where  p.NOM_PERMIS like:x");
		req.setParameter("x", "%"+nom+"%");
		return (Permis) req.getResultList().get(0);

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permis> findAll() {
		Query req = en.createQuery("select p from Permis p");

		return req.getResultList();
	}

	
	
	
	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permis> findAllByEcole(long idEcole) {
		Query req = en.createQuery("select p from Permis p where  p.ecole.idecole="+idEcole);

		return req.getResultList(); 
		
		
	}

	
	
	
	
}
