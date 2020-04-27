package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public abstract class AbstractObject implements ImageTile {
	
	private String name;
	private Point2D position;
	private int layer;
	
	public AbstractObject(Point2D position, int layer, String name) {
		this.position = position;
		this.name = name;
		this.layer = layer;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return layer;
	}
	//Movable
//	@Override
//	public void move(Vector2D vec) {
//		return;
//	}
//	@Override
//	public void disableObject(){
//		return;
//	}
//	@Override 
//	public boolean canStep() {
//		return false;
//	}
//	//Interactive
//	@Override
//	public boolean isSolid() {
//		return true;
//	}
//	@Override
//	public void update(AbstractObject element, SokobanGame game) {
//		return;
//	}
//	@Override
//	public void updateForPlayer(ImageTile element, SokobanGame game) {
//		return;
//	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	

}
