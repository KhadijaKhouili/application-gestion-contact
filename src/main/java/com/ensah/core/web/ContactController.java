package com.ensah.core.web;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ensah.core.dao.ContactDao;
import com.ensah.core.hk.Contact;
import com.ensah.core.hk.Groupe;
import com.ensah.core.services.IContactService;
import com.ensah.core.services.IGroupeService;

@Controller
public class ContactController {
	
	@Autowired
	private IContactService contactService; 
	
	@Autowired
    private IGroupeService groupeService;

	@Autowired
	private ServletContext appContext;
	
	public ContactController() {
		
		
	}
	@PostConstruct
	public void init() {
	
	}
	
	@RequestMapping("/showContactForm")
	public String showContactForm(Model model) {
		model.addAttribute("contactModel", new Contact()); // Ajouter un objet Contact vide comme attribut du modèle
		model.addAttribute("contactList", contactService.getAllContactsSortedByName());// Ajouter la liste des contacts comme attribut
																		// du modèle

		return "form"; // On retourne le nom de la vue
	}
	
	// on récupère la valeur du path variable idContact dans le paramètre annotée
		// @PathVariable
		// Ici puisque nous avons utilisé le même nom pour le pathVariable et le
		// paramètre de la méthode, nous pouvons
		// remplacer @PathVariable(name = "idContact") par @PathVariable
		@RequestMapping(value = "/updateContactForm/{idContact}", method = RequestMethod.GET)
		public String updateContactForm(@PathVariable(name = "idContact") int idContact, Model model) {

			model.addAttribute("contactModel", contactService.getContactById(Long.valueOf(idContact)));

			return "updateContactForm";
		}
		// l'annotation @Valid est nécessaire pour faire la validation avec Hibernate
		// Validator
		// On obtient les données envoyées de la vue dans l'attribut du modèle
		// personModel
		// ces données sont copiées vers l'argument person
		// l'argument bindingResult permet de savoir si il y a des erreurs de validation
		@RequestMapping("/updateContact")
		public String updateContact(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult,
				Model model) {

			// Si il y a des erreurs de validation
			if (bindingResult.hasErrors()) {
				return "updateContactForm";
			}

			// Si il y a pas des erreurs
			contactService.updateContact(contact);
			model.addAttribute("contactList", contactService.getAllContactsSortedByName());

			// rediriger vers un autre handler
			return "redirect:/manageContacts";
		}
		
//		@RequestMapping("/addContact")
//		public String process(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult,
//				ModelMap model) {
//
//			if (bindingResult.hasErrors()) {
//				model.addAttribute("errorMsg", "Les données sont invalides.");
//			} else {
//				contactService.addContact(contact);
//				model.addAttribute("infoMsg", "Contact ajouté avec succès");
//
//			}
//			model.addAttribute("contactList", contactService.getAllContactsSortedByName()); // Mettre la liste des contacts dans le modèle
//
//			return "form";
//
//		}
		
		@RequestMapping("/manageContacts")
		public String manageContacts(@ModelAttribute("contactModel") Contact contact,Model model) {
			
			model.addAttribute("contactList", contactService.getAllContactsSortedByName()); // Mettre la liste des contacts dans le modèle
			
			return "listContacts";
		}
		
		@RequestMapping(value = "/deleteContact/{idContact}", method = RequestMethod.GET)
		public RedirectView delete(@PathVariable int idContact) {
			contactService.deleteContact(Long.valueOf(idContact));

			// Behind the scenes, RedirectView will trigger a
			// HttpServletResponse.sendRedirect() – which will perform the actual redirect.
			return new RedirectView(appContext.getContextPath() + "/manageContacts");

			// return "redirect:/manageContacts";
		}
		
	

		@PostMapping("/serachContact")
	    public String rechercherContactsParNom(@RequestParam("search") String search,@RequestParam("valeurChercher") String valeurChercher, Model model) {
			System.out.println("input : "+ search.toString());
			System.out.println("input : "+valeurChercher);
			List<Contact> contacts = new ArrayList<>();
			if(search.equals("personnalNumber")) {
				contacts= contactService.getContactsBypersonnalNumber(valeurChercher);
			}else if(search.equals("professionalNumber")) {
				contacts= contactService.getContactsByprofessionalNumber(valeurChercher);
			}else if(search.equals("nom")) {
				contacts= contactService.getContactsByNom(valeurChercher);
			}
			if(contacts.isEmpty()) {
	        	model.addAttribute("errorMsg", "les donnees cherches n'existe pas !");
	        }else {
	        	model.addAttribute("infoMsg", "les donnees cherches trouvees avec succes !");
	        }
			model.addAttribute("contactList", contacts);
	        return "searchContacts"; // Vue qui affiche les résultats de la recherche
	    }
		
		
		@RequestMapping("/addContact")
		public String process(@Valid @ModelAttribute("contactModel") Contact contact, BindingResult bindingResult,
				ModelMap model) {

			if (bindingResult.hasErrors()) {
				model.addAttribute("errorMsg", "Les données sont invalides.");
			} else {
				contactService.addContact(contact);
				Groupe groupe = groupeService.getGroupeByNom(contact.getNom());
				if(groupe==null) {
					groupe = new Groupe();
					groupe.setNomGroupe(contact.getNom());
				    groupeService.addGroupe(groupe);
				}
				groupe.addContact(contact);
				model.addAttribute("infoMsg", "Contact ajouté avec succès");

			}
			
			model.addAttribute("contactList", contactService.getAllContactsSortedByName()); // Mettre la liste des contacts dans le modèle

			return "form";

		}
		
		
		

}
