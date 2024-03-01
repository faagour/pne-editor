package org.pneditor.petrinet.adapters.grp10;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.grp10.Place;

public class PlaceAdapter extends AbstractPlace {

	private Place p;
	
	public PlaceAdapter(String label) {
		super(label);
		p = new Place(0);
	}
	
	public PlaceAdapter(Place p) {
		super("");
		this.p = p;
	}
	
	public Place getPlace() {
		return p;
	}
	
	@Override
	public void addToken() {
		// TODO Auto-generated method stub
		p.setNbJetons(p.getNbJetons()+1);
	}

	@Override
	public void removeToken() {
		// Si l'argument est négatif, notre implémentation le détecte et ne modifie pas la place.
		p.setNbJetons(p.getNbJetons()-1);
	}

	@Override
	public int getTokens() {
		return p.getNbJetons();
	}

	@Override
	public void setTokens(int tokens) {
		// Si l'argument est négatif, notre implémentation le détecte et ne modifie pas la place.
		p.setNbJetons(tokens);
	}

}
