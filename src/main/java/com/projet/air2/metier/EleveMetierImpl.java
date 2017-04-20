package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.dao.EleveDao;
import com.projet.air2.entities.Eleve;
import com.projet.air2.entities.Role;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EleveMetierImpl implements EleveMetier {

	private EleveDao eleveDao;

	@Override
	public void ajouterEleve(Eleve eleve, Long idEcole, Long idGerant,
			Long idRole, Long idPermis) {

		eleveDao.ajouterEleve(eleve, idEcole, idGerant, idRole, idPermis);
	}

	@Override
	public void deleteEleve(Long idEleve) {

		eleveDao.deleteEleve(idEleve);

	}

	@Override
	public void modifierEleve(Eleve eleve) {

		eleveDao.modifierEleve(eleve);
	}

	@Override
	public Eleve finEleveById(Long id) {
		return eleveDao.finEleveById(id);
	}

	@Override
	public Eleve findEleveByUsername(String username) {
		// TODO Auto-generated method stub
		return eleveDao.findEleveByUsermane(username);
	}

	@Override
	public List<Eleve> findAll() {
		return eleveDao.findAll();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
		eleveDao.attribuerRole(r, userID);
	}

	public EleveDao getEleveDao() {
		return eleveDao;
	}

	public void setEleveDao(EleveDao eleveDao) {
		this.eleveDao = eleveDao;
	}

	@Override
	public long nbEleves() {

		return eleveDao.nbEleves();
	}

	@Override
	public long nbElevesByGerant(long idGerant) {
		return eleveDao.nbElevesByGerant(idGerant);
	}

	@Override
	public List<Eleve> findAllByGerant(long idGerant) {

		return eleveDao.findAllByGerant(idGerant);
	}

	@Override
	public List<Eleve> findAllByEcole(long idEcole) {
		return eleveDao.findAllByEcole(idEcole);
	}

}