package com.projet.air2.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.air2.LoginController;
import com.projet.air2.entities.Cours;
import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Permis;
import com.projet.air2.metier.EcoleMetier;
import com.projet.air2.metier.PermisMetier;

@Controller
@RequestMapping(value = "/adminPermis")
public class AdminPermisController {

	@Autowired
	private PermisMetier permisMetier;
	@Autowired
	private EcoleMetier ecoleMetier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("permis", new Permis());

		model.addAttribute("LesEcoles", ecoleMetier.findAll());

		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("LesPermis", permisMetier.findAll());
		} else {
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute(
					"LesPermis",
					permisMetier.findAllByEcole(LoginController.getGerant()
							.getEcole().getIdecole()));
		}

		return "permis";
	}

	@RequestMapping("/savePermis")
	public String savePermis(Permis p, BindingResult bindingResult, Model model)
			throws Exception {
		if (bindingResult.hasErrors()) {

			model.addAttribute("typeUser", LoginController.getTypeUser());
			if (LoginController.getAdmin() != null) {
				model.addAttribute("utilisateur", LoginController.getAdmin());
				model.addAttribute("LesPermis", permisMetier.findAll());
			} else {
				model.addAttribute("utilisateur", LoginController.getGerant());
				model.addAttribute(
						"LesPermis",
						permisMetier.findAllByEcole(LoginController.getGerant()
								.getEcole().getIdecole()));
			}
			return "permis";
		}

		if (p.getIdpermis() == null) {

			if (LoginController.getAdmin() != null) {

				permisMetier.ajouterPermis(p, p.getEcole().getIdecole());

			} else if (LoginController.getGerant() != null) {

				Ecole ecole = ecoleMetier.finByIdGerant(LoginController
						.getGerant().getIdgerant());
				permisMetier.ajouterPermis(p, ecole.getIdecole());

			}

		} else {
			permisMetier.modifierPermis(p);
		}
		model.addAttribute("permis", new Permis());

		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("LesPermis", permisMetier.findAll());
		} else {
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute(
					"LesPermis",
					permisMetier.findAllByEcole(LoginController.getGerant()
							.getEcole().getIdecole()));

		}
		return "permis";
	}

	@RequestMapping("/suppPermis")
	public String suppPermis(@RequestParam(value = "idpermis") Long idpermis,
			Model model) {

		permisMetier.deletePermis(idpermis);
		
		model.addAttribute("permis", new Permis());

		model.addAttribute("LesEcoles", ecoleMetier.findAll());

		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("LesPermis", permisMetier.findAll());
		} else {
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute(
					"LesPermis",
					permisMetier.findAllByEcole(LoginController.getGerant()
							.getEcole().getIdecole()));
		}

		return "permis";
	}

}
