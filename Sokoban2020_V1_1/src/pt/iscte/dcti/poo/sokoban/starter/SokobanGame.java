package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class SokobanGame implements Observer {
 	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private List<ImageTile> tiles = new ArrayList<ImageTile>();
	private List<AbstractObject> elementsOfTheMap = new ArrayList<>();
	private List<Movable> elementsMovable = new ArrayList<>();
	private List<InteractiveObject> elementsInteractive = new ArrayList<>();
	private Empilhadora player; 
	private int level;
	private boolean youJustFell;
	
	public SokobanGame(int level){
		this.level = level;
		ImageMatrixGUI.getInstance().setName("SokobanGame Level 0");
		ImageMatrixGUI.getInstance().setStatusMessage("Welcome, please use the arrows to move the player");
		tiles = buildSampleLevel();
		buildLevelFromFile(level);
		tiles.addAll(elementsOfTheMap);
		ImageMatrixGUI.getInstance().addImages(tiles);
		for(AbstractObject x: elementsOfTheMap) { 
			if (x instanceof Movable)
				elementsMovable.add((Movable)x);
			else if(x instanceof InteractiveObject)
				elementsInteractive.add((InteractiveObject)x);
		}
	}
	private List<ImageTile> buildSampleLevel(){
		// Build 10x10 floor tiles
		for (int y = 0; y != HEIGHT; y++)
			for (int x = 0; x != WIDTH ; x++)
				tiles.add(new Chao(new Point2D(x,y)));	
		return tiles;	
	}
	private void buildLevelFromFile(int level) {
		String fileName = "levels/level" + level + ".txt";
		try {
			int integerY = 0;
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				for(int integerX = 0; integerX < line.length(); integerX++)
					if(SokobanGame.filter(line.charAt(integerX), new Point2D(integerX, integerY)) != null)
						elementsOfTheMap.add(SokobanGame.filter(line.charAt(integerX), new Point2D(integerX, integerY)));
				Empilhadora empilhadora = getPlayer(line, integerY);
				if(empilhadora != null) {
					player = empilhadora;
					tiles.add(empilhadora);
				}
				integerY++;
			}
			
		}catch (FileNotFoundException e) {
			System.err.println("ficheiro nao encontrado");
		}
	}
	private Empilhadora getPlayer(String line, int integerY) {
		for(int integerX = 0; integerX < line.length(); integerX++)
			if(line.charAt(integerX) == 'E')
				return new Empilhadora(new Point2D(integerX, integerY));
		return null;
	}
	public void setYouJustFell() {
		youJustFell = true;
	}
	private void movableCheck (Vector2D vec) {
		Point2D newPosition = player.getPosition().plus(vec);
		Point2D futurePosition = newPosition.plus(vec);
		for(AbstractObject x: elementsOfTheMap)
			if(newPosition.equals(x.getPosition()) && playerCanMove(futurePosition)) {
				if(x instanceof Movable) {
					((Movable)(x)).move(vec);
					interactiveCheck((Movable)x);
				}	
			}
	}
	private void interactiveCheck (Movable element) {
		for(AbstractObject x: (elementsOfTheMap))
			if(x instanceof InteractiveObject)
				if(x.getPosition().equals(element.getPosition()))
					((InteractiveObject)x).update(element, this);
	}
	private void interactiveCheckForPlayer (ImageTile element) {
		for(AbstractObject x: elementsOfTheMap)
			if(x.getPosition().equals(element.getPosition()))
				if (x instanceof InteractiveObject)
					((InteractiveObject)(x)).updateForPlayer(element, this);
	}
	private boolean playerCanMove(Point2D newPosition) {
		if(!canMoveHole(newPosition))
			return false;
		for(Movable x: elementsMovable) {
			if(!((Movable)x).canStep() && newPosition.equals(x.getPosition())) {
				return false;
			}
		}
		for(InteractiveObject x: elementsInteractive)
			if(newPosition.equals(x.getPosition()) && x.isSolid())
				return false;
		return true;
	}
	private boolean canMoveHole(Point2D newPosition) {
		for(Movable x: elementsMovable) 
			if(x.canStep() && newPosition.equals(x.getPosition()))
				if((((ImageTile)x) instanceof Pedra))
					if(((Pedra)x).getIsBig()) 
						return false;
		return true;
	}	
	
	private boolean winCeck() {
		if(player.getMovesLeft()<= 0 || youJustFell){
			startLevel();
			return false;
		}
		for(ImageTile x: tiles)
			if (x instanceof Alvo)
				if(!((Alvo)x).getCaixaIsInside())
					return false;
		BestScores bs = new BestScores(level);
		System.out.println("Get the best: " + bs.getTopOne());
		bs.setBestScore(player.getMoves());
		System.out.println("Get the best AFTER: " + bs.getTopOne());
		return true;
	}
	
	public void resetMoves() {
		player.resetMoves();
	}
	
	private void startLevel() {
		tiles.clear();
		elementsOfTheMap.clear();
		player = null;
		ImageMatrixGUI.getInstance().clearImages();
		ImageMatrixGUI.getInstance().unregisterObserver(this);
		SokobanGame s = new SokobanGame(level);
		ImageMatrixGUI.getInstance().registerObserver(s);
	}
	
	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		if (Direction.isDirection(lastKeyPressed)) {
			if (player != null) {
				Direction dir = Direction.directionFor(lastKeyPressed);
				movableCheck(dir.asVector());
				if(playerCanMove(player.getPosition().plus(dir.asVector()))) {
					player.move(dir.asVector());
					interactiveCheckForPlayer(player);
				}
				if(winCeck()) {
					level++;
					startLevel();
				}
			}
		}		
		if (lastKeyPressed == KeyEvent.VK_ENTER) 
			if (player != null) {
				level++;
				startLevel();
			}
		ImageMatrixGUI.getInstance().update();
	}
	public static AbstractObject filter(char elementOfTheMap, Point2D position) {
		  switch (elementOfTheMap) {
		  case 'b': return new Bateria(position) ;     
		  case '#': return new Parede(position) ;
          case 'P': return new Pedra(position, true) ;
          case 'p': return new Pedra(position, false) ;
          case 'C': return new Caixote(position) ;
          case 'X': return new Alvo(position) ;
          case 'O': return new Buraco(position) ;
          default: return null;
      }
	}
}
