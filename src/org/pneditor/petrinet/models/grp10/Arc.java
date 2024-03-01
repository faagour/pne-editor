package org.pneditor.petrinet.models.grp10;

/**
 * Cette classe représente un arc dans un réseau de pétri. Elle contient une place.
 * @author Romain
 *
 */
public class Arc {
	
	protected int poids;
	protected boolean sortant;
	protected Place place;
	
	/**
	 * Crée un Arc
	 * @param p : la place qui est reliée par cet arc.
	 * @param poids
	 * @param sortant
	 */
	public Arc(Place p, int poids, boolean sortant) {
		this.place = p;
		this.poids = poids < 0 ? 1 : poids;
		this.sortant = sortant;
	}
	
	/**
	 * Change le poids de l'arc
	 * @param poids
	 */
	public void changerPoids(int poids) {
		this.poids = poids;
	}
	
	/**
	 * Modifie le nombre de jetons de la place en suivant les règles des réseaux de pétri
	 */
	public void act() {
		this.place.setNbJetons(
				sortant ? this.place.getNbJetons() - poids : this.place.getNbJetons() + poids);
		if(this.place.getNbJetons() < 0) {
			this.place.setNbJetons(0);
		}
	}
	
	/**
	 * Renvoie true si la place peut être modifée en suivant les régles des réseaux de pétri
	 * @return (Poids < nombre de jetons de la place)
	 */
	public boolean canAct() {
		return sortant ? this.place.getNbJetons() >= poids : true;
	}
	
	/**
	 * Renvoie la valeur du caractère sortant de la place
	 * @return sortant
	 */
	public boolean isSortant() {
		return sortant;
	}
	
	/**
	 * Vérifie l'égalité entre deux arcs
	 * @return true en cas d'égalité
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Arc) {
			Arc a = (Arc) o;
			return (a.place == this.place)
					&& (a.sortant == this.sortant)
					&& (a.poids == a.poids);
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Arc : {poids: " + poids + ", sortant: " + sortant + ", place: " + place.toString() + "}";
	}

}
