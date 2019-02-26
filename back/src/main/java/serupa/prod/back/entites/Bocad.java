package serupa.prod.back.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
	    uniqueConstraints={@UniqueConstraint(columnNames={"type", "phase", "revision"})}
	)
public class Bocad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JsonIgnore
	private Commande projet;
	@Column(nullable = false)
	private String phase;
	private Float poids;
	private String revision;
	private Integer longueur;
	private String peinture;
	private String atelier;
	private String type; // boulons, Expedition, matiere ou Assemblage
	@OneToMany(mappedBy = "bocad",cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BocadElementExpedition> expeditions = new ArrayList<BocadElementExpedition>();
	@OneToMany(mappedBy = "bocad",cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BocadElementBoulons> boulons = new ArrayList<BocadElementBoulons>();
	
	public Bocad(){
	}

	public Bocad(String phase, Float poids, String revision, Integer longueur, String peinture) {
		super();
		this.phase = phase;
		this.poids = poids;
		this.revision = revision;
		this.longueur = longueur;
		this.peinture = peinture;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Commande getProjet() {
		return projet;
	}

	public void setProjet(Commande projet) {
		this.projet = projet;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Float getPoids() {
		return poids;
	}

	public void setPoids(Float poids) {
		this.poids = poids;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public Integer getLongueur() {
		return longueur;
	}

	public void setLongueur(Integer longueur) {
		this.longueur = longueur;
	}

	public String getPeinture() {
		return peinture;
	}

	public void setPeinture(String peinture) {
		this.peinture = peinture;
	}

	public List<BocadElementExpedition> getElementExpeditions() {
		return expeditions;
	}

	public void setElements(List<BocadElementExpedition> elements) {
		this.expeditions = elements;
	}
	
	public void addExpedition(BocadElementExpedition element) {
		this.expeditions.add(element);
		element.setBocad(this);
	}
	
	public List<BocadElementExpedition> getElementBoulons() {
		return expeditions;
	}

	public void setBoulons(List<BocadElementBoulons> elements) {
		this.boulons = elements;
	}
	
	public void addBoulon(BocadElementBoulons element) {
		this.boulons.add(element);
		element.setBocad(this);
	}

	public String getAtelier() {
		return atelier;
	}

	public void setAtelier(String atelier) {
		this.atelier = atelier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
