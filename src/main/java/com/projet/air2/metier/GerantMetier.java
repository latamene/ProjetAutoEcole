package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.entities.Gerant;
import com.projet.air2.entities.Role;

public interface GerantMetier {
	public void ajouterGerant(Gerant gerant, Long idAdmin, Long idRole);

	public void deleteGerant(Long idGerant);

	public void modifierGerant(Gerant gerant);

	public Gerant finGerantById(Long id);

	public Gerant findGerantByUsername(String username);

	public List<Gerant> findGerantNoAtribuerEcole();

	public List<Gerant> findAll();

	public void attribuerRole(Role r, Long userID);

	public long nbGerant();

	public List<Gerant> findGerantByIdEcole(long idEcole);
}
