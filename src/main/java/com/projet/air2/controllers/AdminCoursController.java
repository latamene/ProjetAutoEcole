package com.projet.air2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.air2.LoginController;
import com.projet.air2.entities.Cours;
import com.projet.air2.entities.Ecole;
import com.projet.air2.metier.CoursMetier;
import com.projet.air2.metier.EcoleMetier;

@Controller
@RequestMapping(value = "/adminCours")
public class AdminCoursController {

	@Autowired
	private CoursMetier coursMetier;
	@Autowired
	private EcoleMetier ecoleMetier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("cours", new Cours());

		model.addAttribute("LesEcoles", ecoleMetier.findAll());

		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {

			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("LesCours", coursMetier.findAll());

		} else {
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute(
					"LesCours",
					coursMetier.findAllByEcole(LoginController.getGerant()
							.getEcole().getIdecole()));

		}

		return "cours";
	}

	@RequestMapping("/saveCours")
	public String saveCat(Cours c, BindingResult bindingResult, Model model)
			throws Exception {
		if (bindingResult.hasErrors()) {

			model.addAttribute("typeUser", LoginController.getTypeUser());
			if (LoginController.getAdmin() != null) {
				model.addAttribute("LesCours", coursMetier.findAll());
				model.addAttribute("utilisateur", LoginController.getAdmin());

			} else {
				model.addAttribute("utilisateur", LoginController.getGerant());
				model.addAttribute(
						"LesCours",
						coursMetier.findAllByEcole(LoginController.getGerant()
								.getEcole().getIdecole()));

			}
			return "cours";
		}

		if (c.getIdcours() == null) {

			if (LoginController.getAdmin() != null) {
				coursMetier.ajouterCours(c, c.getEcole().getIdecole());
			} else if (LoginController.getGerant() != null) {

				Ecole ecole = ecoleMetier.finByIdGerant(LoginController
						.getGerant().getIdgerant());

				coursMetier.ajouterCours(c, ecole.getIdecole());
			}

		} else
			coursMetier.modifierCours(c);
		model.addAttribute("cours", new Cours());

		model.addAttribute("LesEcoles", ecoleMetier.findAll());

		model.addAttribute("typeUser", LoginController.getTypeUser());

		if (LoginController.getAdmin() != null) {
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("LesCours", coursMetier.findAll());

		} else {
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute(
					"LesCours",
					coursMetier.findAllByEcole(LoginController.getGerant()
							.getEcole().getIdecole()));

		}
		return "cours";
	}

	@RequestMapping("/suppCours")
	public String suppCours(@RequestParam(value = "idcours") Long idcours,
			Model model) {
		
		coursMetier.deleteCours(idcours);
		model.addAttribute("cours", new Cours());

		model.addAttribute("LesEcoles", ecoleMetier.findAll());

		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {

			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("LesCours", coursMetier.findAll());

		} else {
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute(
					"LesCours",
					coursMetier.findAllByEcole(LoginController.getGerant()
							.getEcole().getIdecole()));

		}

		return "cours";
	}

}
