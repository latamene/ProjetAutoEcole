package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Gerant;

public class EcoleDaoImpl implements EcoleDao {
	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterEcole(Ecole ecole, Long idAdmin, Long idGerant) {

		Administrateur admin = en.find(Administrateur.class, idAdmin);
		Gerant gerant = en.find(Gerant.class, idGerant);

		ecole.setAdministrateur(admin);
		ecole.setGerant(gerant);
		en.persist(ecole);

	}

	@Override
	public void deleteEcole(Long idEcole) {

		Ecole ecole = finEcoleById(idEcole);
		en.remove(ecole);
	}

	@Override
	public void modifierEcole(Ecole ecole) {
		en.merge(ecole);
	}

	@Override
	public Ecole finEcoleById(Long id) {
		return en.find(Ecole.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ecole> findAll() {
		Query req = en.createQuery("select e from Ecole e");

		return req.getResultList();
	}

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

	@Override
	public Ecole findByName(String name) {
		Query req = en
				.createQuery("select e from Ecole e where  e.designation like:x");
		req.setParameter("x", "%" + name + "%");

		if (req.getResultList().size() != 0) {
			return (Ecole) req.getResultList().get(0);
		} else
			return null;

	}

	@Override
	public Ecole finByIdGerant(long idGerant) {
		Query req = en
				.createQuery("select e from Ecole e where  e.gerant.idgerant ="+idGerant);
	 

		if (req.getResultList().size() != 0) {
			return (Ecole) req.getResultList().get(0);
		} else
			return null;
	}

	@Override
	public long nbEcole() {
		Query req = en.createQuery("select count(*) from Ecole");
	 

		if (req.getResultList().size() != 0) {
			return (long) req.getResultList().get(0);
		} else
			return 0;
	}

}
