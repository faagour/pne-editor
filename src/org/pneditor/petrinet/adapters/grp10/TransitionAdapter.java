package org.pneditor.petrinet.adapters.grp10;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.grp10.Arc;
import org.pneditor.petrinet.models.grp10.Place;
import org.pneditor.petrinet.models.grp10.Transition;

public class TransitionAdapter extends AbstractTransition {
	
	private Transition transition;

	public TransitionAdapter(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	public TransitionAdapter(Transition t) {
		super("");
		transition = t;
	}
	
	void addArc(Arc a) {
		transition.addArc(a);
	}
	
	Transition getTransition() {
		return transition;
	}
	
	
	public boolean isPlace() {
		return false;
		
	}


}

