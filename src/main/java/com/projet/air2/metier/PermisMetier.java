package com.projet.air2.metier;

import java.util.List;

 
import com.projet.air2.entities.Permis;

public interface PermisMetier {
	public void ajouterPermis(Permis prermis, long idGerant);

	public void deletePermis(Long idPrermis);

	public void modifierPermis(Permis prermis);

	public Permis finPermisById(Long id);

	public Permis finPermisByNom(String nom);

	public List<Permis> findAll();

	public List<Permis> findAllByEcole(long idEcole);
}
