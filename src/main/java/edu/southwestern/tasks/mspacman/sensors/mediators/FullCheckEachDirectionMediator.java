package edu.southwestern.tasks.mspacman.sensors.mediators;

import edu.southwestern.parameters.CommonConstants;
import edu.southwestern.tasks.mspacman.sensors.VariableDirectionBlockLoadedInputOutputMediator;
import edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.BiasBlock;
import edu.southwestern.tasks.mspacman.sensors.blocks.booleansensors.SpecificGhostIsEdibleBlock;
import edu.southwestern.tasks.mspacman.sensors.blocks.counting.CountLairGhostsBlock;
import edu.southwestern.tasks.mspacman.sensors.blocks.counting.PillsRemainingBlock;
import edu.southwestern.tasks.mspacman.sensors.blocks.counting.PowerPillsRemainingBlock;
import edu.southwestern.tasks.mspacman.sensors.blocks.time.SpecificGhostEdibleTimeBlock;
import edu.southwestern.tasks.mspacman.sensors.blocks.time.SpecificGhostLairTimeBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.counts.VariableDirectionCountAllPillsInKStepsBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.counts.VariableDirectionKStepJunctionCountBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.counts.VariableDirectionKStepPillCountBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.distance.VariableDirectionJunctionDistanceBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.distance.VariableDirectionPillDistanceBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.distance.VariableDirectionPowerPillDistanceBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.distance.ghosts.specific.VariableDirectionSpecificGhostDistanceBlock;
import edu.southwestern.tasks.mspacman.sensors.directional.specific.VariableDirectionSpecificGhostIncomingBlock;

/**
 *
 * @author Jacob Schrum
 */
public class FullCheckEachDirectionMediator extends VariableDirectionBlockLoadedInputOutputMediator {

	public FullCheckEachDirectionMediator() {
		int direction = -1;

		blocks.add(new BiasBlock());
		// Distances
		blocks.add(new VariableDirectionPillDistanceBlock(direction));
		blocks.add(new VariableDirectionPowerPillDistanceBlock(direction));
		blocks.add(new VariableDirectionJunctionDistanceBlock(direction));
		// Specific Ghosts
		for (int i = 0; i < CommonConstants.numActiveGhosts; i++) {
			blocks.add(new VariableDirectionSpecificGhostDistanceBlock(direction, i));
			blocks.add(new VariableDirectionSpecificGhostIncomingBlock(i));
			blocks.add(new SpecificGhostIsEdibleBlock(i));
			blocks.add(new SpecificGhostEdibleTimeBlock(i));
			blocks.add(new SpecificGhostLairTimeBlock(i));
		}
		// Look ahead
		blocks.add(new VariableDirectionKStepPillCountBlock(direction));
		blocks.add(new VariableDirectionKStepJunctionCountBlock(direction));
		// Counts
		blocks.add(new PowerPillsRemainingBlock(true, false));
		blocks.add(new PillsRemainingBlock(true, false));
		blocks.add(new CountLairGhostsBlock(true, false));
		// Other
		// blocks.add(new AnyEdibleGhostBlock());
		// High level
		// blocks.add(new
		// VariableDirectionDistanceFromJunctionToGhostBlock(direction, true,
		// true));
		blocks.add(new VariableDirectionCountAllPillsInKStepsBlock(direction));
		// blocks.add(new VariableDirectionCountJunctionOptionsBlock());
		// blocks.add(new VariableDirectionOneStepSafeBlock());
	}
}
