package com.projet.air2.dao;

import java.util.List;

import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Role;

public interface AdministrateurDao {

	public void ajouterAdmin(Administrateur admin, Long idRole);

	public void deleteAdmin(Long idAdmin);

	public void modifierAdmin(Administrateur admin);

	public Administrateur finAdminById(Long id);
	
	public Administrateur findAdminByUsername(String username);

	public List<Administrateur> findAll();

	public void attribuerRole(Role r, Long userID);
	
	public long nbAdministrateur();

}
