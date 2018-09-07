package edu.southwestern.tasks.popacman.ghosts.controllers;

import static pacman.game.Constants.MAG;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import edu.southwestern.parameters.Parameters;
import edu.southwestern.tasks.mspacman.agentcontroller.ghosts.SharedNNGhostsController;
import edu.southwestern.tasks.mspacman.facades.GameFacade;
import pacman.controllers.Controller;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
//TODO: this could be useful
import pacman.game.info.GameInfo;
import pacman.game.internal.Maze;
import pacman.game.Drawable;
import pacman.game.Game;
import popacman.entrants.ghosts.spooky.George;
import popacman.entrants.ghosts.spooky.John;
import popacman.entrants.ghosts.spooky.Paul;
import popacman.entrants.ghosts.spooky.Ringo;
import popacman.examples.StarterGhostComm.POCommGhost;
import popacman.prediction.GhostLocation;
import popacman.prediction.PillModel;
import popacman.prediction.fast.GhostPredictionsFast;
import oldpacman.controllers.NewGhostController;

/**
 * a class that converts oldpacman controller information into popacman controller information
 * @author pricew
 *
 */
public class OldToNewGhostIntermediaryController extends Controller<EnumMap<GHOST, MOVE>> implements Drawable {

	public final oldpacman.controllers.NewGhostController Network;
	//public final EnumMap<GHOST, IndividualGhostController> controllers;
	public final MASController MASController;
	
	public PillModel pillModel = null;
	public Maze currentMaze;
	public GhostPredictionsFast ghostPredictions = null;
	ArrayList<Integer> eatenPills;
	ArrayList<Integer> eatenPowerPills;
	public int lastPowerPillEatenTime = -1;
	public int lastPillEatenTime = -1;
	public int[] ghostEdibleTime;
	public boolean usePillModel = Parameters.parameters.booleanParameter("usePillModel");
	public boolean useGhostModel = Parameters.parameters.booleanParameter("useGhostModel");
	public final double GHOST_THRESHOLD = Parameters.parameters.doubleParameter("probabilityThreshold");
	
	private final static GHOST BLINKY = pacman.game.Constants.GHOST.BLINKY;
	private final static GHOST PINKY = pacman.game.Constants.GHOST.PINKY;
	private final static GHOST INKY = pacman.game.Constants.GHOST.INKY;
	private final static GHOST SUE = pacman.game.Constants.GHOST.SUE;
	
	private final ZombieGhost zombieBlinky;
	private final ZombieGhost zombiePinky;
	private final ZombieGhost zombieInky;
	private final ZombieGhost zombieSue;
	
	
    public OldToNewGhostIntermediaryController(SharedNNGhostsController controller) {
    	//Instanitate the EnumMap for the MASController
    	EnumMap<GHOST, IndividualGhostController> map = new EnumMap<GHOST, IndividualGhostController>(pacman.game.Constants.GHOST.class);
    	
    	//Instantiate the zombie controllers
    	this.zombieBlinky = new ZombieGhost(BLINKY);
    	this.zombiePinky = new ZombieGhost(PINKY);
    	this.zombieInky = new ZombieGhost(INKY);
    	this.zombieSue = new ZombieGhost(SUE);
    	
    	//Put the zombie controllers in the map
    	map.put(BLINKY, zombieBlinky);
    	map.put(PINKY, zombiePinky);
    	map.put(INKY, zombieInky);
    	map.put(SUE, zombieSue);
    	
    	MASController = new MASController(map);
    	this.Network = (NewGhostController) controller;
	}

    
	private EnumMap<GHOST, MOVE> convertMoveMapOldToPO(EnumMap<oldpacman.game.Constants.GHOST, oldpacman.game.Constants.MOVE> move) {
		EnumMap<pacman.game.Constants.GHOST, pacman.game.Constants.MOVE> result = new EnumMap<pacman.game.Constants.GHOST, pacman.game.Constants.MOVE>(pacman.game.Constants.GHOST.class);
    	for(oldpacman.game.Constants.GHOST g : move.keySet()) {
    		result.put(ghostConverterOldToPO(g), moveConverterOldToPO(move.get(g)));
    	}
		return result;
	}
    
    public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue){
    	EnumMap<GHOST, MOVE> map = convertMoveMapOldToPO(this.Network.getMove());
    	for(GHOST g : map.keySet()) {
    		switch(g) {
    			case BLINKY:
    				zombieBlinky.setMove(map.get(g));
    				break;
    			case PINKY:
    				zombiePinky.setMove(map.get(g));
    				break;
    			case INKY:
    				zombieInky.setMove(map.get(g));
    				break;
    			case SUE:
    				zombieSue.setMove(map.get(g));
    				break;
    			default:
    				throw new UnsupportedOperationException("invalid ghost");    				
    		}	
    	}
    	
    	return convertMoveMapOldToPO(this.Network.getMove());
    }

	
	/**
	 * Takes an oldpacman move and returns the equivalent popacman move
	 * @param move a move of the older pacman move enumeration
	 * @return the equivalent move of the po pacman move enumeration
	 * @throws NoSuchFieldException
	 * @author pricew
	 */
	public static pacman.game.Constants.MOVE moveConverterOldToPO(oldpacman.game.Constants.MOVE move){
		switch(move) {
			case NEUTRAL:
				return pacman.game.Constants.MOVE.NEUTRAL;
			case UP:
				return pacman.game.Constants.MOVE.UP;
			case LEFT:
				return pacman.game.Constants.MOVE.LEFT;
			case DOWN:
				return pacman.game.Constants.MOVE.DOWN;
			case RIGHT:
				return pacman.game.Constants.MOVE.RIGHT;
			default:
				throw new UnsupportedOperationException("invalid parameter move");
		}
	}
	
	/**
	 * Converts the enumerations from the older pacman game to the PO version enumerations
	 * @param ghost a ghost of the older pacman code ghost enumeration
	 * @return the eqivalent ghost of the po pacman ghost enumeration
	 * @author pricew
	 */
	public static pacman.game.Constants.GHOST ghostConverterOldToPO(oldpacman.game.Constants.GHOST ghost){
		switch(ghost) {
		case BLINKY:
			return pacman.game.Constants.GHOST.BLINKY;
		case PINKY:
			return pacman.game.Constants.GHOST.PINKY;
		case INKY:
			return pacman.game.Constants.GHOST.INKY;
		case SUE:
			return pacman.game.Constants.GHOST.SUE;
		default:
			throw new UnsupportedOperationException("invalid parameter ghost");
		}
	}
	
	/**
	 * This method is used to draw visualizations of any models the ghost uses to the screen so that users may observe them.
	 * @param graphics The grpahics object we are drawing to
	 */
    public void draw(Graphics2D graphics) {

    }

    //determines whether or not to use this classes draw method
	public boolean enabled() {
		return false;
	}
	
	/**
	 * This method updates models of the game state based on observations the ghosts make.
	 * @param game The game we are playing in
	 * @param timeDue The computing time we are allowed
	 * @return the updated GameFacade
	 */
	public GameFacade updateModels(Game game, long timeDue) {
        //If we switched mazes, we need new models
		if(currentMaze != game.getCurrentMaze()){

        }
		
		if(game.wasPacManEaten()) {

		}
		      
		GameFacade informedGameFacade = new GameFacade(game);
		return informedGameFacade;
	}

}
