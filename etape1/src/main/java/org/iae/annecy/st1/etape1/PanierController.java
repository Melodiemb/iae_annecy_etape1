package org.iae.annecy.st1.etape1;

import org.iae.annecy.st1.etape1.model.panier.Panier;

public class PanierController {

	Panier panier01;
	
	public PanierController(Panier panier1) {
		this.panier01 = panier1;
		// TODO Auto-generated constructor stub
		
	}
		public String get(){
			return panier01.afficherListePanier();
		
	
	}

	
}
