package org.iae.annecy.st1.etape1.model.person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.iae.annecy.st1.etape1.model.produit.Produit;

public class Client implements Serializable{

	
	private ArrayList <Person> ListeClient = new ArrayList <Person>();

	public ArrayList <Person> getListeClient() {
		return ListeClient;
	}

	public void setListeClient(ArrayList <Person> listeClient) {
		ListeClient = listeClient;
	}

	
	public void ajouterClient(Person cli){
		this.ListeClient.add(cli);
		//save();
}
	
	public String afficherListeClient(){ 
	String text = "";
	int i = 1;
	for(Person cli : ListeClient){
		text += ( i+"Le numéro ID du client est : " + cli.getId() + "," + cli.getNom() + cli.getPrenom()+  "\n");
		i++;
		}
	return text;
}
	
	public void saveCli(){
		try{
			FileOutputStream fos = new FileOutputStream("file02");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (IOException ioe){
			ioe.printStackTrace();
		}	
	
	}
}

