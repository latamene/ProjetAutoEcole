package com.projet.air2.dao;

import java.util.List;

import com.projet.air2.entities.Moniteur;
import com.projet.air2.entities.Role;

public interface MoniteurDao {
	public void ajouterMoniteur(Moniteur moniteur, Long IdGerant, Long idEcole,
			Long idRole);

	public void deleteMoniteur(Long idMoniteur);

	public void modifierMoniteur(Moniteur moniteur);

	public Moniteur finMoniteurById(Long id);

	public Moniteur findMoniteurByUsername(String username);

	public List<Moniteur> findAll();

	public void attribuerRole(Role r, Long userID);

	public long nbMoniteur();

	public long nbMoniteurByGerant(long idGerant);

	public List<Moniteur> findAllByGerant(long idGerant);

	public List<Moniteur> findAllByEcole(long idEcole);

}
