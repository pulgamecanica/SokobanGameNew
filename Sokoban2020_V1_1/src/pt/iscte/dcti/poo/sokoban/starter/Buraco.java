package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractObject implements InteractiveObject {
	
	public Buraco(Point2D position) {
		super(position, 3, "Buraco");
	}

	@Override
	public boolean isSolid() {
		return false;
	}
	@Override
	public void update(Movable element, SokobanGame game) {
		element.disableObject();
		ImageMatrixGUI.getInstance().setStatusMessage(element.getName() + " felt inside the Hole!");
	}
	@Override
	public void updateForPlayer(ImageTile element, SokobanGame game) {
		game.setYouJustFell();
		ImageMatrixGUI.getInstance().setStatusMessage(element.getName() + " YOU felt inside the Hole!");
	}

}
