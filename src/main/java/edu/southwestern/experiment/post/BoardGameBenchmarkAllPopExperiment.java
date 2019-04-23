package edu.southwestern.experiment.post;

import java.util.ArrayList;
import java.util.List;

import edu.southwestern.boardGame.BoardGame;
import edu.southwestern.boardGame.BoardGameState;
import edu.southwestern.boardGame.agents.BoardGamePlayer;
import edu.southwestern.boardGame.agents.HeuristicBoardGamePlayer;
import edu.southwestern.boardGame.featureExtractor.BoardGameFeatureExtractor;
import edu.southwestern.boardGame.fitnessFunction.BoardGameFitnessFunction;
import edu.southwestern.boardGame.fitnessFunction.SimpleWinLoseDrawBoardGameFitness;
import edu.southwestern.boardGame.heuristics.NNBoardGameHeuristic;
import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.evolution.genotypes.Genotype;
import edu.southwestern.experiment.Experiment;
import edu.southwestern.networks.Network;
import edu.southwestern.parameters.CommonConstants;
import edu.southwestern.parameters.Parameters;
import edu.southwestern.tasks.CommonTaskUtil;
import edu.southwestern.tasks.SinglePopulationTask;
import edu.southwestern.tasks.boardGame.BoardGameUtil;
import edu.southwestern.util.ClassCreation;
import edu.southwestern.util.PopulationUtil;
import edu.southwestern.util.datastructures.Pair;
import edu.southwestern.util.graphics.DrawingPanel;

public class BoardGameBenchmarkAllPopExperiment<T extends Network, S extends BoardGameState> implements Experiment{
	
	protected ArrayList<Genotype<T>> population;
	protected SinglePopulationTask<T> task;

	
	private BoardGame<S> bg;
	private BoardGameFitnessFunction<S> selectionFunction;
	private BoardGameFeatureExtractor<S> featExtract;
	private HeuristicBoardGamePlayer<S> player;
	private BoardGamePlayer<S> opponent;
	
	private List<BoardGameFitnessFunction<S>> fitFunctions = new ArrayList<BoardGameFitnessFunction<S>>();
		
	/**
	 * Gets the best Coevolved BoardGamePlayer in a given Task; initializes the boardGame and fitnessFunction
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void init() {

		String lastSavedDir = Parameters.parameters.stringParameter("lastSavedDirectory");

		this.task = (SinglePopulationTask<T>) MMNEAT.task;
		if (lastSavedDir == null || lastSavedDir.equals("")) {
			System.out.println("Nothing to load");
			System.exit(1);
		} else {
			System.out.println("Loading: " + lastSavedDir);
			population = PopulationUtil.load(lastSavedDir);
			}
		
		try {
			bg = (BoardGame<S>) ClassCreation.createObject("boardGame");
			//selectionFunction = (BoardGameFitnessFunction<S>) ClassCreation.createObject("boardGameFitnessFunction");
			featExtract = (BoardGameFeatureExtractor<S>) ClassCreation.createObject("boardGameFeatureExtractor");
			player = (HeuristicBoardGamePlayer<S>) ClassCreation.createObject("boardGamePlayer"); // The Player
			opponent = (BoardGamePlayer<S>) ClassCreation.createObject("boardGameOpponent");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			System.exit(1);
		}
		

		// Just trying anything to make this work
		//MMNEAT.registerFitnessFunction(selectionFunction.getFitnessName());
		MMNEAT.registerFitnessFunction("Win/Loss?");
		
		// Add Other Scores here to keep track of other Fitness Functions
		fitFunctions.add(new SimpleWinLoseDrawBoardGameFitness<S>());
		
		for(BoardGameFitnessFunction<S> fit : fitFunctions){
			MMNEAT.registerFitnessFunction(fit.getFitnessName(), false);
		}
		
		fitFunctions.add(0, selectionFunction);
		
	}
	
	/**
	 * Pits the best Co-Evolved BoardGamePlayer against a Static Opponent
	 */
	@Override
	public void run() {
		
		for(Genotype<T> gene : population){
			
			DrawingPanel panel = null;
			DrawingPanel cppnPanel = null;
			
			if(CommonConstants.watch){
				Pair<DrawingPanel, DrawingPanel> drawPanels = CommonTaskUtil.getDrawingPanels(gene);
				
				panel = drawPanels.t1;
				cppnPanel = drawPanels.t2;
				
				panel.setVisible(true);
				if(cppnPanel != null) cppnPanel.setVisible(true);
			}
			
			
			player.setHeuristic((new NNBoardGameHeuristic<T,S>(gene.getId(), featExtract, gene)));
			@SuppressWarnings("unchecked")
			BoardGamePlayer<S>[] players = new BoardGamePlayer[]{player, opponent};

			for(int i = 0; i < CommonConstants.trials; i++){
				BoardGameUtil.playGame(bg, players, fitFunctions, new ArrayList<BoardGameFitnessFunction<S>>()); // No Other Scores
			}
			
			if (panel != null) {
				panel.dispose();
			} 
			if(cppnPanel != null) {
				cppnPanel.dispose();
			}
		}
	}

	/**
	 * Isn't called; the run() method terminates on its own
	 */
	@Override
	public boolean shouldStop() {
		return true;
	}

}
