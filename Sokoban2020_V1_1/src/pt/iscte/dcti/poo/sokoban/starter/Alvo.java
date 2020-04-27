package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractObject implements InteractiveObject {
	
	private boolean caixaIsInside;
	
	public Alvo(Point2D position) {
		super(position, 1, "Alvo");
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public void update(Movable element, SokobanGame game) {
		caixaIsInside = true;
	}
	public void updateForPlayer(ImageTile element, SokobanGame game) {
		caixaIsInside = false;
	}
	public boolean getCaixaIsInside() {
		return caixaIsInside;
	}
}
