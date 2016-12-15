package org.iae.annecy.st1.etape1.controller;

import org.iae.annecy.st1.etape1.model.Catalogue;

public class CatalogueController {
	Catalogue cat = new Catalogue();

	public CatalogueController(Catalogue c1) {
		// TODO Auto-generated constructor stub
	}
	public CatalogueController() {
		// TODO Auto-generated constructor stub
	}
	
	public Catalogue getCat() {
		return cat;
	}

	public void setCat(Catalogue cat) {
		this.cat = cat;
	}

	public String get(){
		return cat.afficherMaListe();
	}

	
}
