package org.iae.annecy.st1.etape1.model.person;

import java.io.Serializable;

import org.iae.annecy.st1.common.mvc.AbstractDataView;
import org.iae.annecy.st1.common.mvc.BasicDataView;
import org.iae.annecy.st1.common.mvc.DataView;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nom;
	private String prenom;
	

	public Person() {
		
	}

	public Person(Integer id, String nom, String prenom) {
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	

}
