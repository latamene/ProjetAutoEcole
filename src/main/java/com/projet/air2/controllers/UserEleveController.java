package com.projet.air2.controllers;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projet.air2.LoginController;
import com.projet.air2.entities.Cours;
import com.projet.air2.metier.CoursMetier;

@Controller
@RequestMapping(value = "/userEleve")
public class UserEleveController {

	@Autowired
	private CoursMetier coursMetier;

	@RequestMapping(value = "/planning")
	public String planning(Model model) {
		
		System.out.println();
		System.out.println(coursMetier.findAll().size());
		List<Cours> coursss=coursMetier.findAll();
		for (int i = 0; i < coursss.size(); i++) {
			System.out.println("nom de cours : "+coursss.get(i).getNomCours());
			System.out.println("nom de cours : "+coursss.get(i).getDateCours());
		}
		
		System.out.println();
		model.addAttribute("typeUser", LoginController.getTypeUser());
		
		if (LoginController.getAdmin() != null) {
			 
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("listeCours", coursMetier.findAll());
		} else {
			 
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute("listeCours", coursMetier.findAllByEcole(LoginController.getGerant().getEcole().getIdecole()));
		}
		
		
		
		
		return "planningAdmin";
	}

	@RequestMapping(value = "/savePlanning")
	public String savePlanning(@RequestParam String info, Model model) {

		StringTokenizer cours = new StringTokenizer(info, ",");
		int i = 1;
		while (cours.hasMoreTokens()) {
			 
			  
			String donnes[] = cours.nextToken().split(":");
			
			System.out.println("le nom : "+donnes[0]);
			System.out.println("la date : "+donnes[1]);
			
			Cours coursUpDate=  coursMetier.finCoursByNom(donnes[0]);
			coursUpDate.setDateCours(donnes[1]);
			coursMetier.modifierCours(coursUpDate);

		}

	 

		
		model.addAttribute("typeUser", LoginController.getTypeUser());
		
		
		if (LoginController.getAdmin() != null) {
	 
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("listeCours", coursMetier.findAll());
		} else {
			 
			model.addAttribute("utilisateur", LoginController.getGerant());
			model.addAttribute("listeCours", coursMetier.findAllByEcole(LoginController.getGerant().getEcole().getIdecole()));
		}
	 

		return "planningAdmin";
	}
	
	
	
	
	
	@RequestMapping(value = "/planningEleve")
	public String planningEleve(Model model) {
	
		System.out.println();
		System.out.println(coursMetier.findAll().size());
		List<Cours> coursss=coursMetier.findAll();
		for (int i = 0; i < coursss.size(); i++) {
			System.out.println("nom de cours : "+coursss.get(i).getNomCours());
			System.out.println("nom de cours : "+coursss.get(i).getDateCours());
		}
		
		 
		model.addAttribute("typeUser", LoginController.getTypeUser());
		
		
		if (LoginController.getEleve() != null) {
			 
			model.addAttribute("utilisateur", LoginController.getEleve());
			model.addAttribute("listeCours", coursMetier.findAll());
		} else {
			 
			model.addAttribute("utilisateur", LoginController.getMoniteur());
			model.addAttribute("listeCours", coursMetier.findAllByEcole(LoginController.getGerant().getEcole().getIdecole()));
		}
	 	
		
		return "planningEleve";
	}

}
