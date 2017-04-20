package com.projet.air2.metier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.projet.air2.dao.AdministrateurDao;
import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Role;

@Transactional
public class AdministrateurMetierImpl implements AdministrateurMetier {

	private AdministrateurDao administrateurDao;

	@Override
	public void ajouterAdmin(Administrateur admin, Long idrole) {
		
		administrateurDao.ajouterAdmin(admin, idrole);
	
	
	}

	@Override
	public void deleteAdmin(Long IdAdmin) {
		administrateurDao.deleteAdmin(IdAdmin);
	}

	@Override
	public void modifierAdmin(Administrateur admin) {

		administrateurDao.modifierAdmin(admin);
	}

	@Override
	public Administrateur finAdminById(Long id) {
		return administrateurDao.finAdminById(id);
	}

	@Override
	public Administrateur findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return administrateurDao.findAdminByUsername(username);
	}

	@Override
	public List<Administrateur> findAll() {
		return administrateurDao.findAll();
	}

	@Override
	public void attribuerRole(Role r, Long userID) {
		administrateurDao.attribuerRole(r, userID);
	}

	public AdministrateurDao getAdministrateurDao() {
		return administrateurDao;
	}

	public void setAdministrateurDao(AdministrateurDao administrateurDao) {
		this.administrateurDao = administrateurDao;
	}

	@Override
	public long nbAdministrateur() {
		 
		return administrateurDao.nbAdministrateur();
	}

}
