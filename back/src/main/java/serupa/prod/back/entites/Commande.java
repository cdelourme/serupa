package serupa.prod.back.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Commande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(unique = true, nullable = false)
	private String numero;
	private String nomClient;
	private String adresse;
	private String affaire;
	private String societe;
	
	@OneToMany(mappedBy="maCommande",cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<OrdreDebit> listOrdreDebit = new ArrayList<OrdreDebit>();
	@OneToMany(mappedBy="projet",cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Bocad> listBocad = new ArrayList<Bocad>();
	
	public Commande(){}

	public Commande(String numero, String nomClient, String adresse) {
		super();
		this.numero = numero;
		this.nomClient = nomClient;
		this.adresse = adresse;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getAffaire() {
		return affaire;
	}

	public void setAffaire(String affaire) {
		this.affaire = affaire;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public List<OrdreDebit> getListOrdreDebit() {
		return listOrdreDebit;
	}

	public void setListOrdreDebit(List<OrdreDebit> listOrdreDebit) {
		this.listOrdreDebit = listOrdreDebit;
	}
	
	public void addOrdreDebit(OrdreDebit od) {
		this.listOrdreDebit.add(od);
		od.setMaCommande(this);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Bocad> getListBocad() {
		return listBocad;
	}

	public void setListBocad(List<Bocad> listBocad) {
		this.listBocad = listBocad;
	}
	public void addBocad(Bocad bo) {
		this.listBocad.add(bo);
		bo.setProjet(this);
	}
}
