package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Role;

public interface AdministrateurMetier {
	public void ajouterAdmin(Administrateur admin, Long idrole);

	public void deleteAdmin(Long IdAdmin);

	public void modifierAdmin(Administrateur admin);

	public Administrateur finAdminById(Long id);

	public Administrateur findAdminByUsername(String username);

	public List<Administrateur> findAll();

	public void attribuerRole(Role r, Long userID);

	public long nbAdministrateur();
}
