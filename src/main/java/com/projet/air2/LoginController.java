package com.projet.air2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.projet.air2.entities.Administrateur;
import com.projet.air2.entities.Eleve;
import com.projet.air2.entities.Gerant;
import com.projet.air2.entities.Moniteur;
import com.projet.air2.metier.AdministrateurMetier;
import com.projet.air2.metier.CoursMetier;
import com.projet.air2.metier.EcoleMetier;
import com.projet.air2.metier.EleveMetier;
import com.projet.air2.metier.GerantMetier;
import com.projet.air2.metier.MoniteurMetier;

@Controller
public class LoginController {

	@Autowired
	private AdministrateurMetier administrateurMetier;

	@Autowired
	private GerantMetier gerantMetier;

	@Autowired
	private MoniteurMetier moniteurMetier;

	@Autowired
	private EleveMetier eleveMetier;

	@Autowired
	private EcoleMetier ecoleMetier;
	
	@Autowired 
	private CoursMetier coursMetier;
	
	
	private static String typeUser;
	private static Administrateur admin;
	private static Gerant gerant;
	private static Moniteur moniteur;
	private static Eleve eleve;
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/login")
	public String login() {

		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {

		return "login";
	}

	@RequestMapping(value = "/index")
	public String indexHome(Model model, Principal principal) {

		// model.addAttribute("ListeAdministrateurs",administrateurMetier.findAll());

		admin = administrateurMetier.findAdminByUsername(principal.getName());

		gerant = gerantMetier.findGerantByUsername(principal.getName());

		moniteur = moniteurMetier.findMoniteurByUsername(principal.getName());

		eleve = eleveMetier.findEleveByUsername(principal.getName());

		if (admin != null) {
			model.addAttribute("nbAdministrateur", administrateurMetier.nbAdministrateur());
			model.addAttribute("nbMoniteur", moniteurMetier.nbMoniteur());
			model.addAttribute("nbEcole", ecoleMetier.nbEcole());
			model.addAttribute("nbGerant",gerantMetier.nbGerant());
			model.addAttribute("nbEleve",eleveMetier.nbEleves());
			
			model.addAttribute("utilisateur", admin);
			model.addAttribute("typeUser", "administrateur");
			setTypeUser("administrateur");

			return "home";

		} else if (gerant != null) {
			
		 
			model.addAttribute("nbMoniteur", moniteurMetier.nbMoniteurByGerant(gerant.getIdgerant()));
			model.addAttribute("nbEleve",eleveMetier.nbElevesByGerant(gerant.getIdgerant()));
			
			
			
			model.addAttribute("utilisateur", gerant);
			model.addAttribute("typeUser", "gerant");
			setTypeUser("gerant");

			return "home";

		} else if (moniteur != null) {
			
			model.addAttribute("listeCours", coursMetier.findAllByEcole(moniteur.getEcole().getIdecole()));
			
			model.addAttribute("utilisateur", moniteur);
			model.addAttribute("typeUser", "moniteur");
			setTypeUser("moniteur");
			return "planningEleve";

		} else if (eleve != null) {
			
			
			model.addAttribute("listeCours", coursMetier.findAllByEcole(eleve.getEcole().getIdecole()));
			model.addAttribute("utilisateur", eleve);
			model.addAttribute("typeUser", "eleve");
			setTypeUser("eleve");
			return "planningEleve";
		}

		return "home";
	}

	@RequestMapping(value = "photoUser", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoUser(Long iduser) throws IOException {

		switch (getTypeUser()) {
		case "administrateur":
			Administrateur admin = administrateurMetier.finAdminById(iduser);

			if (admin.getPhoto() == null)
				return new byte[0];
			else
				return IOUtils.toByteArray(new ByteArrayInputStream(admin
						.getPhoto()));

		case "gerant":
			Gerant gerant = gerantMetier.finGerantById(iduser);

			if (gerant.getPhoto() == null)
				return new byte[0];
			else
				return IOUtils.toByteArray(new ByteArrayInputStream(gerant
						.getPhoto()));

		case "moniteur":
			Moniteur moniteur = moniteurMetier.finMoniteurById(iduser);

			if (moniteur.getPhoto() == null)
				return new byte[0];
			else
				return IOUtils.toByteArray(new ByteArrayInputStream(moniteur
						.getPhoto()));

		case "eleve":
			Eleve eleve = eleveMetier.finEleveById(iduser);

			if (eleve.getPhoto() == null)
				return new byte[0];
			else
				return IOUtils.toByteArray(new ByteArrayInputStream(eleve
						.getPhoto()));

		default:
			break;
		}
		return null;

	}

	public static String getTypeUser() {
		return typeUser;
	}

	public static void setTypeUser(String typeUser1) {
		LoginController.typeUser = typeUser1;
	}

	public static Administrateur getAdmin() {
		return admin;
	}

	public static void setAdmin(Administrateur admin) {
		LoginController.admin = admin;
	}

	public static Gerant getGerant() {
		return gerant;
	}

	public static void setGerant(Gerant gerant) {
		LoginController.gerant = gerant;
	}

	public static Moniteur getMoniteur() {
		return moniteur;
	}

	public static void setMoniteur(Moniteur moniteur) {
		LoginController.moniteur = moniteur;
	}

	public static Eleve getEleve() {
		return eleve;
	}

	public static void setEleve(Eleve eleve) {
		LoginController.eleve = eleve;
	}

}
