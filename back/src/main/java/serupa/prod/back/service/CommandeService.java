package serupa.prod.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serupa.prod.back.Repository.CommandeRepository;
import serupa.prod.back.entites.Commande;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public void create(String name) {
        //commandeRepository.findAll();
        // Set breakpoint here to confirm the fail over behaviour during a transaction
        Commande entity = new Commande("12456","Toto","Avenue");

        commandeRepository.save(entity);
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
}