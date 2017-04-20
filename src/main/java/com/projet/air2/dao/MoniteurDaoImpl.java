package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Gerant;
import com.projet.air2.entities.Moniteur;
import com.projet.air2.entities.Role;

public class MoniteurDaoImpl implements MoniteurDao {
	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterMoniteur(Moniteur moniteur, Long idGerant, Long idEcole,
			Long idRole) {

		Ecole ecole = en.find(Ecole.class, idEcole);
		Gerant gerant = en.find(Gerant.class, idGerant);
		Role role = en.find(Role.class, idRole);

		moniteur.setEcole(ecole);
		moniteur.setGerant(gerant);
		moniteur.setRole(role);
		en.persist(moniteur);
	}

	@Override
	public void deleteMoniteur(Long idMoniteur) {

		Moniteur moniteur = finMoniteurById(idMoniteur);
		en.remove(moniteur);

	}

	@Override
	public void modifierMoniteur(Moniteur moniteur) {
		en.merge(moniteur);
	}

	@Override
	public Moniteur finMoniteurById(Long id) {
		return en.find(Moniteur.class, id);
	}

	@Override
	public Moniteur findMoniteurByUsername(String username) {
		Query req = en
				.createQuery("select m from Moniteur m where  m.username like:x");
		req.setParameter("x", "%" + username + "%");

		if (req.getResultList().size() != 0) {
			return (Moniteur) req.getResultList().get(0);
		} else
			return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moniteur> findAll() {
		Query req = en.createQuery("select m from Moniteur m");

		return req.getResultList();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
		Moniteur moniteur = en.find(Moniteur.class, userID);
		moniteur.setRole(r);
		en.persist(moniteur);

	}

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

	@Override
	public long nbMoniteur() {
		Query req = en.createQuery("select count(*)from Moniteur");

		if (req.getResultList().size() != 0) {
			return (long) req.getResultList().get(0);
		} else
			return 0;
	}

	@Override
	public long nbMoniteurByGerant(long idGerant) {
		Query req = en.createQuery("select count(*)from Moniteur m where  m.gerant.idgerant="+idGerant);

		if (req.getResultList().size() != 0) {
			return (long) req.getResultList().get(0);
		} else
			return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moniteur> findAllByGerant(long idGerant) {
		Query req = en.createQuery("select m from Moniteur  m where  m.gerant.idgerant="+idGerant);

		return req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moniteur> findAllByEcole(long idEcole) {
		Query req = en.createQuery("select m from Moniteur  m where  m.ecole.idecole="+idEcole);

		return req.getResultList();
	}

}
