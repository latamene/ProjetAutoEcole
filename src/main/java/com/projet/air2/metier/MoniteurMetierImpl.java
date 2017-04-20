package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.dao.MoniteurDao;
import com.projet.air2.entities.Moniteur;
import com.projet.air2.entities.Role;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MoniteurMetierImpl implements MoniteurMetier {

	private MoniteurDao moniteurDao;

	@Override
	public void ajouterMoniteur(Moniteur moniteur, Long idGerant, Long idEcole,
			Long idRole) {

		moniteurDao.ajouterMoniteur(moniteur, idGerant, idEcole, idRole);
	}

	@Override
	public void deleteMoniteur(Long idMoniteur) {

		moniteurDao.deleteMoniteur(idMoniteur);
	}

	@Override
	public void modifierMoniteur(Moniteur moniteur) {

		moniteurDao.modifierMoniteur(moniteur);
	}

	@Override
	public Moniteur finMoniteurById(Long id) {
		return moniteurDao.finMoniteurById(id);
	}

	@Override
	public Moniteur findMoniteurByUsername(String username) {
		// TODO Auto-generated method stub
		return moniteurDao.findMoniteurByUsername(username);
	}

	@Override
	public List<Moniteur> findAll() {
		return moniteurDao.findAll();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {

		moniteurDao.attribuerRole(r, userID);
	}

	public MoniteurDao getMoniteurDao() {
		return moniteurDao;
	}

	public void setMoniteurDao(MoniteurDao moniteurDao) {
		this.moniteurDao = moniteurDao;
	}

	@Override
	public long nbMoniteur() {
	 
		return moniteurDao.nbMoniteur();
	}

	@Override
	public long nbMoniteurByGerant(long idGerant) {
	 
		return moniteurDao.nbMoniteurByGerant(idGerant);
	}

	@Override
	public List<Moniteur> findAllByGerant(long idGerant) {
	 
		return moniteurDao.findAllByGerant(idGerant);
	}

	@Override
	public List<Moniteur> findAllByEcole(long idEcole) {
		 
		return moniteurDao.findAllByEcole(idEcole);
	}

}
