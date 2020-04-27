package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractObject implements InteractiveObject{

	public Parede(Point2D position) {
		super(position, 2, "Parede");
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void update(Movable element, SokobanGame game) {
		ImageMatrixGUI.getInstance().setStatusMessage("Can't Move Foreward");
	}

	@Override
	public void updateForPlayer(ImageTile element, SokobanGame game) {
		ImageMatrixGUI.getInstance().setStatusMessage("Can't Move Foreward");
	}

}
