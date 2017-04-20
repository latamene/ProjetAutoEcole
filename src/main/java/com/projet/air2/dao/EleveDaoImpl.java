package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Eleve;
import com.projet.air2.entities.Gerant;
import com.projet.air2.entities.Permis;
import com.projet.air2.entities.Role;

public class EleveDaoImpl implements EleveDao {
	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterEleve(Eleve eleve, Long idEcole, Long idGerant,
			Long idRole, Long idPermis) {

		Ecole ecole = en.find(Ecole.class, idEcole);
		Gerant gerant = en.find(Gerant.class, idGerant);
		Role role = en.find(Role.class, idRole);
		Permis permis = en.find(Permis.class, idPermis);

		eleve.setEcole(ecole);
		eleve.setGerant(gerant);
		eleve.setRole(role);
		eleve.setPermis(permis);
		en.persist(eleve);

	}

	@Override
	public void deleteEleve(Long idEleve) {
		Eleve eleve = finEleveById(idEleve);

		en.remove(eleve);

	}

	@Override
	public void modifierEleve(Eleve eleve) {
		en.merge(eleve);

	}

	@Override
	public Eleve finEleveById(Long id) {

		return en.find(Eleve.class, id);
	}

	@Override
	public Eleve findEleveByUsermane(String username) {
		Query req = en
				.createQuery("select e from Eleve e where  e.username like:x");
		req.setParameter("x", "%" + username + "%");
		
		if(req.getResultList().size()!=0){
			return (Eleve) req.getResultList().get(0);
		}
		else return null;
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> findAll() {

		Query req = en.createQuery("select e from Eleve e");

		return req.getResultList();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
		Eleve eleve = en.find(Eleve.class, userID);
		eleve.setRole(r);
		en.persist(eleve);

	}

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

	@Override
	public long nbEleves() {
		Query req = en
				.createQuery("select count(*) from Eleve");
		if(req.getResultList().size()!=0){
			return (long) req.getResultList().get(0);
		}
		else return 0;
		
	}

	@Override
	public long nbElevesByGerant(long idGerant) {
		Query req = en.createQuery("select count(*)from Eleve e where  e.gerant.idgerant="+idGerant);

		if (req.getResultList().size() != 0) {
			return (long) req.getResultList().get(0);
		} else
			return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> findAllByGerant(long idGerant) {
		Query req = en.createQuery("select e from Eleve e where  e.gerant.idgerant="+idGerant);

		return req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eleve> findAllByEcole(long idEcole) {
		Query req = en.createQuery("select e from Eleve e where  e.ecole.idecole="+idEcole);

		return req.getResultList();
	}

}
