package com.projet.air2.dao;

import java.util.List;

import com.projet.air2.entities.Role;

public interface RoleDao {
	public void ajouterRole(Role role);

	public void deleteRole(Long idRole);

	public void modifierRole(Role role);

	public Role finRoleById(Long id);

	public List<Role> findAll();

}
