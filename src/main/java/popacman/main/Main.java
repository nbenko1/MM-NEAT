package popacman.main;


import java.util.EnumMap;

import edu.southwestern.tasks.popacman.controllers.MyPacMan;
import pacman.Executor;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
import pacman.game.Constants.GHOST;

/**
 * Created by pwillic on 06/05/2016.
 */
public class Main {

    public static void main(String[] args) {

        Executor executor = new Executor.Builder()
        		.setVisual(true)
        	  	.setTickLimit(4000)
        	  	.build();		
        
    	EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);

        MyPacMan badboy = new MyPacMan();
        
        controllers.put(GHOST.INKY, new popacman.examples.StarterGhost.POGhost(GHOST.INKY));
        controllers.put(GHOST.BLINKY, new popacman.examples.StarterGhost.POGhost(GHOST.BLINKY));
        controllers.put(GHOST.PINKY, new popacman.examples.StarterGhost.POGhost(GHOST.PINKY));
        controllers.put(GHOST.SUE, new popacman.examples.StarterGhost.POGhost(GHOST.SUE));
        
        executor.runGameTimed(badboy, new MASController(controllers));
    }
}
