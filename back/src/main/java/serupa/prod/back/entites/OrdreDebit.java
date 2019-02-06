package serupa.prod.back.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrdreDebit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Date delai;
	private String commentaire;
	private String demande_par;
	private Date demande_le;
	private String atelier;
	@Column(unique = true, nullable = false)
	private String nom;
	
	@ManyToOne
	@JsonIgnore
	private Commande maCommande;
	
	@OneToMany(mappedBy="od_Parent",cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
    private List<OrdreDebitElement> listElements = new ArrayList<OrdreDebitElement>();
	
	public OrdreDebit(){
	}

	public OrdreDebit(String nom, Date delai, String commentaire, String demande_par, Date demande_le, String atelier) {
		super();
		this.nom = nom;
		this.delai = delai;
		this.commentaire = commentaire;
		this.demande_par = demande_par;
		this.demande_le = demande_le;
		this.atelier = atelier;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Date getDelai() {
		return delai;
	}

	public void setDelai(Date delai) {
		this.delai = delai;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDemande_par() {
		return demande_par;
	}

	public void setDemande_par(String demande_par) {
		this.demande_par = demande_par;
	}

	public Date getDemande_le() {
		return demande_le;
	}

	public void setDemande_le(Date demande_le) {
		this.demande_le = demande_le;
	}

	public String getAtelier() {
		return atelier;
	}

	public void setAtelier(String atelier) {
		this.atelier = atelier;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Commande getMaCommande() {
		return maCommande;
	}

	public void setMaCommande(Commande maCommande) {
		this.maCommande = maCommande;
	}

	public List<OrdreDebitElement> getListElements() {
		return listElements;
	}

	public void setListElements(List<OrdreDebitElement> listElements) {
		this.listElements = listElements;
	}
	
	public void addElement(OrdreDebitElement element) {
		this.listElements.add(element);
		element.setOd_Parent(this);
	}
	
}
