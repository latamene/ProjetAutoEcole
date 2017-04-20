package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Gerant;
import com.projet.air2.entities.Role;

public class GerantDaoImpl implements GerantDao {
	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterGerant(Gerant gerant, Long idAdmin, Long idRole) {

		Administrateur admin = en.find(Administrateur.class, idAdmin);
		Role role = en.find(Role.class, idRole);

		gerant.setAdministrateur(admin);
		gerant.setRole(role);
		en.persist(gerant);

	}

	@Override
	public void deleteGerant(Long idGerant) {
		Gerant gerant = finGerantById(idGerant);
		en.remove(gerant);
	}

	@Override
	public void modifierGerant(Gerant gerant) {
		en.merge(gerant);
	}

	@Override
	public Gerant finGerantById(Long id) {
		return en.find(Gerant.class, id);
	}

	@Override
	public Gerant findGerantByUsername(String username) {
		Query req = en
				.createQuery("select g from Gerant g where  g.username like:x");
		req.setParameter("x", "%" + username + "%");
		 
		
		if(req.getResultList().size()!=0){
			
		 
			return (Gerant) req.getResultList().get(0);
		}else {
			
		 
			return null;
		}
	 
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Gerant> findGerantNoAtribuerEcole() {
		
		Query req = en.createQuery("select g from Gerant g where g.ecole.idecole IS NULL");

		return req.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gerant> findAll() {
		Query req = en.createQuery("select g from Gerant g");

		return req.getResultList();
	}
	
	

	@Override
	public void attribuerRole(Role r, Long userID) {
		Gerant gerant = en.find(Gerant.class, userID);
		gerant.setRole(r);
		en.persist(gerant);

	}

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

	@Override
	public long nbGerant() {
		Query req = en
				.createQuery("select count(*)from Gerant");
		if(req.getResultList().size()!=0){
			return (long) req.getResultList().get(0);
		}else {
			return 0;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gerant> findGerantByIdEcole(long idEcole) {
		 
		Query req = en.createQuery("select g from Gerant g where g.ecole.idecole="+idEcole);

		return req.getResultList();
		
		
		
	}

	

}
