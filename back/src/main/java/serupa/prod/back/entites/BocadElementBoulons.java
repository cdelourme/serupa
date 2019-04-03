package serupa.prod.back.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BocadElementBoulons implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String numero;
	private Integer quantite;
	private String designation;
	private String din;
	private String resistance;
	private String stockageSite;
	private Float poids;
	private Float poidsTotal;
	private String remarque;
	private String palette ="";
	@ManyToOne
	@JsonIgnore
	private Bocad bocad;
	
	public BocadElementBoulons() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
	public String getDin() {
		return din;
	}
	public void setDin(String din) {
		this.din = din;
	}
	public String getResistance() {
		return resistance;
	}
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	public String getStockageSite() {
		return stockageSite;
	}
	public void setStockageSite(String stockageSite) {
		this.stockageSite = stockageSite;
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
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public String getPalette() {
		return palette;
	}
	public void setPalette(String palette) {
		this.palette = palette;
	}
	public Bocad getBocad() {
		return bocad;
	}
	public void setBocad(Bocad bocad) {
		this.bocad = bocad;
	}
}
