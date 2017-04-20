package com.projet.air2.controllers;

import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.multipart.MultipartFile;

import com.projet.air2.LoginController;
import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Gerant;
import com.projet.air2.metier.AdministrateurMetier;
import com.projet.air2.metier.EcoleMetier;
import com.projet.air2.metier.GerantMetier;

@Controller
@RequestMapping(value = "/adminEcole")
public class AdminEcoleController {

	@Autowired
	private EcoleMetier ecoleMetier;

	@Autowired
	private GerantMetier gerantMetier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("ecole", new Ecole());
		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("LesGerants",
				gerantMetier.findGerantNoAtribuerEcole());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
		return "ecole";
	}

	@RequestMapping("/saveEcole")
	public String saveEco(Ecole ecole, BindingResult bindingResult,
			MultipartFile file, Model model) throws Exception {

		if (bindingResult.hasErrors()) {

			model.addAttribute("ecole", new Ecole());
			model.addAttribute("LesEcoles", ecoleMetier.findAll());
			model.addAttribute("LesGerants",
					gerantMetier.findGerantNoAtribuerEcole());
			model.addAttribute("typeUser", LoginController.getTypeUser());
			model.addAttribute("utilisateur", LoginController.getAdmin());
			return "ecole";
		}

		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());

			ecole.setLogo(file.getBytes());
			ecole.setNomlogo(file.getOriginalFilename());

		}

		if (ecole.getIdecole() == null) {

			// Gerant gerant =
			// gerantMetier.finGerantById(ecole.getGerant().getIdgerant());
			// on spécifie bien que c'est le gerant courant qui a affecter le
			// gerant a l'ecole et non pas l'admin qui a crée le gerant
			// System.out.println("les info de gerant sont "+ecole.getGerant().toString());

			ecoleMetier.ajouterEcole(ecole, LoginController.getAdmin().getIdadmin(), ecole.getGerant().getIdgerant());

			Ecole ecole1 = ecoleMetier.finByIdGerant(ecole.getGerant().getIdgerant());

			Gerant gerant = ecole.getGerant();
			gerant.setEcole(ecole1);
			// on active le gerant une fois qu'on lui a designer une ecole a geré 
			gerant.setActive(true);

			// on met a jour la table des gerants
			gerantMetier.modifierGerant(gerant);

		} else {

			ecoleMetier.modifierEcole(ecole);
		}

		model.addAttribute("ecole", new Ecole());
		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("LesGerants",
				gerantMetier.findGerantNoAtribuerEcole());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
		return "ecole";
	}

}
