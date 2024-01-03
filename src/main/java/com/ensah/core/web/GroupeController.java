package com.ensah.core.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.ensah.core.hk.Contact;
import com.ensah.core.hk.Groupe;
import com.ensah.core.services.IContactService;
import com.ensah.core.services.IGroupeService;

@Controller
public class GroupeController {
	
	@Autowired
	private ServletContext appContext;
	
	@Autowired
    private final IGroupeService groupeService;
	
	@Autowired
    private final IContactService contactService;

    public GroupeController(IGroupeService groupeService, IContactService contactService) {
        this.groupeService = groupeService;
        this.contactService = contactService;
    }
    
    @RequestMapping("/showGroupeForm")
	public String showContactForm(Model model) {
		model.addAttribute("groupeModel", new Groupe()); // Ajouter un objet Contact vide comme attribut du modèle
		model.addAttribute("groupeList", groupeService.getAllGroupes());// Ajouter la liste des groupes comme attribut
																		// du modèle

		return "formGroupe"; // On retourne le nom de la vue
	}
    
    @GetMapping("/groupes")
    public String afficherGroupes(Model model) {
        List<Groupe> groupes = groupeService.getAllGroupes();
        model.addAttribute("groupeList", groupes);
        return "groupes";
    }
    
    @RequestMapping(value = "/deleteGroupe/{idGroupe}", method = RequestMethod.GET)
	public RedirectView delete(@PathVariable int idGroupe) {
		groupeService.deleteGroupe(Long.valueOf(idGroupe));

		// Behind the scenes, RedirectView will trigger a
		// HttpServletResponse.sendRedirect() – which will perform the actual redirect.
		return new RedirectView(appContext.getContextPath() + "/manageGroupes");
	}
    
    @RequestMapping(value = "/updateGroupeForm/{idGroupe}", method = RequestMethod.GET)
	public String updateGroupeForm(@PathVariable(name = "idGroupe") int idGroupe, Model model) {

		model.addAttribute("groupeModel", groupeService.getGroupeById(Long.valueOf(idGroupe)));

		return "updateGroupeForm";
	}
	// l'annotation @Valid est nécessaire pour faire la validation avec Hibernate
	// Validator
	// On obtient les données envoyées de la vue dans l'attribut du modèle
	// personModel
	// ces données sont copiées vers l'argument person
	// l'argument bindingResult permet de savoir si il y a des erreurs de validation
	@RequestMapping("/updateGroupe")
	public String updateContact(@Valid @ModelAttribute("groupeModel") Groupe groupe, BindingResult bindingResult,
			Model model) {

		// Si il y a des erreurs de validation
		if (bindingResult.hasErrors()) {
			return "updateGroupeForm";
		}

		// Si il y a pas des erreurs
		groupeService.updateGroupe(groupe);
		model.addAttribute("groupeList", groupeService.getAllGroupes());

		// rediriger vers un autre handler
		return "redirect:/manageGroupes";
	}
    
    
//    @PostMapping("/addGroupes")
//    public String creerGroupe(@RequestParam("nomGroupe") String nomGroupe) {
//        Groupe groupe = new Groupe();
//        groupe.setNom(nomGroupe);
//        groupeService.addGroupe(groupe);
//        return "redirect:/formGroupe";
//    }
    @RequestMapping("/addGroupe")
	public String process(@Valid @ModelAttribute("groupeModel") Groupe groupe, BindingResult bindingResult,
			ModelMap model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMsg", "Les données sont invalides.");
		} else {
			Groupe g = groupeService.getGroupeByNom(groupe.getNomGroupe());
			if(g==null) {
			groupeService.addGroupe(groupe);
			model.addAttribute("infoMsg", "Groupe ajouté avec succès");
			}else {
				model.addAttribute("errorMsg", "Ce groupe est deja existe !");
			}

		}
		model.addAttribute("groupeList", groupeService.getAllGroupes()); // Mettre la liste des contacts dans le modèle

		return "formGroupe";

	}
    
    @RequestMapping("/manageGroupes")
	public String manageGroupes(Model model,@ModelAttribute("groupeModel") Groupe groupe) {

		model.addAttribute("groupeList", groupeService.getAllGroupes()); // Mettre la liste des contacts dans le modèle
		model.addAttribute("contactList", contactService.getAllContacts());
		return "listGroupes";
	}
    
