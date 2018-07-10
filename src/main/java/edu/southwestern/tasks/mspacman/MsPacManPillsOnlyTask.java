package edu.southwestern.tasks.mspacman;

import edu.southwestern.evolution.genotypes.Genotype;
import edu.southwestern.networks.Network;
import edu.southwestern.parameters.CommonConstants;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.util.datastructures.ArrayUtil;
import edu.southwestern.util.datastructures.Pair;

/**
 * Simplified version of the game where ghosts stay in prison and Ms. Pac-Man only needs to eat pills
 *
 * @author Jacob Schrum
 * @param <T> phenotype
 */
public class MsPacManPillsOnlyTask<T extends Network> extends MsPacManTask<T> {

	public MsPacManPillsOnlyTask() {
		Parameters.parameters.setBoolean("imprisonedWhileEdible", true);
		CommonConstants.imprisonedWhileEdible = true;
		noPowerPills = true;
	}

	@Override
	public Pair<double[], double[]> oneEval(Genotype<T> individual, int num) {
		removePillsNearPowerPills = Parameters.parameters.booleanParameter("removePillsNearPowerPills");
		noPills = false;
		endOnlyOnTimeLimit = false;
		exitLairEdible = false;
		lairExitDatabase = false;
		simultaneousLairExit = false;
		ghostsStartOutsideLair = false;
		endAfterGhostEatingChances = false;
		onlyOneLairExitAllowed = false;
		CommonConstants.pacmanStartingPowerPillIndex = -1;
		Pair<double[], double[]> full = super.oneEval(individual, num);

		return full;
	}
}
