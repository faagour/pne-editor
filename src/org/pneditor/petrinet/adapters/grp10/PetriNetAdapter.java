package org.pneditor.petrinet.adapters.grp10;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.grp10.Arc;
import org.pneditor.petrinet.models.grp10.ArcVideur;
import org.pneditor.petrinet.models.grp10.ArcZero;
import org.pneditor.petrinet.models.grp10.Place;
import org.pneditor.petrinet.models.grp10.Reseau;
import org.pneditor.petrinet.models.grp10.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	
	private Reseau reseau;
	
	public PetriNetAdapter() {
		reseau = new Reseau();
	}

	@Override
	public AbstractPlace addPlace() {
		// TODO Auto-generated method stub
		reseau.addPlace(new Place(0));
		return new PlaceAdapter(reseau.getPlace(reseau.nbPlaces()-1));
	}

	@Override
	public AbstractTransition addTransition() {
		// TODO Auto-generated method stub
		reseau.addTransition(new Transition());
		return new TransitionAdapter(reseau.choisirTransition(reseau.getNbTransition()-1));
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		Arc arc;
		if(destination.isPlace()) {
			TransitionAdapter t = (TransitionAdapter) source;
			PlaceAdapter p = (PlaceAdapter) destination;
			arc = new Arc(p.getPlace(), 0, false);
			t.addArc(arc);
			return new ArcAdapter(arc, t.getTransition());
		}
		else {
			TransitionAdapter t = (TransitionAdapter) destination;
			PlaceAdapter p = (PlaceAdapter) source;
			arc = new Arc(p.getPlace(), 0, true);
			t.addArc(arc);
			return new ArcAdapter(arc, t.getTransition());
		}
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		PlaceAdapter p = (PlaceAdapter) place;
		ArcZero a = new ArcZero(p.getPlace());
		TransitionAdapter t = (TransitionAdapter) transition;
		t.addArc(a);
		return new ArcAdapter(a, t.getTransition());
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		PlaceAdapter p = (PlaceAdapter) place;
		ArcVideur a = new ArcVideur(p.getPlace());
		TransitionAdapter t = (TransitionAdapter) transition;
		t.addArc(a);
		return new ArcAdapter(a, t.getTransition());
	}

	@Override
	public void removePlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		Place p = ((PlaceAdapter) place).getPlace();
		for(int i=0; i < reseau.getNbTransition(); i++) {
			if(reseau.getPlace(i) == p) {
				reseau.removePlace(i);
			}
		}
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		// TODO Auto-generated method stub
		Transition p = ((TransitionAdapter) transition).getTransition();
		for(int i=0; i < reseau.getNbTransition(); i++) {
			if(reseau.choisirTransition(i) == p) {
				reseau.removeTransition(i);
			}
		}
	}

	@Override
	public void removeArc(AbstractArc arc) {
		// TODO Auto-generated method stub
		ArcAdapter a = (ArcAdapter) arc;
		Transition t = a.getTransition();
		for(int i=0; i < t.nbArc(); i++) {
			if(t.getArc(i).equals(a.getArc())) {
				t.removeArc(i);
			}
		}
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return ((TransitionAdapter)transition).getTransition().estTirable();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		((TransitionAdapter)transition).getTransition().step();
	}
	
	

}
