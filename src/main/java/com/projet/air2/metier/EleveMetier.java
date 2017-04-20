package com.projet.air2.metier;

import java.util.List;

import com.projet.air2.entities.Eleve;
import com.projet.air2.entities.Role;

public interface EleveMetier {
	public void ajouterEleve(Eleve eleve, Long idEcole, Long idGerant,
			Long idRole, Long idPermis);

	public void deleteEleve(Long idEleve);

	public void modifierEleve(Eleve eleve);

	public Eleve finEleveById(Long id);

	public Eleve findEleveByUsername(String username);

	public List<Eleve> findAll();

	public void attribuerRole(Role r, Long userID);

	public long nbEleves();

	public long nbElevesByGerant(long idGerant);

	public List<Eleve> findAllByGerant(long idGerant);

	public List<Eleve> findAllByEcole(long idEcole);
}
