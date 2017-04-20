package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.dao.RoleDao;
import com.projet.air2.entities.Role;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public class RoleMetierImpl implements RoleMetier {

	private RoleDao roleDao;
	
	@Override
	public void ajouterRole(Role role) {
 
		roleDao.ajouterRole(role);
	}

	@Override
	public void deleteRole(Long idRole) {
 
		roleDao.deleteRole(idRole);
	}

	@Override
	public void modifierRole(Role role) {
 
		roleDao.modifierRole(role);
	}

	@Override
	public Role finRoleById(Long id) {
 		return roleDao.finRoleById(id);
	}

	@Override
	public List<Role> findAll() {
 		return roleDao.findAll();
	}

	
	
	
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
