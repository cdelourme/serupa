package serupa.prod.back.entites;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import serupa.prod.back.operation.I_Manage;

@Entity
public class Manage implements Serializable{

	public Manage(String nom, String typeOperation, String path) {
		super();
		this.nom = nom;
		this.typeOperation = typeOperation;
		this.path = path;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String typeOperation;
	private String path;

	public Manage() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public File[] getFichiers() {
		// TODO Auto-generated method stub
		File root = new File(getPath());
		return (root.isDirectory())?root.listFiles():null;
	}
}
