package org.iae.annecy.st1.etape1.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.iae.annecy.st1.etape1.model.produit.Produit;

public class Catalogue implements Serializable {

	private ArrayList<Produit> maListe = new ArrayList<Produit>();

	public ArrayList<Produit> getmaListe() {
		return maListe;
	}

	public Produit rechercherProduits(String ref) {
		Iterator<Produit> it = this.getmaListe().iterator();
		Produit unProduit = new Produit();
		while (it.hasNext()) {
			Produit current = it.next();
			if (current.getRef().equals(ref)) {
				unProduit = current;
				break;
			}
		}
		return unProduit;

	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("file");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void setMaListe(ArrayList<Produit> maListe) {
		this.maListe = maListe;
	}

	public void getMaListe(ArrayList<Produit> maListe) {
		this.maListe = maListe;
	}

	public void ajouterProduit(Produit p) {
		this.maListe.add(p);
		save();
	}

	public String afficherMaListe() {
		String text = "";
		int i = 1;
		for (Produit p : maListe) {
			text += (i + ".La réference du produit est : " + p.getRef() + "," + p.getDescription() + " il s'agit d'"
					+ p.getNom() + " et au prix de " + p.getPrix() + " euros. " + p.getDescriptionProduit() + "\n");
			i++;
		}
		return text;
	}

}