package serupa.prod.back.entites;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class BocadElement  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String repere;
	private Integer quantite;
	private String designation;
	private String dimension;
	private Float poids;
	private Integer longueur;
	private Float poidsTotal;
	private String pieceMenante;
	@ManyToOne
	private Bocad bocad;
	
	public BocadElement(){
	}

	public BocadElement(String repere, Integer quantite, String designation, String dimension, Float poids,
			Float poidsTotal, String pieceMenante) {
		super();
		this.repere = repere;
		this.quantite = quantite;
		this.designation = designation;
		this.dimension = dimension;
		this.poids = poids;
		this.poidsTotal = poidsTotal;
		this.pieceMenante = pieceMenante;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRepere() {
		return repere;
	}

	public void setRepere(String repere) {
		this.repere = repere;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Float getPoids() {
		return poids;
	}

	public void setPoids(Float poids) {
		this.poids = poids;
	}

	public Float getPoidsTotal() {
		return poidsTotal;
	}

	public void setPoidsTotal(Float poidsTotal) {
		this.poidsTotal = poidsTotal;
	}

	public String getPieceMenante() {
		return pieceMenante;
	}

	public void setPieceMenante(String pieceMenante) {
		this.pieceMenante = pieceMenante;
	}

	public Bocad getBocad() {
		return bocad;
	}

	public void setBocad(Bocad bocad) {
		this.bocad = bocad;
	}

	public Integer getLongueur() {
		return longueur;
	}

	public void setLongueur(Integer longueur) {
		this.longueur = longueur;
	}
}
