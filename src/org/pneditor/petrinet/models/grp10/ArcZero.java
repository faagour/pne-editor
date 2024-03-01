package org.pneditor.petrinet.models.grp10;

/**
 * Arc de type arc zéro.
 * Un arc zéro est un arc standard qui ne s'active que lorsque la place est vide.
 * @author Romain
 *
 */
public class ArcZero extends Arc {

	public ArcZero(Place p) {
		super(p, 0, true);
	}
	
	/**
	 * Un arc de type arc zéro n'a pas d'action associée.
	 */
	@Override
	public void act() {
		
	}
	
	/**
	 * La méthode redéfinie canAct renvoie true si la place est bien vide.
	 */
	@Override
	public boolean canAct() {
		return this.place.getNbJetons() == 0;
	}
	
	@Override
	public String toString() {
		return "Arc Zero : {place: " + place.toString() + "}";
	}
}
