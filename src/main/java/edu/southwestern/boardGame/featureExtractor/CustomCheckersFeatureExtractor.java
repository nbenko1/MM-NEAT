package edu.southwestern.boardGame.featureExtractor;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.boardGame.TwoDimensionalBoardGameState;
import edu.southwestern.util.datastructures.ArrayUtil;

public class CustomCheckersFeatureExtractor<T extends TwoDimensionalBoardGameState> extends TwoDimensionalRawBoardGameFeatureExtractor<T> {

	@SuppressWarnings("unchecked")
	public CustomCheckersFeatureExtractor(){
		this((T) MMNEAT.boardGame.getCurrentState());
	}
	
	public CustomCheckersFeatureExtractor(T bgs){
		super(bgs);
	}
	
	@Override
	public double[] getFeatures(T bgs) {
		double[] originalRaw = bgs.getDescriptor();
		
		double[] ourNewFeatures = new double[6]; //TODO
		
		

		// 0 == player check
		// 1 == opponent check
		// 2 == player king
		// 3 == opponent king
		for (double d : originalRaw) { // loops through each square on the checkers board
			if (d == 0) { // Found a Player Check
				//playerChecks
				ourNewFeatures[0]++;
			} else if (d == 2) { // Found a Player King
				//playerKings
				ourNewFeatures[1]++;
			} else if (d == 1) { // Found an Enemy Check
				//opponentChecks
				ourNewFeatures[2]++;
			} else if (d == 3) { // Found an Enemy King
				//opponentKings
				ourNewFeatures[3]++;
			}
		}
		
		//difference between number of checks
		ourNewFeatures[4] = (ourNewFeatures[0]-ourNewFeatures[2]);
		//difference between number of kings
		ourNewFeatures[5] = (ourNewFeatures[2]-ourNewFeatures[4]);
		
		//divides each element by max pieces to get values between (-1,1)
		for(int i = 0; i < ourNewFeatures.length; i ++) {
			ourNewFeatures[i] = (ourNewFeatures[i]/12);
		}
		
		
		double[] result = ArrayUtil.combineArrays(originalRaw, ourNewFeatures);
		
		return result;
	}

	/**
	 * Simply names all features according to the coordinates associated with each space.
	 */
	@Override
	public String[] getFeatureLabels() {
		String[] result = new String[(board.getBoardHeight() * board.getBoardWidth()) + 6];
		for(int j = 0; j < board.getBoardHeight(); j++) {
			for(int i = 0; i < board.getBoardWidth(); i++) {
				result[j * board.getBoardWidth() + i] = "Space ("+i+","+j+")";
			}
		}
		
		//String[] combinedLabels = new String[result.length+6]; // TODO
		

		int boardSize = board.getBoardHeight() * board.getBoardWidth();
		result[boardSize]      = "PlayerChecks";
		result[boardSize + 1]  = "PlayerKings";
		result[boardSize + 2]  = "OpponentChecks";
		result[boardSize + 3]  = "OpponentKings";
		result[boardSize + 4]  = "ChecksDiff";
		result[boardSize + 5]  = "KingsDiff";
		
		//doesn't work with strings
		//String[] combinedLabels = ArrayUtil.combineArrays(result, additionalFeatureLabels);
		
		return result;
	}
}
