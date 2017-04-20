package com.projet.air2.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
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
import com.projet.air2.entities.Administrateur;
import com.projet.air2.metier.AdministrateurMetier;

@Controller
@RequestMapping(value = "adminAdmin")
public class AdminAdministrateurController {

	@Autowired
	private AdministrateurMetier administrateurMetier;

	// @RequestMapping(value = "/indexHome")
	// public String indexHome(Model model) {
	//
	// //model.addAttribute("administrateur", new Administrateur());
	// //model.addAttribute("ListeAdministrateurs",administrateurMetier.findAll());
	//
	// return "home";
	// }

	@RequestMapping(value = "/index")
	public String index(Model model) {

		model.addAttribute("administrateur", new Administrateur());
		model.addAttribute("ListeAdministrateurs",
				administrateurMetier.findAll());

		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());

		return "administrateur";
	}

	@RequestMapping("/saveAdmin")
	public String saveAdmin(Administrateur admin, Model model,
			MultipartFile file, BindingResult bindingResult) throws Exception {
		
		
  		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("administrateur", admin);
			model.addAttribute("ListeAdministrateurs",
					administrateurMetier.findAll());
			model.addAttribute("typeUser", LoginController.getTypeUser());
			model.addAttribute("utilisateur", LoginController.getAdmin());
			return "administrateur";
		}

		if (!file.isEmpty()) {
			BufferedImage bi = ImageIO.read(file.getInputStream());

			admin.setPhoto(file.getBytes());
			admin.setNomphoto(file.getOriginalFilename());
		}

		admin.setActive(true);
		 
		if ( admin.getIdadmin()== null) { 
			 
			admin.setPassword(MD5(admin.getPassword()));
			System.out.println("le mot de passe est : " + admin.getPassword());
			administrateurMetier.ajouterAdmin(admin, (long) 1);
		}

		else {
			administrateurMetier.modifierAdmin(admin);
		}

		model.addAttribute("administrateur", new Administrateur());
		model.addAttribute("ListeAdministrateurs",
				administrateurMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
		return "administrateur";
	}

	@RequestMapping(value = "/ListeIndex")
	public String indexListe(Model model) {

		// model.addAttribute("administrateur", new Administrateur());
		model.addAttribute("ListeAdministrateurs",
				administrateurMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
		return "liste-administrateurs";
	}

	@RequestMapping(value = "photoAdmin", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] photoAdmin(Long idadmin) throws IOException {

		Administrateur admin = administrateurMetier.finAdminById(idadmin);

		if (admin.getPhoto() == null)
			return new byte[0];
		else
			return IOUtils.toByteArray(new ByteArrayInputStream(admin
					.getPhoto()));
	}

	@RequestMapping("/suppAdmin")
	public String deleteAdmin(@RequestParam(value = "idadmin") Long idadmin,
			Model model) {

		administrateurMetier.deleteAdmin(idadmin);
		model.addAttribute("ListeAdministrateurs",
				administrateurMetier.findAll());
		model.addAttribute("typeUser", LoginController.getTypeUser());
		model.addAttribute("utilisateur", LoginController.getAdmin());
		return "liste-administrateurs";
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
