package org.iae.annecy.st1.etape1.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.iae.annecy.st1.etape1.model.produit.Produit;



public class Catalogue {

	
	private ArrayList<Produit> maListe = new ArrayList <Produit>();

	public ArrayList<Produit> getmaListe() {
		return maListe;
	}
	public Produit rechercherProduits(String Ref){
		Iterator<Produit> it = this.getmaListe().iterator();
		Produit unProduit = new Produit();
		while (it.hasNext()){
			Produit current = it.next();
			if(current.getRef().equals(Ref)){
				unProduit =current;
				break;
			}
		}
		return unProduit;
	}
	
	public void setMaListe(ArrayList<Produit> maListe) {
		maListe = maListe;
	}
	public void getMaListe(ArrayList<Produit> maListe) {
		maListe = maListe;
	}


	public void ajouterProduit(Produit p){
		this.maListe.add(p);
	}

			
	

	public String afficherMaListe() {
		String text = "";
		int i = 1;
		for(Produit p : maListe){
			text += ( i+".La réference du produit est : " + p.getRef() + "," + p.getDescription() + " il s'agit d'" + p.getNom() + " et au prix de "+ p.getPrix() + " euros" + p.getDescriptionProduit() + "\n");
			i++;
			}
		return text;
	}

	}
	
