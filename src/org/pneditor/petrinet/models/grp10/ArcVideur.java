package org.pneditor.petrinet.models.grp10;

/**
 * Arc de type arc videur
 * Un arc de type videur supprime tout les jetons dans une place dès qu'il y en a un.
 * @author Romain
 *
 */
public class ArcVideur extends Arc {

	
	/**
	 * Crée un arc videur.
	 * Un arc videur est toujours sortant et de poids 0.
	 * @param p
	 */
	public ArcVideur(Place p) {
		super(p, 0, true);
	}
	
	@Override
	/**
	 * La méthode redéfinie act vide tout les jetons d'une place.
	 */
	public void act() {
		this.place.setNbJetons(0);
	}
	
	/**
	 * La méthode redéfinie canAct renvoie true si la place n'est pas vide en jetons.
	 */
	@Override
	public boolean canAct() {
		return this.place.getNbJetons() > 0;
	}
	
	@Override
	public String toString() {
		return "Arc Videur : {place: " + place.toString() + "}";
	}
}
