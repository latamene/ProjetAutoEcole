package com.projet.air2.dao;

import java.util.List;

 import com.projet.air2.entities.Ecole;

public interface EcoleDao {
	public void ajouterEcole(Ecole ecole, Long idAdmin, Long idGerant);

	public void deleteEcole(Long idEcole);

	public void modifierEcole(Ecole ecole);

	public Ecole finEcoleById(Long id);


	public List<Ecole> findAll();

	public  Ecole  findByName(String name);
	
	public Ecole finByIdGerant(long idGerant);
	
	public long nbEcole();
}
