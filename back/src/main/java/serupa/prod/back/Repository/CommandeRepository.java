package serupa.prod.back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import serupa.prod.back.entites.Commande;


public interface CommandeRepository extends JpaRepository<Commande, Long>{

	public Commande findByNumero(String num);
}