//    @GetMapping("/groupes/{idGroupe}")
//    public String afficherGroupe( @PathVariable(name = "idGroupe") Long idGroupe, Model model) {
//        Groupe groupe = groupeService.getGroupeById(idGroupe);
//        model.addAttribute("groupeList", groupe);
//        return "groupe";
//    }
    
    @RequestMapping("/AddContactsAuGroupe/{idGroupe}")
	public String AddContactsAuGroupe(Model model,@PathVariable(name = "idGroupe") Long idGroupe) {

//		model.addAttribute("groupeList", groupeService.getAllGroupes()); // Mettre la liste des contacts dans le modèle
		model.addAttribute("contactList", contactService.getAllContacts());
		return "contactsAssociees";
	}
    
//    @GetMapping("/groupes/{idGroupe}")
//    public String afficherContactsDeGroupe( @PathVariable(name = "idGroupe") Long idGroupe, Model model) {
//        Groupe groupe = groupeService.getGroupeById(Long.valueOf(idGroupe));
//        System.out.println(" all contacts : "+groupe.getContacts());
//        System.out.println("hii");
//       // model.addAttribute("groupeList", groupeService.getAllGroupes());
//        model.addAttribute("groupeListContacts", groupe.getContacts());
//        model.addAttribute("nomgroupe",groupe.getNomGroupe());
//        
////        return "groupe";
//        return "contactsAssociees";
//    }
    
    @GetMapping("/AddContactsAuGroupe/{idGroupe}/Contact/{idContact}")
    public String ajouterContactAuGroupe( @PathVariable(name = "idGroupe") Long idGroupe,@PathVariable("idContact") Long idContact) {
        Groupe groupe = groupeService.getGroupeById(Long.valueOf(idGroupe));
        Contact contact = contactService.getContactById(Long.valueOf(idContact));
        groupe.addContact(contact);
        groupeService.updateGroupe(groupe);
        System.out.println("idContact "+idContact);
        System.out.println("idGroupe "+idGroupe);
//        System.out.println(" all contacts : "+groupe.getContacts());
//        model.addAttribute("groupeListContacts", groupe.getContacts());
      return "redirect:/manageGroupes";
//        return "redirect:/groupes/" + idGroupe;
    }
    
    @RequestMapping("/groupeContacts/{idGroupe}")
    public String showGroupeContacts(Model model,@PathVariable(name = "idGroupe") Long idGroupe) {
    	
        Groupe groupe = groupeService.getGroupeById(Long.valueOf(idGroupe));
        model.addAttribute("groupeListContacts", groupe.getContacts());
        model.addAttribute("nomG", groupe.getNomGroupe());

		return "groupeContacts"; // On retourne le nom de la vue
	
	}
    @RequestMapping(value = "/delete/{idContact}/Groupe/{idGroupe}", method = RequestMethod.GET)
	public String deleteContactFromGroupe(@PathVariable int idGroupe,@PathVariable int idContact) {
    	Contact contact = contactService.getContactById(Long.valueOf(idContact));
    	Groupe groupe = groupeService.getGroupeById(Long.valueOf(idGroupe));
    	groupe.removeContact(contact);
    	groupeService.updateGroupe(groupe);
		// Behind the scenes, RedirectView will trigger a
		// HttpServletResponse.sendRedirect() – which will perform the actual redirect.
		return "redirect:/groupeContacts/{idGroupe}";
	}
    
    //Chercher par nom du groupe
    @PostMapping("/serachGroupe")
    public String rechercherGroupesParNom(@RequestParam("valeurChercher") String valeurChercher, Model model) {
		System.out.println("input : "+valeurChercher);
		Groupe groupe=null;
	    groupe= groupeService.getGroupeByNom(valeurChercher);
	
		if(groupe==null) {
        	model.addAttribute("errorMsg", "les donnees cherches n'existe pas !");
        }else {
        	model.addAttribute("infoMsg", "les donnees cherches trouvees avec succes !");
        }
		model.addAttribute("groupe", groupe);
        return "searchGroupes"; // Vue qui affiche les résultats de la recherche
    }
}

