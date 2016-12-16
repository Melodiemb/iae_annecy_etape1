
package org.iae.annecy.st1.etape1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.Catalogue;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.panier.Panier;
import org.iae.annecy.st1.etape1.model.person.Client;
import org.iae.annecy.st1.etape1.model.person.Person;
import org.iae.annecy.st1.etape1.model.person.PersonAddModel;
import org.iae.annecy.st1.etape1.model.person.PersonGetModel;
import org.iae.annecy.st1.etape1.model.produit.Produit;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Main {

	private static Scanner scanBasic;
	private static Scanner scanFin;
	private static Scanner scan;
	private static MainController mainController;

	static {
		Main.mainController = new MainController();
	}

	public static void main(final String[] args) throws Exception {

		Catalogue c1 = null;
		c1 = new Catalogue();

		Scanner scanBasic = new Scanner(System.in);
		int choixMenu = 0;
		int chConti = 0;
		// int choixRetour = 0;

		do {
			MenuView.afficherMenuPrincipal();
			choixMenu = scanBasic.nextInt();
			switch (choixMenu) {
			case 1:
				produitMelodie(c1);
				
				break;
			case 2:
				produitJDE();
				
				break;
			case 3:
				produitClient(c1);
				break;
			}
			ConsoleHelper.display("Voulre-vous effectuer un retour au menu princpal? 1.Oui\n" + " 2.Non");
			chConti = scanBasic.nextInt();
		} while (chConti == 1);
	}

	public static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
	}

	private static void initCustomerModel() {
		final PersonGetModel customerGetModel = new PersonGetModel();
		customerGetModel.register(mainController);

		final PersonAddModel customerAddModel = new PersonAddModel();
		customerAddModel.register(mainController);
	}

	private static void produitMelodie(Catalogue c1) throws ClassNotFoundException, IOException {

		Produit p1 = new Produit("REFLS", "une lampe", "le produit est en tissu", 5,
				" Cette lampe est de grande qualité, exportée depuis l'Asie elle apportera une réelle harmonie feing-shui à votre interieur.");
		Produit p2 = new Produit("REFGE", "un porte-feuille", "le produit est en cuir", 6,
				" Ce porte-feuille est un produit de grande qualité venant tout droit des hautes-alpes, il est moderne et très à la mode.");
		Produit p3 = new Produit("MOP", "un sapin", "le produit est en pin végétal", 30,
				"Ce sapin vous apportera l'esprit de noel.");
		
		try {
			File fichier = new File("file");
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			c1 = (Catalogue) ois.readObject();
		} catch (FileNotFoundException e) {
			c1 = new Catalogue();
			c1.ajouterProduit(p1);
			c1.ajouterProduit(p2);
			c1.ajouterProduit(p3);
			
		}

		scanBasic = new Scanner(System.in);
		int choixMenu = 0;
		int choixRetour = 0;
		do {

			do {
				MenuView.afficherMenu();

				choixMenu = scanBasic.nextInt();
				if (choixMenu == 1) {
					String choixProduit = null;

					CatalogueController cat1 = new CatalogueController();
					cat1.setCat(c1);
					ConsoleHelper.display(cat1.get());

					choixProduit = scanBasic.next();
					int choixAttribut = 0;
					do {
						MenuView.afficherAttribut();
						choixAttribut = scanBasic.nextInt();
						if (choixAttribut == 1) {
							ConsoleHelper.display("Veuillez rentrer le prix désiré: ");
							c1.rechercherProduits(choixProduit).setPrix(scanBasic.nextDouble());

						} else if (choixAttribut == 2) {
							ConsoleHelper.display("Veuillez rentrer la description désirée: ");
							c1.rechercherProduits(choixProduit).setDescription(scanBasic.nextLine());

						} else if (choixAttribut == 3) {
							ConsoleHelper.display("Veuillez rentrer le nom désiré: ");
							c1.rechercherProduits(choixProduit).setNom(scanBasic.next());

						} else if (choixAttribut == 4) {
							ConsoleHelper.display("Veuillez rentrer la description exhaustive du produit:");
							scanBasic.nextLine();
							c1.rechercherProduits(choixProduit).setDescriptionProduit(scanBasic.nextLine());
						}

					} while (!(choixAttribut == 1 || choixAttribut == 2 || choixAttribut == 3 || choixAttribut == 4));
					c1.save();

				} else if (choixMenu == 2) {
					CatalogueController cat1 = new CatalogueController();
					cat1.setCat(c1);
					ConsoleHelper.display(cat1.get());

				} else if (choixMenu == 3) {
					ConsoleHelper.display("Veuillez renseigner le nom du produit");
					String nom = scanBasic.next();
					ConsoleHelper.display("Veuillez renseigner la référence");
					String ref = scanBasic.next();
					while (ref.equals(c1.rechercherProduits(ref).getRef())) {
						ConsoleHelper.display("La réference existe déjà, veuillez inserer une nouvelle!");
						ref = scanBasic.next();
					}
					ConsoleHelper.display("Veuillez renseigner une courte description");
					String description = scanBasic.nextLine();
					scanBasic.nextLine();
					ConsoleHelper.display("Veuillez renseigner une longue description");
					String descriptionProduit = scanBasic.nextLine();
					ConsoleHelper.display("Veuillez renseigner le prix desiré pour ce produit");
					int prix = scanBasic.nextInt();
					while (prix <= 0) {
						ConsoleHelper.display("Le prix est négatif, veuillez insérer en un nouveau");
						prix = scanBasic.nextInt();
					}
					new Produit(ref, nom, description, prix, descriptionProduit);
					c1.ajouterProduit(new Produit(ref, nom, description, prix, descriptionProduit));

					ConsoleHelper.display("Vous venez de créer un produit");
				}
				c1.save();
				ConsoleHelper.display("Voulez-vous revenir au menu du catalogue ? 1. Oui 2. Non");
				choixRetour = scanBasic.nextInt();

			} while (choixRetour == 1);
		} while (choixMenu < 4);
	}

	private static void produitJDE() throws IOException, ClassNotFoundException {

		Client cli1 = new Client();
		Person pers1 = new Person(18, "Tudor", "Jean-Pierre");

		try {
			File fichier = new File("file02");
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
			cli1 = (Client) ois.readObject();
		} catch (FileNotFoundException e) {
			cli1 = new Client();
			cli1.ajouterClient(pers1);
		}

		initUserModel();
		initCustomerModel();

		scan = new Scanner(System.in, "UTF-8");
		int choixMenu = 0;
		int choixRetour = 0;

		// MenuView.afficherMenuClient();
		// choixMenu = scan.nextInt();

		int choixMe = 0;
		// do {
		MenuView.afficherMenuClient();
		choixMenu = 0;
		choixMenu = scan.nextInt();
		switch (choixMenu) {
		case 1:

			ConsoleHelper.display(cli1.afficherListeClient());

			// MenuView.afficherMenuClient();
			// choixMenu = scan.nextInt();
			break;
		case 2:

			int personId;
			String personNom;
			String personPrenom;

			ConsoleHelper.display("Quel est l'ID du client ?");
			personId = scan.nextInt();

			ConsoleHelper.display("Quel est le nom du client ?");
			personNom = scan.next();

			ConsoleHelper.display("Quel est le prénom du client ?");
			personPrenom = scan.next();

			Person newpersonne = new Person(personId, personNom, personPrenom);

			cli1.ajouterClient(newpersonne);
			cli1.saveCli();

			ConsoleHelper.display("Vous venez de créer un client.\n");

			/*
			 * MenuView.afficherMenuClient(); choixMenu = scan.nextInt();
			 */
			break;
		case 3:
			ConsoleHelper.display("Quel client souhaitez-vous modifier? (Numéro d'ID)");

			ConsoleHelper.display(cli1.afficherListeClient());
			int chxClient = scan.nextInt();
			ConsoleHelper.display("Quel attribut souhaitez-vous changer?" + "\n 1.Nom" + "\n 2.Prénom");
			int chxAttribut = scan.nextInt();

			switch (chxAttribut) {
			case 1:
				ConsoleHelper.display("Quel est le nouveau nom?");
				cli1.rechercherClient(chxClient).setNom(scan.next());
				break;
			case 2:
				ConsoleHelper.display("Quel est le nouveau prénom?");
				cli1.rechercherClient(chxClient).setPrenom(scan.next());
				break;

			}

			cli1.saveCli();

			break;
		}

		/*
		 * ConsoleHelper.
		 * display("Voulez-vous revenir au menu du catalogue ? 1. Oui 2. Non");
		 * choixRetour = scan.nextInt();
		 */
		// } while (choixMe == 1);

	}


	private static void produitClient(Catalogue c1) throws IOException, ClassNotFoundException {

		Produit p1 = new Produit("REL", "une lampe", "le produit est en tissu", 5,
				" Cette lampe est de grande qualité, exportée depuis l'Asie elle apportera une réelle harmonie feing-shui à votre interieur.");
		Produit p2 = new Produit("REG", "un porte-feuille", "le produit est en cuir", 6,
				" Ce porte-feuille est un produit de grande qualité venant tout droit des hautes-alpes, il est moderne et très à la mode.");
		Produit p3 = new Produit("MOP", "un sapin", "le produit est en pin végétal", 30,
				"Ce sapin vous apportera l'esprit de noel.");
		Produit p4 = new Produit("LEF", "une plante", "le produit est vert", 8,
				"le produit est certifié résistant à toutes les conditions métérologiques.");

		Panier panier1 = new Panier();

		c1 = new Catalogue();
		c1.ajouterProduit(p1);
		c1.ajouterProduit(p2);
		c1.ajouterProduit(p3);
		c1.ajouterProduit(p4);
		scanFin = new Scanner(System.in);
		int choix2Menu = 0;

		int choix3Menu = 0;

		int chq = 0;
		int qt = 0;
		int prixTotal = 0;
		int prix = 0;
		int codePromo = 0;
		double px = 0;
		double prixPromo = 0;
		double prixCalcul = 0;

		do {
			MenuView.menupanier();

			choix2Menu = scanFin.nextInt();
			if (choix2Menu == 1) {
				ConsoleHelper.display(c1.afficherMaListe());

			} else {
				do {
					CatalogueController cat4 = new CatalogueController(c1);
					cat4.setCat(c1);
					ConsoleHelper.display(cat4.get());

					ConsoleHelper.display("Entrez la référence du produit que vous voulez ajouter:");
					String choixProduitPanier = scanFin.next();
					panier1.ajouterproduit(c1.rechercherProduits(choixProduitPanier));

					ConsoleHelper.display("Pour quelle quantité?");
					qt = scanFin.nextInt();
					panier1.retrouveProduitpanier(choixProduitPanier).setQuant(qt);
					ConsoleHelper.display("•*´¨`*•.¸ Voici votre panier: ¸.•*´¨`*•.¸");
					PanierController panier01 = new PanierController(panier1);
					ConsoleHelper.display(panier01.get());
					prix = (int) (panier1.retrouveProduitpanier(choixProduitPanier).getPrix() * qt);
					prixTotal = prixTotal + prix;
					ConsoleHelper.display("Voici le prix total de votre panier:" + prixTotal);
					ConsoleHelper.display("Voulez-vous ajouter d'autres produits?\n" + "1. Oui\n" + "2. Non");
					choix3Menu = scanFin.nextInt();

				} while (choix3Menu == 1);
				ConsoleHelper.display("Disposez-vous d'un code de promotion?" + "1. Oui" + "2. Non");
				codePromo = scanFin.nextInt();

				if (codePromo == 1) {
					ConsoleHelper.display("Veuillez rentrer votre pourcentage de promotion");

					int pourcentage = scanFin.nextInt();
					px = pourcentage;
					prixCalcul = (px / 100);
					prixPromo = prixTotal * prixCalcul;
					ConsoleHelper.display("Voici le prix total du panier: " + prixPromo + "€");

				} else {
					ConsoleHelper.display("Voici le prix total du panier: " + prixTotal + "€\n");
				}

				ConsoleHelper.display("Voulez-vous mettre votre panier en commande?\n" + "1. Oui\n" + "2. Non");
				choix3Menu = scanFin.nextInt();
				if (choix3Menu == 1) {
					ConsoleHelper.display("• ˚ •˛•˚ * 。Création de votre commande en cours 。• ˚ •˛•˚ * 。\n");
				} else {
					ConsoleHelper.display("Abandon & fin de la commande.");
				}

			}
			ConsoleHelper.display("Voulez-vous continuer?\n" + "1. Oui\n" + "2. Non");
			chq = scanFin.nextInt();
		} while (chq == 1);

	}
}