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
import com.projet.air2.entities.Gerant;
import com.projet.air2.metier.GerantMetier;

@Controller
@RequestMapping(value = "/adminGerant")
public class AdminGerantController {

	@Autowired
	private GerantMetier gerantMetier;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("gerant", new Gerant());
		model.addAttribute("ListeGerants", gerantMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());

		return "gerant";
	}

	@RequestMapping("/saveGerant")
	public String saveGerant(Gerant gerant, Model model, MultipartFile file,
			BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("gerant", gerant);
			model.addAttribute("ListeGerants", gerantMetier.findAll());
			model.addAttribute("typeUser", LoginController.getTypeUser());
			model.addAttribute("utilisateur", LoginController.getAdmin());
			return "gerant";
		}

		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());

			gerant.setPhoto(file.getBytes());
			gerant.setNomphoto(file.getOriginalFilename());

		}
		// tant qu'on lui a pas affecté une ecole il peut pas travailler 
		//gerant.setActive(true);
		// il faut récupérer le id de l'administrateur qui va créer le gérant
		if ( gerant.getIdgerant()==null){
			gerant.setPassword(MD5(gerant.getPassword()));
			gerantMetier.ajouterGerant(gerant, LoginController.getAdmin().getIdadmin(), (long) 2);
		}
			
	 
		else{
			gerantMetier.modifierGerant(gerant);
		}
			
		model.addAttribute("gerant", new Gerant());
		model.addAttribute("ListeGerants", gerantMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
		return "gerant";
	}

	@RequestMapping(value = "/ListeIndex")
	public String indexListe(Model model) {

		// model.addAttribute("administrateur", new Administrateur());
		model.addAttribute("ListeGerants", gerantMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());

		return "liste-gerants";
	}

	@RequestMapping(value = "photoGerant", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoGerant(Long idgerant) throws IOException {

		Gerant gerant = gerantMetier.finGerantById(idgerant);

		if (gerant.getPhoto() == null)
			return new byte[0];
		else
			return IOUtils.toByteArray(new ByteArrayInputStream(gerant
					.getPhoto()));
	}

	@RequestMapping("/suppGerant")
	public String deleteCat(@RequestParam(value = "idgerant") Long idgerant,
			Model model) {
		
		gerantMetier.deleteGerant(idgerant);
		model.addAttribute("ListeGerants", gerantMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
	
		return "liste-gerants";
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
		 
		System.out.println("le md5 de admin5 :"+generatedPassword);
		return generatedPassword;
	}
}
