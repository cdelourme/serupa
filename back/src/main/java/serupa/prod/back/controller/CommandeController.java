package serupa.prod.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import serupa.prod.back.entites.Commande;
import serupa.prod.back.service.CommandeService;

@Controller
//@RequestMapping("commande")
public class CommandeController {
	@Autowired
	private CommandeService commandeService;
	
	@GetMapping("commande/{id}")
	public ResponseEntity<Commande> getCommandeById(@PathVariable("id") Long id) {
		Commande maCommande = commandeService.findById(id);
		return new ResponseEntity<Commande>(maCommande, HttpStatus.OK);
		//return maCommande;
	}
	@GetMapping("commande/all")
	public ResponseEntity<List<Commande>> getAllCommandes() {
		List<Commande> list = commandeService.getAllCommandes();
		return new ResponseEntity<List<Commande>>(list, HttpStatus.OK);
	}
	@PostMapping("commande")
	public ResponseEntity<Void> addCommande(@RequestBody Commande maCommande, UriComponentsBuilder builder) {
                boolean flag = commandeService.addCommande(maCommande);
                if (flag == false) {
                	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/commande/{id}").buildAndExpand(maCommande.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("commande")
	public ResponseEntity<Commande> updateCommande(@RequestBody Commande maCommande) {
		commandeService.updateCommande(maCommande);
		return new ResponseEntity<Commande>(maCommande, HttpStatus.OK);
	}
	@DeleteMapping("commande/{id}")
	public ResponseEntity<Void> deleteCommande(@PathVariable("id") Integer id) {
		commandeService.deleteCommande(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  