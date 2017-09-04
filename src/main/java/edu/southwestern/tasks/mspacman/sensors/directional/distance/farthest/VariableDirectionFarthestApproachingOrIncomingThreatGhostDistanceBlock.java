/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.southwestern.tasks.mspacman.sensors.directional.distance.farthest;

import edu.southwestern.tasks.mspacman.facades.GameFacade;

/**
 *
 * @author Jacob Schrum
 */
public class VariableDirectionFarthestApproachingOrIncomingThreatGhostDistanceBlock
		extends VariableDirectionFarthestDistanceBlock {

	public VariableDirectionFarthestApproachingOrIncomingThreatGhostDistanceBlock(int dir) {
		super(dir);
	}

	@Override
	public String getType() {
		return "Approaching/Incoming Threat Ghost";
	}

	@Override
	public int[] getTargets(GameFacade gf) {
		return gf.getApproachingOrIncomingThreatGhostLocations(dir);
	}
}
