package net.ent.etrs.repaspatient.presenter;

import net.ent.etrs.repaspatient.model.facades.FacadeFactory;
import net.ent.etrs.repaspatient.model.facades.FacadeMetier;
import net.ent.etrs.repaspatient.view.FacadeVue;
import net.ent.etrs.repaspatient.view.FacadeVueFactory;

public class Presenter {
	
	public Presenter() {
		
	FacadeMetier fm ;
	FacadeVue fv ;
	
	FacadeFactory.fabriqueFacadeMetier();
	FacadeVueFactory.fabriqueFacadeVue();
		
	}
	

	
	
	public void MenuPrincipal() {
		int result = 0;
		do {
			result = fv.afficherMenu();
			traitementChoix(result);
		} while (result != 0);
		
	}
	
	private void traitementChoix(int choix) {
		switch(choix){
			case 1: // lister tous les choix
			
			
		}
	}
}
