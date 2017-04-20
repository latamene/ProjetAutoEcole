package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.entities.Role;

public interface RoleMetier {
	public void ajouterRole(Role role);

	public void deleteRole(Long idRole);

	public void modifierRole(Role role);

	public Role finRoleById(Long id);

	public List<Role> findAll();
}
