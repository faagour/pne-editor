package org.pneditor.petrinet.models.grp10;

import java.util.ArrayList;

/**
 * Représente une transition dans un réseau de pétri
 * @author Fouad
 *
 */

public class Transition {
	
	private ArrayList<Arc> arcs;	
	
	/**
	 * Crée une transition, sans arc reliés.
	 */
	public Transition() {
		arcs= new ArrayList<Arc>();
	}
	
	/**
	 * Execute une étape dans l'exécution des réseaux de pétri
	 */
	public void step() {
		if(estTirable()) {
			for (Arc arc : arcs) {
				arc.act();	
			}
		}
	}
	
	/**
	 * Vérifie qu'une transition est tirable
	 * @return
	 */
	public boolean estTirable() {
		for (Arc arc : arcs) {
			if (!arc.canAct()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Ajoute un arc à la transition
	 * Il n'est pas possible d'ajouter un arc en double
	 * @param arc : l'arc à ajouter
	 * @return true si l'arc est ajouté
	 */
	public boolean addArc(Arc arc) {
		for(Arc arc1 : arcs) {
			if(arc1.equals(arc))
				return false;
		}
		arcs.add(arc);
		return true;
	}
	
	/**
	 * Retire un arc de la transition
	 * @param index : l'indice de l'arc à supprimer
	 */
	public void removeArc(int index) {
		arcs.remove(index);
	}
	
	/**
	 * Renvoie le nombre d'arcs reliés à la transition
	 * @return : le nombre d'arcs réliés à la transition
	 */
	public int nbArc() {
		return arcs.size();
	}
	
	/**
	 * Cherche un arc relié à la transition
	 * @param index : l'indice de l'arc à chercher
	 * @return l'arc recherché.
	 */
	public Arc getArc(int index) {
		return arcs.get(index);
	}
	
	@Override
	public String toString() {
		String res = "Transition : " + nbArc() + " arcs\n";
		for(Arc arc : arcs) {
			res += arc.toString() + '\n';
		}
		return res;
	}
	

}
