package com.projet.air2.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projet.air2.LoginController;
import com.projet.air2.entities.Ecole;
import com.projet.air2.entities.Moniteur;
import com.projet.air2.metier.AdministrateurMetier;
import com.projet.air2.metier.EcoleMetier;
import com.projet.air2.metier.GerantMetier;
import com.projet.air2.metier.MoniteurMetier;

@Controller
@RequestMapping(value = "/adminMoniteur")
public class AdminMoniteurController {

	@Autowired
	private MoniteurMetier moniteurMetier;

	@Autowired
	private AdministrateurMetier administrateurMetier;

	@Autowired
	private EcoleMetier ecoleMetier;

	@Autowired
	private GerantMetier gerantMetier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("moniteur", new Moniteur());
		
		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());

		if (LoginController.getAdmin() != null) {
			
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAll());
			model.addAttribute("utilisateur", LoginController.getAdmin());
			
			
		} else {
			
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			model.addAttribute("utilisateur", LoginController.getGerant());
			
			
			
		}
		return "moniteur";
	}

	@RequestMapping("/saveMoniteur")
	public String saveGerant(Moniteur moniteur, Model model,
			MultipartFile file, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("moniteur", moniteur);
			 
			model.addAttribute("LesEcoles", ecoleMetier.findAll());
			model.addAttribute("typeUser", LoginController.getTypeUser());

			if (LoginController.getAdmin() != null) {
				model.addAttribute("ListeMoniteurs", moniteurMetier.findAll());
				model.addAttribute("utilisateur", LoginController.getAdmin());
			} else {
				model.addAttribute("ListeMoniteurs", moniteurMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
				model.addAttribute("utilisateur", LoginController.getGerant());
			}

			return "moniteur";
		}

		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());

			moniteur.setPhoto(file.getBytes());
			moniteur.setNomphoto(file.getOriginalFilename());

		}

		moniteur.setActive(true);
		// il faut récupérer le id de l'administrateur qui va créer le gérant
		if (moniteur.getIdmoniteur() == null) {
			moniteur.setPassword(MD5(moniteur.getPassword()));

			if (LoginController.getAdmin() != null) {

				// moniteurMetier.ajouterMoniteur(moniteur, idGerant, idEcole, idRole);

				Ecole ecole = ecoleMetier.finEcoleById(moniteur.getEcole()
						.getIdecole());

				System.out.println("les info de l ecole" + ecole.toString());
				moniteurMetier.ajouterMoniteur(moniteur, ecole.getGerant()
						.getIdgerant(), ecole.getIdecole(), (long) 3);

			} else {/// dans le cas où si le gerant

				moniteurMetier.ajouterMoniteur(moniteur, LoginController.getGerant().getIdgerant(), LoginController.getGerant().getEcole().getIdecole(), (long) 3);

			}

			// moniteurMetier.ajouterMoniteur(moniteur, idGerant, idEcole,
			// idRole);

		}

		else {
			moniteurMetier.modifierMoniteur(moniteur);
		}

		model.addAttribute("moniteur", new Moniteur());
 
		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());

		if (LoginController.getAdmin() != null) {
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAll());
			model.addAttribute("utilisateur", LoginController.getAdmin());
		} else {
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			model.addAttribute("utilisateur", LoginController.getGerant());
		}
		return "moniteur";
	}

	@RequestMapping(value = "/ListeIndex")
	public String indexListe(Model model) {

		 
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAll());
			model.addAttribute("utilisateur", LoginController.getAdmin());
		} else {
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			model.addAttribute("utilisateur", LoginController.getGerant());
		}

		return "liste-moniteurs";
	}

	@RequestMapping(value = "photoMoniteur", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoMoniteur(Long idmoniteur) throws IOException {

		Moniteur moniteur = moniteurMetier.finMoniteurById(idmoniteur);

		if (moniteur.getPhoto() == null)
			return new byte[0];
		else
			return IOUtils.toByteArray(new ByteArrayInputStream(moniteur
					.getPhoto()));
	}

	@RequestMapping("/suppMoniteur")
	public String deleteMoniteur(
			@RequestParam(value = "idmoniteur") Long idmoniteur, Model model) {

		moniteurMetier.deleteMoniteur(idmoniteur);
		 
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAll());
			model.addAttribute("utilisateur", LoginController.getAdmin());
		} else {
			model.addAttribute("ListeMoniteurs", moniteurMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			model.addAttribute("utilisateur", LoginController.getGerant());
		}

		return "liste-moniteurs";
	}

	
	 
	
	
	
	 
	
	
	public String MD5(String passwordToHash) {

		String generatedPassword = null;
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(passwordToHash.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		System.out.println("le md5 de admin5 :" + generatedPassword);
		return generatedPassword;
	}
	
	
	
	
	
	
	
	
	
}
