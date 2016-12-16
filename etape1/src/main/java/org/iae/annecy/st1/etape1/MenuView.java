package org.iae.annecy.st1.etape1;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class MenuView {

	public static void afficherMenu() {

		ConsoleHelper.display("\t •*´¨`*•.¸¸Menu du catalogue •*´¨`*•.¸  \n" + "1. Modifier un produit \n"
				+ "2. Afficher le catalogue \n" + "3. Ajouter un produit \n" + "4. Retour menu principal");
	}

	public static void afficherAttribut() {
		ConsoleHelper.display("1. Modifier le prix \n" + "2. Modifier la description\n" + "3. Modifier le nom \n"
				+ "4. Modifier la description exhaustive");
	}

	public static void afficherMenuPrincipal() {
		ConsoleHelper.display("•*´¨`*•.¸¸Bienvenue!•*´¨`*•.¸\n");
		ConsoleHelper
				.display("1. Gestion du catalogue produit\n" + "2. Gestion du repertoire client\n" + "3. Accès client");
	}

	public static void afficherMenuClient() {
		ConsoleHelper.display(
				"1.Afficher la liste des clients\n" + "2.Ajouter un client\n" + "3.Retrouver & modifier un client");
	}

	public static void menupanier() {
		ConsoleHelper.display("Bonjour, vous pouvez faire votre choix entre les différentes rubriques: \n"
				+ "1.Afficher la liste de produits \n" + "2.Ajouter des produits à votre commande \n");
	}
}
