package org.iae.annecy.st1.etape1.model.panier;

import java.util.ArrayList;

import org.iae.annecy.st1.etape1.model.person.Person;
import org.iae.annecy.st1.etape1.model.produit.Produit;

public class Panier {
	private Person personne = new Person();

	private ArrayList<Produit> produits = new ArrayList<Produit>();

	public Person getPersonne() {
		return personne;
	}

	public void setPersonne(Person personne) {
		this.personne = personne;
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	
	public void ajouterproduit(Produit prod){
		this.getProduits().add(prod);
	}
	
	

}
