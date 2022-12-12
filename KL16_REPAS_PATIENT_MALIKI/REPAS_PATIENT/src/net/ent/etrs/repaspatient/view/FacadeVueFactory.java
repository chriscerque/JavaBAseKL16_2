package net.ent.etrs.repaspatient.view;

public class FacadeVueFactory {
	public static FacadeVue fabriqueFacadeVue(){
		return new FacadeVueImpl();
	}
}
