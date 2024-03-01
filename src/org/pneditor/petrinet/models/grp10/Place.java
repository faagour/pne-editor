package org.pneditor.petrinet.models.grp10;

/**
 * Cette classe représente une place dans un réseau de pétri
 * @author Romain
 * 
 */

public class Place {
	
	private int nbJetons;
	
	/**
	 * Crée une nouvelle place
	 * @param nbJetons : le nombre de jetons à ajouter dans la place après création
	 */
	public Place(int nbJetons) {
		this.nbJetons = nbJetons;
	}
	
	/**
	 * Changer le nombre de jetons dans la place
	 * @param nbJetons
	 */
	public void setNbJetons(int nbJetons) {
		this.nbJetons = nbJetons < 0 ? 0 : nbJetons;
	}
	
	/**
	 * Obtenir le nombre de jetons dans la place
	 * @return nbJetons
	 */
	public int getNbJetons() {
		return this.nbJetons;
	}
	
	@Override
	public String toString() {
		if(nbJetons >= 0)
			return "Place : {nbJetons: " + nbJetons + "}";
		else
			return "Place : {nbJetons: " + nbJetons + "} Erreur : jetons négatifs";
	}

}
