package serupa.prod.back.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import serupa.prod.back.entites.Commande;


public interface CommandeRepository extends JpaRepository<Commande, Long>{

	public Commande findByNumero(String num);
	public Optional<Commande> findById(Long id);
    List<Commande> findAll();
}
