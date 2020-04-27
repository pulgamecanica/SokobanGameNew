package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Vector2D;

public interface Movable extends ImageTile {
	void move(Vector2D vec);
	void disableObject();
	boolean canStep();
}
