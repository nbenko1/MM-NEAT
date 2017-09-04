/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.southwestern.tasks.mspacman.sensors.directional.reachfirst;

import edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class VariableDirectionPowerPillBeforeJunctionBlock extends VariableDirectionPowerPillBeforeTargetBlock {

	public VariableDirectionPowerPillBeforeJunctionBlock(int dir) {
		super(dir);
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getJunctionIndices();
	}

	@Override
	public String getTargetType() {
		return "Junction";
	}

}
