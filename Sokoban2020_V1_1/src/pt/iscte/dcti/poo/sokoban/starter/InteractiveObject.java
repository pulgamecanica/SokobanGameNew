package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;

public interface InteractiveObject extends ImageTile{
	boolean isSolid();
	void update(Movable element, SokobanGame game);
	void updateForPlayer(ImageTile element, SokobanGame game);
}