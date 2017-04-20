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
import com.projet.air2.entities.Eleve;
import com.projet.air2.entities.Permis;
import com.projet.air2.metier.EcoleMetier;
import com.projet.air2.metier.EleveMetier;
import com.projet.air2.metier.PermisMetier;

@Controller
@RequestMapping(value = "/adminEleve")
public class AdminEleveController {

	@Autowired
	private EleveMetier eleveMetier;

	@Autowired
	private PermisMetier permisMetier;
	
	@Autowired
	private EcoleMetier ecoleMetier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("eleve", new Eleve());
	
		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("permis", new Permis());
		model.addAttribute("ListePermis", permisMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("ListeEleves", eleveMetier.findAll());
			
		} else {
			model.addAttribute("ListeEleves", eleveMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			
			model.addAttribute("utilisateur", LoginController.getGerant());
		}

		return "eleve";
	}

	@RequestMapping("/saveEleve")
	public String saveEleve(Eleve eleve, Model model, MultipartFile file,
			BindingResult bindingResult) throws Exception {
 

		if (bindingResult.hasErrors()) {
			model.addAttribute("eleve", eleve);
			
			
			model.addAttribute("LesEcoles", ecoleMetier.findAll());
			model.addAttribute("ListePermis", permisMetier.findAll());
			model.addAttribute("typeUser", LoginController.getTypeUser());
			if (LoginController.getAdmin() != null) {
				model.addAttribute("ListeEleves", eleveMetier.findAll());
				model.addAttribute("utilisateur", LoginController.getAdmin());
			} else {
				model.addAttribute("utilisateur", LoginController.getGerant());
				model.addAttribute("ListeEleves", eleveMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			}
			return "eleve";
		}

		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());

			eleve.setPhoto(file.getBytes());
			eleve.setNomphoto(file.getOriginalFilename());

		}

		eleve.setActive(true);
		// il faut récupérer les id pour créer l'eleve
		if ( eleve.getIdeleve()==null) {
			// les informations qu'il faut avoir pour insérer un nouvel étudiant
			// eleveMetier.ajouterEleve(eleve, idEcole, idGerant, idRole,
			// idPermis);

			eleve.setPassword(MD5(eleve.getPassword()));

			if (LoginController.getAdmin() != null) {// le cas ou c'est l'administrateur qui ajoute l'éleve
             
                  
                  Ecole ecole = ecoleMetier.finEcoleById(eleve.getEcole().getIdecole());
                  
                  
                  System.out.println("l id de gerant de l ecole est : "+ecole.getGerant().getIdgerant());
                  
                  
                  
                  
				eleveMetier.ajouterEleve(eleve, eleve.getEcole().getIdecole(), ecole.getGerant().getIdgerant(), (long) 4,
						eleve.getPermis().getIdpermis());
			} else {

				eleveMetier.ajouterEleve(eleve, (long) 1, LoginController
						.getGerant().getIdgerant(), (long) 4, eleve.getPermis()
						.getIdpermis());
			}

		}

		else {
			eleveMetier.modifierEleve(eleve);
		}

		model.addAttribute("eleve", new Eleve());
		
		model.addAttribute("LesEcoles", ecoleMetier.findAll());
		model.addAttribute("ListePermis", permisMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("ListeEleves", eleveMetier.findAll());
			model.addAttribute("utilisateur", LoginController.getAdmin());
		} else {
			model.addAttribute("ListeEleves", eleveMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			
			model.addAttribute("utilisateur", LoginController.getGerant());
		}
		return "eleve";
	}

	@RequestMapping(value = "/ListeIndex")
	public String indexListe(Model model) {

		// model.addAttribute("administrateur", new Administrateur());
		
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("utilisateur", LoginController.getAdmin());
			model.addAttribute("ListeEleves", eleveMetier.findAll());
		} else {
			model.addAttribute("ListeEleves", eleveMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			
			model.addAttribute("utilisateur", LoginController.getGerant());
		}

		return "liste-eleves";
	}

	@RequestMapping(value = "photoEleve", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoEleve(Long ideleve) throws IOException {

		Eleve eleve = eleveMetier.finEleveById(ideleve);

		if (eleve.getPhoto() == null)
			return new byte[0];
		else
			return IOUtils.toByteArray(new ByteArrayInputStream(eleve
					.getPhoto()));
	}

	@RequestMapping("/suppEleve")
	public String deleteEleve(@RequestParam(value = "ideleve") Long ideleve,
			Model model) {

		eleveMetier.deleteEleve(ideleve);
		
		model.addAttribute("typeUser", LoginController.getTypeUser());
		if (LoginController.getAdmin() != null) {
			model.addAttribute("ListeEleves", eleveMetier.findAll());
			model.addAttribute("utilisateur", LoginController.getAdmin());
		} else {
			model.addAttribute("ListeEleves", eleveMetier.findAllByGerant(LoginController.getGerant().getIdgerant()));
			
			model.addAttribute("utilisateur", LoginController.getGerant());
		}

		return "liste-eleves";
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

		 
		return generatedPassword;
	}
}
