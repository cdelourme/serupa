package serupa.prod.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serupa.prod.back.Repository.CommandeRepository;
import serupa.prod.back.entites.Commande;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public void create(Commande entity) {
        //commandeRepository.findAll();
        // Set breakpoint here to confirm the fail over behaviour during a transaction
    	try {
    		commandeRepository.save(entity);
    	}catch(Exception e) {System.out.println(e.getMessage());;}
    }
    public Commande findByNumero(String numero) {
		return commandeRepository.findByNumero(numero);
    }
    
    public Commande findById(Long id) {
    	Optional<Commande> opt = commandeRepository.findById(id);
		return opt.isPresent()?opt.get():null;
    }
    
	public List<Commande> getAllCommandes(){
		return commandeRepository.findAll();
	}
	
	public List<String> getAllNumeroCommande(){
		List<String> retour = new ArrayList<String>();
		List<Commande> liste = getAllCommandes();
		for(Commande com : liste) {
			retour.add(com.getNumero());			
		}
		return retour;
	}
	
	public synchronized boolean addCommande(Commande maCommande){
        if (commandeRepository.findByNumero(maCommande.getNumero()) != null) {
            return false;
        } else {
        	commandeRepository.save(maCommande);
            return true;
        }
	}
	
	public void updateCommande(Commande maCommande) {
		commandeRepository.save(maCommande);
	}
	
	public void deleteCommande(long commandeId) {
		commandeRepository.deleteById(commandeId);
	}
}