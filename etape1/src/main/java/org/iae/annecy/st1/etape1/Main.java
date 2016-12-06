/**
 * 
 */

package org.iae.annecy.st1.etape1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.Catalogue;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;

/**
 * Classe permetant de tester le MVC.
 * 
 * @author Djer1013
 */
public class Main{

	/**
	 * COntroller pemetant le traitement des actions d'exemple.
	 */
	private static MainController mainController;

	static {
		Main.mainController = new MainController();
	}

	/**
	 * Lance l'application.
	 * 
	 * @param args
	 *            command line parameters
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(final String[] args) throws IOException, ClassNotFoundException {
		initUserModel();
		
		
		
		final DataView userData = mainController.get("user:display");
		final StringView userView = new UserTextFrenchView();
		

		//ConsoleHelper.display(userView.build(userData));
		
		Catalogue c1 = null;
		
		
		try {
			File fichier = new File("file");
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream(fichier));
			c1 = (Catalogue) ois.readObject();
		} catch (IOException e) {
			c1 = new Catalogue();

		}
		CatalogueController cat1 = new CatalogueController();
		cat1.setCat(c1); 
		
		Produit p1 = new Produit("REFLS","une lampe", "le produit est en tissu",5," Cette lampe est de grande qualité, exportée depuis l'Asie elle apportera une réelle harmonie feing-shui à votre interieur.");
		Produit p2 = new Produit("REFGE","une porte-feuille", "le produit est en cuir",6," Ce porte-feuille est un produit de grande qualité venant tout droit des hautes-alpes, il est moderne et très à la mode.");
		
		/*c1.ajouterProduit(p1);
		c1.ajouterProduit(p2);
		*/
		
		Scanner sc = new Scanner(System.in);
		int choixMenu = 0;
		int choixRetour = 0;
		do{
			
			do{
				afficherMenu();
				
				choixMenu = sc.nextInt(); //ce sera l'entier suivant
				if (choixMenu ==1){
					String choixProduit = null;
					
					
					System.out.println(cat1.get());
					
					choixProduit = sc.next();
					int choixAttribut = 0;
					do{
						afficherAttribut();
						choixAttribut = sc.nextInt(); // on a recupere ce qu'il veut changer
						if (choixAttribut ==1){
							System.out.println("Veuillez rentrer le prix désiré: ");
							c1.rechercherProduits(choixProduit).setPrix(sc.nextDouble());//iterateur il manque les set prix
						}
						else if(choixAttribut ==2){
							System.out.println("Veuillez rentrer la description désirée: ");
							c1.rechercherProduits(choixProduit).setDescription(sc.nextLine());// il manque les set
						}
						else if(choixAttribut ==3){
							System.out.println("Veuillez rentrer le nom désiré: ");
							c1.rechercherProduits(choixProduit).setNom(sc.next());
						}
						else if(choixAttribut ==4){
							System.out.println("Veuillez rentrer la description exhaustive du produit:");
							sc.nextLine();
							c1.rechercherProduits(choixProduit).setDescriptionProduit(sc.nextLine());
						}
			
					} while(!(choixAttribut ==1 || choixAttribut ==2 || choixAttribut ==3 || choixAttribut ==4));
				
						
					
				}
				else if(choixMenu ==2){
					System.out.println(cat1.get()); //c.afficherMaListe();
					
					
				}
				else if(choixMenu ==3){
					System.out.println("Veuillez renseigner le nom du produit");
					String nom = sc.next();
					System.out.println("Veuillez renseigner la référence");
					String ref = sc.next();
					System.out.println("Veuillez renseigner une courte description");
					String description = sc.nextLine();
					sc.nextLine();
					System.out.println("Veuillez renseigner une longue description");
					String descriptionProduit = sc.nextLine();
					System.out.println("Veuillez renseigner le prix desiré pour ce produit");
					int prix = sc.nextInt();
					new Produit(ref,nom, description, prix, descriptionProduit);
					c1.ajouterProduit(new Produit(ref,nom, description, prix, descriptionProduit));
					 
					}
				
				
				
				System.out.println("Vous venez de créer un produit");
				
				
				System.out.println("Voulez-vous revenir au menu principal? 1. Oui 2. Non");
				choixRetour = sc.nextInt();
			} while(choixRetour == 1);
		} while(!(choixMenu ==1 || choixMenu ==2));
		
		
	}
	
	public static void afficherMenu(){
		System.out.println("Bienvenue terrien! ʕ•́ᴥ•̀ʔっ \n");
		System.out.println("♡ Menu du catalogue ♡ \n"
				+ "1. Modifier un produit \n"
				+ "2. Afficher le catalogue \n"
				+ "3. Ajouter un produit");
	}
	
	public static void afficherAttribut(){
		System.out.println(
				  "1. Modifier le prix \n"
				+ "2. Modifier la description\n"
				+ "3. Modifier le nom \n"
				+ "4. Modifier la description exhaustive");
	}

	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
	}

}
