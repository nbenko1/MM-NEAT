package edu.utexas.cs.nn.tasks.microrts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jdom.JDOMException;

import edu.utexas.cs.nn.MMNEAT.MMNEAT;
import edu.utexas.cs.nn.evolution.genotypes.Genotype;
import edu.utexas.cs.nn.networks.Network;
import edu.utexas.cs.nn.networks.NetworkTask;
import edu.utexas.cs.nn.networks.hyperneat.HyperNEATTask;
import edu.utexas.cs.nn.networks.hyperneat.Substrate;
import edu.utexas.cs.nn.parameters.CommonConstants;
import edu.utexas.cs.nn.parameters.Parameters;
import edu.utexas.cs.nn.tasks.NoisyLonerTask;
import edu.utexas.cs.nn.tasks.SinglePopulationCoevolutionTask;
import edu.utexas.cs.nn.tasks.microrts.evaluation.NNEvaluationFunction;
import edu.utexas.cs.nn.tasks.microrts.fitness.ProgressiveFitnessFunction;
import edu.utexas.cs.nn.tasks.microrts.fitness.RTSFitnessFunction;
import edu.utexas.cs.nn.util.ClassCreation;
import edu.utexas.cs.nn.util.datastructures.Pair;
import micro.ai.HasEvaluationFunction;
import micro.ai.core.AI;
import micro.gui.PhysicalGameStatePanel;
import micro.rts.GameState;
import micro.rts.PhysicalGameState;
import micro.rts.PlayerAction;
import micro.rts.units.Unit;
import micro.rts.units.UnitTypeTable;

/**
 * @author alicequint
 * 
 * @param <T> NN
 */
public class SinglePopulationCompetativeCoevolutionMicroRTSTask<T extends Network> extends SinglePopulationCoevolutionTask<T> implements NetworkTask, HyperNEATTask, MicroRTSInformation {

	private PhysicalGameState pgs;
	private PhysicalGameState initialPgs;
	private UnitTypeTable utt;
	private int MAXCYCLES = 5000;
	private JFrame w = null;
	private GameState gs;
	private boolean gameover;
	private int currentCycle;
	private boolean AiInitialized = false;

	private double averageUnitDifference;
	private int baseUpTime1;
	private int baseUpTime2;
	private int harvestingEfficiencyIndex1;
	private int harvestingEfficiencyIndex2;

	NNEvaluationFunction<T> ef1;
	NNEvaluationFunction<T> ef2;
	RTSFitnessFunction ff;

	HasEvaluationFunction ai1 = null;
	HasEvaluationFunction ai2 = null;

	public SinglePopulationCompetativeCoevolutionMicroRTSTask() {
		utt = new UnitTypeTable();
		//create objects
		try {
			ef1 = (NNEvaluationFunction<T>) ClassCreation.createObject(Parameters.parameters.classParameter("microRTSEvaluationFunction"));
			ef2 =(NNEvaluationFunction<T>) ClassCreation.createObject(Parameters.parameters.classParameter("microRTSOpponentEvaluationFunction"));
			ff = (RTSFitnessFunction) ClassCreation.createObject(Parameters.parameters.classParameter("microRTSFitnessFunction"));
			initialPgs = PhysicalGameState.load("data/microRTS/maps/" + Parameters.parameters.stringParameter("map"), utt);
		} catch (JDOMException | IOException | NoSuchMethodException e) {
			e.printStackTrace();
			System.exit(1);
		}
		for(String function : ff.getFunctions()){
			MMNEAT.registerFitnessFunction(function);
		}
		//create a copy of the physical game state so that it can be edited without changing initialPgs
		pgs = initialPgs.clone();
		ef1.givePhysicalGameState(pgs);
		ef2.givePhysicalGameState(pgs);
		ff.givePhysicalGameState(pgs);
		ff.setMaxCycles(5000);
		ff.giveTask(this);
		gs = new GameState(pgs, utt);
	}

	@Override
	public int numObjectives() {
		return ff.getFunctions().length;
	}

	@Override
	public double getTimeStamp() {
		return gs == null ? 0 : gs.getTime();
	}

