package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Pedra extends AbstractObject implements Movable {
	
	private String bigName = "BigStone";
	private String smallName = "SmallStone";
	private boolean isBig;
	private boolean disabled;
	

	public Pedra(Point2D position, boolean imageType) {
		super(position, 2, "BigStone");
		isBig = imageType;
		if(imageType)
			setName(bigName);
		else
			setName(smallName);
	}

	public boolean getIsBig() {
		return isBig;
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
	public void disableObject() {
		disabled = true;
	}
	
	@Override 
	public boolean canStep() {
		return disabled;
	}
	
}
