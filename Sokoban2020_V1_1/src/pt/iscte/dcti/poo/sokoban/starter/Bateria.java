package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractObject implements InteractiveObject {
	
	private boolean useless;
	
	public Bateria(Point2D initialPosition){
		super(initialPosition, 1, "Bateria");
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void updateForPlayer(ImageTile element, SokobanGame game) {
		if (!useless) {
			game.resetMoves();
			useless = true;
			ImageMatrixGUI.getInstance().removeImage(this);
		}
		ImageMatrixGUI.getInstance().setStatusMessage("Batery Here");
	}

	@Override
	public void update(Movable element, SokobanGame game) {
		ImageMatrixGUI.getInstance().setStatusMessage("Batery Retrieved");
	}
	
}
