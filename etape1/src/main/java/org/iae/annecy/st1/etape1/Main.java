/**
 * 
 */

package org.iae.annecy.st1.etape1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Savepoint;
import java.util.Scanner;

import org.iae.annecy.st1.common.mvc.BasicDataParam;
import org.iae.annecy.st1.common.mvc.ConsoleInputView;
import org.iae.annecy.st1.common.mvc.DataParam;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.ClientController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.Catalogue;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.person.Client;
import org.iae.annecy.st1.etape1.model.person.Person;
import org.iae.annecy.st1.etape1.model.person.PersonAddModel;
import org.iae.annecy.st1.etape1.model.person.PersonGetModel;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonAddFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonCreateFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonGetFrenchView;
import org.iae.annecy.st1.tools.ConsoleHelper;
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
	 * @throws Exception 
	 */
	public static void main(final String[] args) throws Exception {
		//throws IOException, ClassNotFoundException {

		Scanner scanBasic = new Scanner(System.in);
		int choixMenu = 0;
		int choixRetour = 0;

		do{
			MenuView.afficherMenuPrincipal();
			choixMenu = scanBasic.nextInt(); 
			switch(choixMenu){
			case 1 : 
				produitMelodie();
				MenuView.afficherMenuPrincipal();
				choixMenu = scanBasic.nextInt(); 
				break;
			case 2 :
				produitJDE();
				MenuView.afficherMenuPrincipal();
				choixMenu = scanBasic.nextInt(); 
				break ;
			case 3 :
				//a afire
				MenuView.menupanier();
				choixMenu = scanBasic.nextInt(); 
				break ;
			}
		}while(choixMenu<4);
	}

	/*if (choixMenu ==1){
					produitMelodie();
				}
				else if(choixMenu == 2){
					produitJDE();
					}	*/



	//produitJDE();



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


	private static void produitMelodie() throws ClassNotFoundException, IOException{


		Catalogue c1 = null;
		c1 = new Catalogue();


		Produit p1 = new Produit("REFLS","une lampe", "le produit est en tissu",5," Cette lampe est de grande qualité, exportée depuis l'Asie elle apportera une réelle harmonie feing-shui à votre interieur.");
		Produit p2 = new Produit("REFGE","un porte-feuille", "le produit est en cuir",6," Ce porte-feuille est un produit de grande qualité venant tout droit des hautes-alpes, il est moderne et très à la mode.");

		try {
			File fichier = new File("file");
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream(fichier));
			c1 = (Catalogue) ois.readObject();
		} catch (FileNotFoundException e) {
			c1 = new Catalogue();
			c1.ajouterProduit(p1);
			c1.ajouterProduit(p2);
		}


		Scanner sc = new Scanner(System.in);
		int choixMenu = 0;
		int choixRetour = 0;
		do{

			do{
				MenuView.afficherMenu();

				choixMenu = sc.nextInt(); 
				if (choixMenu ==1){
					String choixProduit = null;

					CatalogueController cat1 = new CatalogueController();
					cat1.setCat(c1); 
					System.out.println(cat1.get());

					choixProduit = sc.next();
					int choixAttribut = 0;
					do{
						MenuView.afficherAttribut();
						choixAttribut = sc.nextInt(); 
						if (choixAttribut ==1){
							ConsoleHelper.display("Veuillez rentrer le prix désiré: ");
							c1.rechercherProduits(choixProduit).setPrix(sc.nextDouble());//iterateur il manque les set prix
						}
						else if(choixAttribut ==2){
							ConsoleHelper.display("Veuillez rentrer la description désirée: ");
							c1.rechercherProduits(choixProduit).setDescription(sc.nextLine());// il manque les set

						}
						else if(choixAttribut ==3){
							ConsoleHelper.display("Veuillez rentrer le nom désiré: ");
							c1.rechercherProduits(choixProduit).setNom(sc.next());

						}
						else if(choixAttribut ==4){
							ConsoleHelper.display("Veuillez rentrer la description exhaustive du produit:");
							sc.nextLine();
							c1.rechercherProduits(choixProduit).setDescriptionProduit(sc.nextLine());
						}



					} while(!(choixAttribut ==1 || choixAttribut ==2 || choixAttribut ==3 || choixAttribut ==4));
					c1.save();

				}
				else if(choixMenu ==2){
					CatalogueController cat1 = new CatalogueController();
					cat1.setCat(c1); 
					ConsoleHelper.display(cat1.get()); //c.afficherMaListe();


				}
				else if(choixMenu ==3){
					ConsoleHelper.display("Veuillez renseigner le nom du produit");
					String nom = sc.next();
					ConsoleHelper.display("Veuillez renseigner la référence");
					String ref = sc.next();
					while(ref.equals(c1.rechercherProduits(ref).getRef())){
						ConsoleHelper.display("La réference existe déjà, veuillez inserer une nouvelle!");
						ref = sc.next();
					}
					ConsoleHelper.display("Veuillez renseigner une courte description");
					String description = sc.nextLine();
					sc.nextLine();
					ConsoleHelper.display("Veuillez renseigner une longue description");
					String descriptionProduit = sc.nextLine();
					ConsoleHelper.display("Veuillez renseigner le prix desiré pour ce produit");
					int prix = sc.nextInt();
					while (prix<=0){
						ConsoleHelper.display("Le prix est négatif, veuillez insérer en un nouveau");
						prix = sc.nextInt();
					}
					new Produit(ref,nom, description, prix, descriptionProduit);
					c1.ajouterProduit(new Produit(ref,nom, description, prix, descriptionProduit));

					ConsoleHelper.display("Vous venez de créer un produit");
				}
				c1.save();
				ConsoleHelper.display("Voulez-vous revenir au menu du catalogue ? 1. Oui 2. Non");
				choixRetour = sc.nextInt();

			} while(choixRetour == 1);
		} while(choixMenu<4);
	}



	private static void produitJDE() throws IOException, Exception{

		Client cli1 = new Client();
		Person pers1 = new Person(18,"Jean-pierre","JP");
		
		
		try {
			File fichier = new File("file02");
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream(fichier));
			cli1 = (Client) ois.readObject();
		} catch (FileNotFoundException e){
			cli1 = new Client();
			cli1.ajouterClient(pers1);
		}

			initUserModel();
			initCustomerModel();

			final Scanner scan = new Scanner(System.in, "UTF-8");
			int choixMenu = 0;
			int choixRetour = 0;

			MenuView.afficherMenuClient();
			choixMenu = scan.nextInt();
			
			//Person pers1 = new Person(18,"Jean-pierre","JP");
			//cli1.ajouterClient(pers1);
			int choixMe = 0;
			do{

				

				switch(choixMenu){
				case 1 : 
					
					System.out.println(cli1.afficherListeClient());

					MenuView.afficherMenuClient();
					choixMenu = scan.nextInt(); 
					break;
				case 2 :
					//DataParam newCustomer = new BasicDataParam();
					int personId;
					String personNom;
					String personPrenom;

					ConsoleHelper.display("Quel est l'ID du client ?");
					personId = scan.nextInt();

					ConsoleHelper.display("Quel est le nom du client ?");
					personNom = scan.next();

					ConsoleHelper.display("Quel est le prénom du client ?");
					personPrenom = scan.next();

					Person newpersonne = new Person(personId,personNom,personPrenom);

					cli1.ajouterClient(newpersonne);
					cli1.saveCli();
					
					ConsoleHelper.display("Vous venez de créer un client.\n");

					/*final DataView customerAddData = mainController.get("person:add", newCustomer);
					final StringView customerAddView = new PersonAddFrenchView();

					ConsoleHelper.display(customerAddView.build(customerAddData));*/

					MenuView.afficherMenuClient();
					choixMenu = scan.nextInt(); 
					break;
				case 3 :	
					ConsoleHelper.display("Quel client souhaitez-vous modifier? (Numéro d'ID)");
					
					ConsoleHelper.display(cli1.afficherListeClient());
					
					//Scanner scan1 = new Scanner(System.in);
					/*int (chClient > clts.getPerson().size()){
						ConsoleHelper.display("Message d'erreur, veuillez rentrer un client valide");
					*/
					break;
					}
				
				ConsoleHelper.display("Voulez-vous revenir au menu du catalogue ? 1. Oui 2. Non");
				choixRetour = scan.nextInt();
			}while(choixMe == 2);
			



				/*ClientController catCli = new ClientController();
					catCli.setCatCli(cli1); 
					System.out.println(catCli.get());*/



				// get a Person
				/*DataParam searchPersonParam = new BasicDataParam();
			searchPersonParam.add("id", "10"); //0-5 inconu, 5-10 TEST, >10 DERUETTE
			final DataView customerData = mainController.get("person:get", searchPersonParam);
			final StringView customerGetView = new PersonGetFrenchView();

			ConsoleHelper.display(customerGetView.build(customerData));*/

				//demande l'ajout d'une personne attribut/attribut ------ hugo
				/*DataParam newCustomer = new BasicDataParam();
			String personId = ConsoleHelper.read(scan, "Quel est l'ID du client ?");
			newCustomer.add("id", personId); // <100 = OK, sinon KO
			String personNom = ConsoleHelper.read(scan, "Quel est le nom du client ?");
			newCustomer.add("nom", personNom);
			String personPrenom = ConsoleHelper.read(scan, "Quel est le prénom du client ?");
			newCustomer.add("prenom", personPrenom);

			final DataView customerAddData = mainController.get("person:add", newCustomer);
			final StringView customerAddView = new PersonAddFrenchView();

			ConsoleHelper.display(customerAddView.build(customerAddData));*/

				//Demande l'ajout d'une personne en une seul fois
				/*final ConsoleInputView customerCreateView = new PersonCreateFrenchView();
			customerCreateView.ask(scan);

			final DataView customerAddDataBulk = mainController.get("person:add", newCustomer);
			final StringView customerAddViewBulk = new PersonAddFrenchView();

			ConsoleHelper.display(customerAddViewBulk.build(customerAddDataBulk));
				 */
			}
			final DataView userData = mainController.get("user:display");
			final StringView userView = new UserTextFrenchView();


			//ConsoleHelper.display(userView.build(userData));

		}
	
