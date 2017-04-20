package com.projet.air2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.projet.air2.entities.Role;

public class RoleDaoImpl implements RoleDao {
	@PersistenceContext
	private EntityManager en;

	@Override
	public void ajouterRole(Role role) {

		en.persist(role);
	}

	@Override
	public void deleteRole(Long idRole){
		Role role = finRoleById(idRole);
		en.remove(role);

	}

	@Override
	public void modifierRole(Role role) {

		en.merge(role);
	}

	@Override
	public Role finRoleById(Long id) {

		return en.find(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {

		Query req = en.createQuery("select r from Role r");

		return req.getResultList();

	}

	public EntityManager getEn() {
		return en;
	}

	public void setEn(EntityManager en) {
		this.en = en;
	}

}
