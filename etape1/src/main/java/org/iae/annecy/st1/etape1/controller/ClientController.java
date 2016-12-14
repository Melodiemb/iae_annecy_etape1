package org.iae.annecy.st1.etape1.controller;

import org.iae.annecy.st1.etape1.model.person.Client;

public class ClientController {

	Client catCli = new Client();
	
	public Client getCatcli(){
		return catCli;
	}
	
	public void setCatCli(Client catCli) {
		this.catCli = catCli;
	}

	public String get(){
		return catCli.afficherListeClient();
	}
}
