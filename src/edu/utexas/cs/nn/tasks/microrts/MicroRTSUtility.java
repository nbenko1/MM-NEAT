package edu.utexas.cs.nn.tasks.microrts;

import java.util.ArrayList;
import java.util.List;

import edu.utexas.cs.nn.evolution.genotypes.Genotype;
import edu.utexas.cs.nn.networks.hyperneat.Substrate;
import edu.utexas.cs.nn.parameters.CommonConstants;
import edu.utexas.cs.nn.parameters.Parameters;
import edu.utexas.cs.nn.tasks.microrts.fitness.ProgressiveFitnessFunction;
import edu.utexas.cs.nn.util.ClassCreation;
import edu.utexas.cs.nn.util.datastructures.Pair;
import edu.utexas.cs.nn.util.datastructures.Triple;
import micro.ai.HasEvaluationFunction;
import micro.ai.core.AI;
import micro.rts.PhysicalGameState;
import micro.rts.PlayerAction;
import micro.rts.units.Unit;

public class MicroRTSUtility {
	
	private static final int RESOURCE_GAIN_VALUE = 2;
	private static final int WORKER_OUT_OF_BOUNDS_PENALTY = 1;
	private static final double WORKER_HARVEST_VALUE = .5; //relative to 1 resource, for use in pool

	public static <T> Pair<double[], double[]> oneEval(AI ai1, AI ai2, MicroRTSInformation task) {
//		do{
//			PlayerAction pa1;
//			try {
//				pa1 = ai1.getAction(0, gs); //throws exception
//				gs.issueSafe(pa1);
//			} catch (Exception e1) { e1.printStackTrace();System.exit(1); }
//			PlayerAction pa2;
//			try {
//				pa2 = ai2.getAction(1, gs); //throws exception
//				gs.issueSafe(pa2);
//			} catch (Exception e) { e.printStackTrace();System.exit(1); }
//			if(Parameters.parameters.classParameter("microRTSFitnessFunction").equals(ProgressiveFitnessFunction.class)){
//				int unitDifferenceNow = 0;
//				int maxBaseX = -1, maxBaseY = -1;
//				double resourcePool = 0;
//				double formerResourcePool = 0;
//				Unit currentUnit;
//				boolean baseAlive = false;
//				boolean baseDeathRecorded = false;
//				for(int i = 0; i < pgs.getWidth(); i++){
//					for(int j = 0; j < pgs.getHeight(); j++){
//						baseAlive = false;
//						currentUnit = pgs.getUnitAt(i, j);
//						if(currentUnit!=null){
//							if(currentUnit.getPlayer() == 0){
//								unitDifferenceNow++;
//								resourcePool += currentUnit.getCost();
//								if(currentUnit.getType().name.equals("Base")){
//									resourcePool += currentUnit.getResources();
//									if(currentUnit.getX() > maxBaseX) maxBaseX = currentUnit.getX(); //if its a new base record its location
//									if(currentUnit.getY() > maxBaseY) maxBaseY = currentUnit.getY();
//									baseAlive = true;
//									assert(baseDeathRecorded == false): "base was created after all previous bases have been destroyed!";
//								} //end if(base)
//								else if(currentUnit.getType().name.equals("Worker")){
//									if(currentUnit.getResources() > 0){
//										resourcePool += WORKER_HARVEST_VALUE;
//										if(!isUnitInRange(currentUnit, 
//												currentUnit.getPlayer() == 0 ? 0 : pgs.getWidth, 
//												currentUnit.getPlayer() == 0 ? 0 : pgs.getWidth, 
//														maxBaseX, maxBaseY)){
//											task.setHarvestingEfficiency (task.getHarvestingEfficiency() - WORKER_OUT_OF_BOUNDS_PENALTY);
//										}
//									}
//								}
//							} //end if (unit is ours)
//							else if(currentUnit.getPlayer() == 1) unitDifferenceNow--;
//						}
//					}//end j
//				}//end i
//				if(!baseAlive && !baseDeathRecorded) {
//					baseUpTime = gs.getTime();
//					baseDeathRecorded = true;
//				}
//				if(resourcePool > formerResourcePool){
//					harvestingEfficiencyIndex += RESOURCE_GAIN_VALUE;
//				}
//				formerResourcePool = resourcePool;
//				averageUnitDifference += (unitDifferenceNow - averageUnitDifference) / currentCycle;
//			} //end if(Parameters.. = progressive)
//			gameover = gs.cycle();
//			if(CommonConstants.watch) w.repaint();
//		}while(!gameover && gs.getTime()<MAXCYCLES);
//
//		if(CommonConstants.watch) 
//			w.dispose();
//		return ff.getFitness(gs);
		return null;
	}

	/**
	 * Method that returns a list of information about the substrate layers
	 * contained in the network.
	 *
	 * @return List of Substrates in order from inputs to hidden to output
	 *         layers
	 */
	public static List<Substrate> getSubstrateInformation(PhysicalGameState pgs) {
		int height = pgs.getHeight();
		int width = pgs.getWidth();
		ArrayList<Substrate> subs = new ArrayList<Substrate>();
		
		Substrate inputsBoardState = new Substrate(new Pair<Integer, Integer>(width, height),
				Substrate.INPUT_SUBSTRATE, new Triple<Integer, Integer, Integer>(0, Substrate.INPUT_SUBSTRATE, 0), "Inputs Board State");
		subs.add(inputsBoardState);
		
		if(Parameters.parameters.booleanParameter("")) {
			
		}
//		if( My){
//			
//		}
			
		if(!Parameters.parameters.booleanParameter("mRTSComplex")){
		Substrate processing = new Substrate(new Pair<Integer, Integer>(width, height), 
				Substrate.PROCCESS_SUBSTRATE, new Triple<Integer, Integer, Integer>(0, Substrate.PROCCESS_SUBSTRATE, 0), "Processing");
		subs.add(processing);
		Substrate output = new Substrate(new Pair<Integer, Integer>(1,1),
				Substrate.OUTPUT_SUBSTRATE, new Triple<Integer, Integer, Integer>(0, Substrate.OUTPUT_SUBSTRATE, 0), "Output");
		subs.add(output);
		}
		return subs;
	} 
	
	public static List<Pair<String, String>> getSubstrateConnectivity(PhysicalGameState pgs) {
		ArrayList<Pair<String, String>> conn = new ArrayList<Pair<String, String>>();
		
		if(Parameters.parameters.booleanParameter("mRTSComplex")) {
			conn.add(new Pair<String, String>("Inputs Board State", "Processing"));
		} else {
			
		}
		
		conn.add(new Pair<String, String>("Processing","Output"));
		if(Parameters.parameters.booleanParameter("extraHNLinks")) {
			if(Parameters.parameters.booleanParameter("mRTSComplex")) {

			} else {
				conn.add(new Pair<String, String>("Inputs Board State","Output"));
			}
		}
		return conn;
	}
	
	/**
	 * determines whether unit is in given range.
	 * @param u unit to be judged
	 * @param x1 top left x of range
	 * @param y1 top left y of range
	 * @param x2 bottom right x of range
	 * @param y2 bottom right y of range
	 * @return true if u is within or on the borders
	 */
	public static boolean isUnitInRange(Unit u, int x1, int y1, int x2, int y2){
		int unitX = u.getX(); 
		int unitY = u.getY();
		if(y1 > y2){ //if "top left" is lower
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		if(x1 > x2){ //if "top left" is farther right
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		if(unitX < x1 || unitY < y1 || unitX > x2 || unitY > y2){
			return false;
		} else
			return true;
	}
}
