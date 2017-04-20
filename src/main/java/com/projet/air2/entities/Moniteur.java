package com.projet.air2.entities;

// Generated 1 f�vr. 2017 02:18:12 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Moniteur generated by hbm2java
 */
@Entity
@Table(name = "moniteur", catalog = "projetair")
public class Moniteur implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idmoniteur;
	private Role role;
	private Ecole ecole;
	private Gerant gerant;
	private String username;
	private String password;
	private Boolean active;
	private String nom;
	private String prenom;
	private String datenaissance;
	private String telephone;
	private String adresse;
	private String nomphoto;
	private byte[] photo;
	private Set<Enseigner> enseigners = new HashSet<Enseigner>(0);

	public Moniteur() {
	}

	public Moniteur(Role role, Ecole ecole, Gerant gerant) {
		this.role = role;
		this.ecole = ecole;
		this.gerant = gerant;
	}

	public Moniteur(Role role, Ecole ecole, Gerant gerant, String username,
			String password, Boolean active, String nom, String prenom,
			String datenaissance, String telephone, String adresse,
			String nomphoto, byte[] photo, Set<Enseigner> enseigners) {
		this.role = role;
		this.ecole = ecole;
		this.gerant = gerant;
		this.username = username;
		this.password = password;
		this.active = active;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
		this.telephone = telephone;
		this.adresse = adresse;
		this.nomphoto = nomphoto;
		this.photo = photo;
		this.enseigners = enseigners;
	}

	public Moniteur(String username, String password, Boolean active,
			String nom, String prenom, String datenaissance, String telephone,
			String adresse, String nomphoto, byte[] photo) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
		this.telephone = telephone;
		this.adresse = adresse;
		this.nomphoto = nomphoto;
		this.photo = photo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDMONITEUR", unique = true, nullable = false)
	public Long getIdmoniteur() {
		return this.idmoniteur;
	}

	public void setIdmoniteur(Long idmoniteur) {
		this.idmoniteur = idmoniteur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDROLE", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDECOLE", nullable = false)
	public Ecole getEcole() {
		return this.ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDGERANT", nullable = false)
	public Gerant getGerant() {
		return this.gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "NOM")
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "PRENOM")
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "DATENAISSANCE")
	public String getDatenaissance() {
		return this.datenaissance;
	}

	public void setDatenaissance(String datenaissance) {
		this.datenaissance = datenaissance;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "ADRESSE")
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "NOMPHOTO")
	public String getNomphoto() {
		return this.nomphoto;
	}

	public void setNomphoto(String nomphoto) {
		this.nomphoto = nomphoto;
	}

	@Column(name = "PHOTO")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "moniteur")
	public Set<Enseigner> getEnseigners() {
		return this.enseigners;
	}

	public void setEnseigners(Set<Enseigner> enseigners) {
		this.enseigners = enseigners;
	}

	@Override
	public String toString() {
		return "Moniteur [idmoniteur=" + idmoniteur + ", username=" + username
				+ ", password=" + password + ", active=" + active + ", nom="
				+ nom + ", prenom=" + prenom + ", datenaissance="
				+ datenaissance + ", telephone=" + telephone + ", adresse="
				+ adresse + ", nomphoto=" + nomphoto + "]";
	}

}
