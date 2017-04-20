package com.projet.air2.dao;

 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Role;

public class AdministrateurDaoImpl implements AdministrateurDao {

	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterAdmin(Administrateur admin, Long idRole) {
		Role role=en.find(Role.class,idRole);
		
		admin.setRole(role);
		en.persist(admin);
	}

	@Override
	public void deleteAdmin(Long idAdmin) {
		
		Administrateur admin = finAdminById(idAdmin) ;
 		en.remove(admin);
 		
	}

	@Override
	public void modifierAdmin(Administrateur admin) {
		// TODO Auto-generated method stub
		en.merge(admin);
	}

	@Override
	public Administrateur finAdminById(Long id) {

		return en.find(Administrateur.class, id);
	}
	
	@Override
	public Administrateur findAdminByUsername(String username) {
		 
		
		
		Query req=en.createQuery("select a from Administrateur a where  a.username like:x");
		req.setParameter("x", "%"+username+"%");
		
		
		if(req.getResultList().size()!=0){
			return (Administrateur) req.getResultList().get(0);
		}else 
			return null;
		
		 
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Administrateur> findAll() {

		Query req = en.createQuery("select a from Administrateur a");

		return req.getResultList();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
 		Administrateur admin = en.find(Administrateur.class, userID);
		admin.setRole(r);
		en.persist(admin);
	}

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

	@Override
	public long nbAdministrateur() {
		 
		Query req=en.createQuery("select count(*) from Administrateur"); 
		if(req.getResultList().size()!=0){
			return   (long) req.getResultList().get(0);
		}else 
			return 0;
	}

	
}
