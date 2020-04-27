package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Caixote extends AbstractObject implements Movable {

	private boolean disabled;
	
	public Caixote(Point2D position) {
		super(position, 2, "Caixote");
	}
	
	
	@Override
	public void move(Vector2D vec) {
		if(disabled)
			return;
		Point2D newPosition = getPosition().plus(vec);
		if (ImageMatrixGUI.getInstance().isWithinBounds(newPosition)){
			setPosition(newPosition);
		}
	}

	@Override
	public void disableObject(){
		disabled = true;
	}

	@Override 
	public boolean canStep() {
		return disabled;
	}
}
