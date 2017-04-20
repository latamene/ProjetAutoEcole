package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.dao.PermisDao;
import com.projet.air2.entities.Permis;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public class PermisMetierImpl implements PermisMetier {

	private PermisDao permisDao;
	
	@Override
	public void ajouterPermis(Permis prermis, long idGerant) {
 
		permisDao.ajouterPermis(prermis,idGerant);
	}

	@Override
	public void deletePermis(Long idPrermis) {
 
		permisDao.deletePermis(idPrermis);
	}

	@Override
	public void modifierPermis(Permis prermis) {
 
		permisDao.modifierPermis(prermis);
	}

	@Override
	public Permis finPermisById(Long id) {
 		return permisDao.finPermisById(id);
	}

	@Override
	public Permis finPermisByNom(String nom) {
 		return permisDao.finPermisByNom(nom);
	}

	@Override
	public List<Permis> findAll() {
 		return permisDao.findAll();
	}

	
	
	
	public PermisDao getPermisDao() {
		return permisDao;
	}

	public void setPermisDao(PermisDao permisDao) {
		this.permisDao = permisDao;
	}

	@Override
	public List<Permis> findAllByEcole(long idEcole) {
		// TODO Auto-generated method stub
		return permisDao.findAllByEcole(idEcole);
	}

}
