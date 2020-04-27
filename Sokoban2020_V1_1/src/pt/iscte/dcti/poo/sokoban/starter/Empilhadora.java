package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Empilhadora implements ImageTile{

	private String PLAYERDOWN = "Empilhadora_D";
	private String PLAYERUP = "Empilhadora_U";
	private String PLAYERLEFT = "Empilhadora_L";
	private String PLAYERRIGTH = "Empilhadora_R";
	private Point2D position;
	private String imageName;
	public static int MOVES = 20;
	public static int movesDone;
	
	
	public Empilhadora(Point2D initialPosition){
		position = initialPosition;
		imageName = PLAYERLEFT;
		movesDone = 0;
	}

	@Override
	public String getName() {
		return imageName;
	}
	@Override
	public Point2D getPosition() {
		return position;
	}
	@Override
	public int getLayer() {
		return 2;
	}
	public int getMoves() {
		return movesDone;
	}
	
	public void resetMoves() {
		MOVES += MOVES;
	}
	
	public int getMovesLeft() {
		return MOVES - getMoves();
	}
	
	private void imageDirection(Vector2D vec) {
		if (vec.getX() == 0 && vec.getY() == 1)
			imageName = PLAYERDOWN;
		else if(vec.getX() == 0 && vec.getY() == -1)
			imageName = PLAYERUP;
		else if (vec.getX() == 1 && vec.getY() == 0)
			imageName = PLAYERRIGTH;
		else if (vec.getX() == -1 && vec.getY() == 0)
			imageName = PLAYERLEFT;	
	}
	public void move(Vector2D vec) {
		Point2D newPosition = position.plus(vec);
		if (ImageMatrixGUI.getInstance().isWithinBounds(newPosition)){
			position = newPosition;
			movesDone++;
		}
		imageDirection(vec);
		ImageMatrixGUI.getInstance().setStatusMessage("Welcome to Sokoban Game, MOVES DONE: " + movesDone + " you have" + getMovesLeft() + " Moves left" +  "enjoy the game :D");
		ImageMatrixGUI.getInstance().update();
	}
}
