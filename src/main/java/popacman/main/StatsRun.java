package popacman.main;

import java.util.EnumMap;

//import entrants.pacman.Squillyprice01.MyPacMan;
import pacman.Executor;
import pacman.controllers.IndividualGhostController;
import pacman.controllers.MASController;
//import pacman.examples.StarterGhost.POGhost;
import pacman.game.Constants.GHOST;
import pacman.game.util.Stats;

public class StatsRun {
	public static void main(String[] args) {
        Executor executor = new Executor.Builder()
        		//.setVisual(true)
        	  	.setTickLimit(4000)
        	  	.build();		

    	EnumMap<GHOST, IndividualGhostController> controllers = new EnumMap<>(GHOST.class);

        edu.southwestern.tasks.popacman.controllers.MyPacMan badboy = new edu.southwestern.tasks.popacman.controllers.MyPacMan();

        controllers.put(GHOST.INKY, new popacman.examples.StarterGhost.POGhost(GHOST.INKY));
        controllers.put(GHOST.BLINKY, new popacman.examples.StarterGhost.POGhost(GHOST.BLINKY));
        controllers.put(GHOST.PINKY, new popacman.examples.StarterGhost.POGhost(GHOST.PINKY));
        controllers.put(GHOST.SUE, new popacman.examples.StarterGhost.POGhost(GHOST.SUE));

        Stats[] stats = executor.runExperiment(badboy, new MASController(controllers), 10, "post evals");
        System.out.println(stats[0]);
	}
}
