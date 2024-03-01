package org.pneditor.petrinet.adapters.grp10;

import java.lang.reflect.Field;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.grp10.Arc;
import org.pneditor.petrinet.models.grp10.ArcVideur;
import org.pneditor.petrinet.models.grp10.ArcZero;
import org.pneditor.petrinet.models.grp10.Place;
import org.pneditor.petrinet.models.grp10.Transition;

public class ArcAdapter extends AbstractArc {
	
	private Arc arc;
	private Transition transition;
	
	
	public ArcAdapter(Arc arc, Transition transition) {
		this.arc = arc;
		this.transition = transition;
	}

	@Override
	public AbstractNode getSource() {
		// TODO Auto-generated method stub
		if(arc.isSortant()) {
			try {
				Field fieldPlace = Arc.class.getDeclaredField("place");
				fieldPlace.setAccessible(true);
				Place place = (Place) fieldPlace.get(arc);
				return new PlaceAdapter(place);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return new TransitionAdapter(transition);
		}
		return null;
	}

	@Override
	public AbstractNode getDestination() {
		// TODO Auto-generated method stub
		if(!arc.isSortant()) {
			try {
				Field fieldPlace = Arc.class.getDeclaredField("place");
				fieldPlace.setAccessible(true);
				Place place = (Place) fieldPlace.get(arc);
				return new PlaceAdapter(place);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			return new TransitionAdapter(transition);
		}
		return null;
	}
	
	Arc getArc() {
		return arc;
	}
	
	Transition getTransition() {
		return transition;
	}

	@Override
	public boolean isReset() {
		// TODO Auto-generated method stub
		return arc instanceof ArcVideur;
	}

	@Override
	public boolean isRegular() {
		// TODO Auto-generated method stub
		return arc instanceof Arc;
	}

	@Override
	public boolean isInhibitory() {
		// TODO Auto-generated method stub
		return arc instanceof ArcZero;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		// Accès à l'attribut privé poids
		Field fieldPoids;
		int poids = 0;
		try {
			fieldPoids = Arc.class.getDeclaredField("poids");
			fieldPoids.setAccessible(true);
			poids = fieldPoids.getInt(arc);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new ResetArcMultiplicityException();
		}
		return poids;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		arc.changerPoids(multiplicity);
	}

}
