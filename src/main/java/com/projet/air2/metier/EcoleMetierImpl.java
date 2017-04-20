package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.dao.EcoleDao;
import com.projet.air2.entities.Ecole;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EcoleMetierImpl implements EcoleMetier {

	private EcoleDao ecoleDao;

	@Override
	public void ajouterEcole(Ecole ecole, Long idAdmin, Long idGerant) {

		ecoleDao.ajouterEcole(ecole, idAdmin, idGerant);
	}

	@Override
	public void deleteEcole(Long idEcole) {
		ecoleDao.deleteEcole(idEcole);
	}

	@Override
	public void modifierEcole(Ecole ecole) {
		ecoleDao.modifierEcole(ecole);
	}

	@Override
	public Ecole finEcoleById(Long id) {
		return ecoleDao.finEcoleById(id);
	}

	@Override
	public List<Ecole> findAll() {
		return ecoleDao.findAll();
	}

	public EcoleDao getEcoleDao() {
		return ecoleDao;
	}

	public void setEcoleDao(EcoleDao ecoleDao) {
		this.ecoleDao = ecoleDao;
	}

	@Override
	public Ecole findByName(String name) {

		return this.ecoleDao.findByName(name);
	}

	@Override
	public Ecole finByIdGerant(long idGerant) {
		 
		return this.ecoleDao.finByIdGerant(idGerant);
	}

	@Override
	public long nbEcole() {
		// TODO Auto-generated method stub
		return ecoleDao.nbEcole();
	}

}
