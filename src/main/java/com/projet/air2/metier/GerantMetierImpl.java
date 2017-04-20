package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.dao.GerantDao;
import com.projet.air2.entities.Gerant;
import com.projet.air2.entities.Role;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GerantMetierImpl implements GerantMetier {

	private GerantDao gerantDao;

	@Override
	public void ajouterGerant(Gerant gerant, Long idAdmin, Long idRole) {

		gerantDao.ajouterGerant(gerant, idAdmin, idRole);

	}

	@Override
	public void deleteGerant(Long idGerant) {
		gerantDao.deleteGerant(idGerant);

	}

	@Override
	public void modifierGerant(Gerant gerant) {

		gerantDao.modifierGerant(gerant);
	}

	@Override
	public Gerant finGerantById(Long id) {
		return gerantDao.finGerantById(id);
	}

	@Override
	public Gerant findGerantByUsername(String username) {
		// TODO Auto-generated method stub
		return gerantDao.findGerantByUsername(username);
	}
	@Override
	public List<Gerant> findGerantNoAtribuerEcole() {
		 
		return  gerantDao.findGerantNoAtribuerEcole();
	}
	@Override
	public List<Gerant> findAll() {
		return gerantDao.findAll();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
		gerantDao.attribuerRole(r, userID);
	}

	public GerantDao getGerantDao() {
		return gerantDao;
	}

	public void setGerantDao(GerantDao gerantDao) {
		this.gerantDao = gerantDao;
	}

	@Override
	public long nbGerant() {
		 
		return gerantDao.nbGerant();
	}

	@Override
	public List<Gerant> findGerantByIdEcole(long idEcole) {
		 
		return gerantDao.findGerantByIdEcole(idEcole);
	}

	

}
