package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Produit implements Serializable{

	private String description, ref, nom, descriptionProduit;
	private double prix;
	private int quant;
	
	public Produit(){
		
	}
	public Produit(String ref, String nom, String description, double prix, String descriptionProduit){
		this.ref = ref;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.descriptionProduit = descriptionProduit;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String afficherProduit(){
		String t = null;
	t += (this.getRef() + " "+ this.getNom() + " " + this.getDescription() + " " + this.getPrix() + " " + this.getDescriptionProduit() + "\n" );
	return t;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescriptionProduit() {
		return descriptionProduit;
	}
	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	
	}
	public String afficherProdpanier(){
		String t ="";
			t+= ("\n ----" + this.getNom() + "----" + "prix unitaire: " + this.getPrix() + "prix produits:"+ this.getPrix()*this.getQuant());
			return t;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
}