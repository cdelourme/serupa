package serupa.prod.back.entites;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrdreDebitElement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	@JsonIgnore
	private OrdreDebit od_Parent;
	
	private String reference;
	private String designation;
	private Integer longueur;
	private Integer largeur;
	private Integer quantite;
	
	
	public OrdreDebitElement(){
	}

	public OrdreDebitElement(String reference, String designation, Integer longueur, Integer largeur,
			Integer quantite) {
		super();
		this.reference = reference;
		this.designation = designation;
		this.longueur = longueur;
		this.largeur = largeur;
		this.quantite = quantite;
	}

	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}

	
	public OrdreDebit getOd_Parent() {
		return od_Parent;
	}


	public void setOd_Parent(OrdreDebit od_Parent) {
		this.od_Parent = od_Parent;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Integer getLongueur() {
		return longueur;
	}


	public void setLongueur(Integer longueur) {
		this.longueur = longueur;
	}


	public Integer getLargeur() {
		return largeur;
	}


	public void setLargeur(Integer largeur) {
		this.largeur = largeur;
	}


	public Integer getQuantite() {
		return quantite;
	}


	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
}