	/**
	 * default behavior
	 */
	@Override
	public int numCPPNInputs() {
		return HyperNEATTask.DEFAULT_NUM_CPPN_INPUTS;
	}

	/**
	 * default behavior
	 */
	@Override
	public double[] filterCPPNInputs(double[] fullInputs) {
		return fullInputs;
	}

	@Override
	public List<Substrate> getSubstrateInformation() {
		return MicroRTSUtility.getSubstrateInformation(pgs);

	}

	@Override
	public List<Pair<String, String>> getSubstrateConnectivity() {
		return MicroRTSUtility.getSubstrateConnectivity(pgs);
	}

	/**
	 * @return ef1's sensor labels if it and ef2 are the same, or
	 * an array containing ef1's sensor labels followed by ef2's
	 * if they are different.
	 */
	@Override
	public String[] sensorLabels() {
		if(ef1.getClass() == ef2.getClass())
			return ef1.sensorLabels();
		else {
			String[] labels = new String[ef1.sensorLabels().length+ef2.sensorLabels().length];
			for(int i = 0; i < ef1.sensorLabels().length; i++){
				labels[i] = ef1.sensorLabels()[i];
			}
			for(int i = 0; i < ef2.sensorLabels().length; i++){
				labels[i+ef1.sensorLabels().length] = ef2.sensorLabels()[i];
			}
			return null;
		}
	}

	@Override
	public String[] outputLabels() {
		return new String[]{"Utility"};
	}

	/**
	 * all actions performed in a single evaluation of a genotype
	 * 
	 * @param individual
	 *            genotype to be evaluated
	 * @param num
	 *            which evaluation is currently being performed
	 * @return Combination of fitness scores (multiobjective possible), and
	 *         other scores (for tracking non-fitness data)
	 */
	@Override
	public ArrayList<Pair<double[], double[]>> evaluateGroup(ArrayList<Genotype<T>> group) {
		//reset:
		gameover = false;
		utt = new UnitTypeTable();
		averageUnitDifference = 0;
		currentCycle = 1;
		baseUpTime1 = 0;
		baseUpTime2 = 0;
		harvestingEfficiencyIndex1 = 0;
		harvestingEfficiencyIndex2 = 0;
		pgs = initialPgs.clone();
		gs = new GameState(pgs, utt);
		try {
			ai1 = (HasEvaluationFunction) ClassCreation.createObject(Parameters.parameters.classParameter("microRTSAgent"));
			ai2 = (HasEvaluationFunction) ClassCreation.createObject(Parameters.parameters.classParameter("microRTSOpponent"));
		} catch (NoSuchMethodException e2) {
			e2.printStackTrace();
			System.exit(1);
		}
		ai1.setEvaluationFunction(ef1);
		ai2.setEvaluationFunction(ef2);
		AiInitialized = true;
		ef1.setNetwork(group.get(0));
		ef2.setNetwork(group.get(1));
		if(CommonConstants.watch)
			w = PhysicalGameStatePanel.newVisualizer(gs,640,640,false,PhysicalGameStatePanel.COLORSCHEME_WHITE);
		ArrayList<Pair<double[], double[]>> a = new ArrayList<Pair<double[], double[]>>();
		a.add(MicroRTSUtility.oneEval((AI)ai1, (AI)ai2, this));
		return a;
	}

	private void initializeAI() {

	}

	@Override
	public double getAverageUnitDifference(){
		return averageUnitDifference;
	}

	@Override
	public int getBaseUpTime(){
		return baseUpTime1 - baseUpTime2;
	}

	@Override
	public int getHarvestingEfficiency(){
		return harvestingEfficiencyIndex1 - harvestingEfficiencyIndex2;
	}

	@Override
	public UnitTypeTable getUnitTypeTable() {
		return utt;
	}

	@Override
	public double[] minScores() {
		return new double[]{}; //TODO
	}

	@Override
	public int groupSize() {
		return 2;
	}

	@Override
	public int getResourceGainValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHarvestingEfficiency() {
		// TODO Auto-generated method stub
		
	}

}
