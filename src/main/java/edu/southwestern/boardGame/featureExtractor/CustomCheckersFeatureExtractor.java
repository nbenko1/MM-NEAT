package edu.southwestern.boardGame.featureExtractor;

import edu.southwestern.MMNEAT.MMNEAT;
import edu.southwestern.boardGame.TwoDimensionalBoardGameState;
import edu.southwestern.util.datastructures.ArrayUtil;
import edu.southwestern.boardGame.checkers.CheckersState;


public class CustomCheckersFeatureExtractor<T extends CheckersState> implements BoardGameFeatureExtractor<T> {

	T board;

	public double PLAYER_CHECK = 0.5;
	public double PLAYER_KING = 0.75;
	public double OP_CHECK = -0.5;
	public double OP_KING = -0.75;
	
	@SuppressWarnings("unchecked")
	public CustomCheckersFeatureExtractor(){
		this((T) MMNEAT.boardGame.getCurrentState());
	}
	
	public CustomCheckersFeatureExtractor(T bgs){
		board = bgs;
	}
	
	@Override
	public double[] getFeatures(T bgs) {
		double[] originalRaw = bgs.getDescriptor();
		
		double[] ourNewFeatures = new double[12];
		
		
		// 0 == player check
		// 1 == opponent check
		// 2 == player king
		// 3 == opponent king
		int x = 0;
		int y = 0;
		//starts in the bottom left, moves up
		int trace = 0;
		for (double d : originalRaw) { // loops through each square on the checkers board

			if (d == PLAYER_CHECK) { // Found a Player Check
				//playerChecks
				ourNewFeatures[0]++;
				if(x==0 || x==7 || y==0 || y == 7) { //if check is up against the wall
					ourNewFeatures[6]++; // number of save checks
				}
				if(y <= 5) {
					if(originalRaw[trace] + 9 == OP_CHECK ||originalRaw[trace] + 9 == OP_KING || originalRaw[trace] + 7 == OP_CHECK || originalRaw[trace] + 7 == OP_KING){
						ourNewFeatures[10]++;
					}
				}
			} else if (d == PLAYER_KING) { // Found a Player King
				//playerKings
				ourNewFeatures[2]++;
				if(x==0 || x==7 || y==0 || y == 7) { //if check is up against the wall
					ourNewFeatures[7]++; // number of save checks
				}
				if(y <= 5 && y >= 2) {
					if(originalRaw[trace] + 9 == OP_CHECK ||originalRaw[trace] + 9 == OP_KING || originalRaw[trace] + 7 == OP_CHECK || originalRaw[trace] + 7 == OP_KING){
						ourNewFeatures[10]++;
					}
					if(originalRaw[trace] - 7 == OP_CHECK ||originalRaw[trace] - 7 == OP_KING || originalRaw[trace] - 9 == OP_CHECK || originalRaw[trace] - 9 == OP_KING){
						ourNewFeatures[10]++;
					}
				}
			} else if (d == OP_CHECK) { // Found an Enemy Check
				//opponentChecks
				ourNewFeatures[3]++;
				if(x==0 || x==7 || y==0 || y == 7) { //if check is up against the wall
					ourNewFeatures[8]++; // number of save checks
				}
				if(y >= 2) {
					if(originalRaw[trace] - 9 == PLAYER_CHECK ||originalRaw[trace] - 9 == PLAYER_KING || originalRaw[trace] - 7 == PLAYER_CHECK || originalRaw[trace] - 7 == PLAYER_KING){
						ourNewFeatures[11]++;
					}
				}
			} else if (d == -0.75) { // Found an Enemy King
				//opponentKings
				ourNewFeatures[3]++;
				if(x==0 || x==7 || y==0 || y == 7) { //if check is up against the wall
					ourNewFeatures[9]++; // number of save checks
				}
				if(y <= 5 && y >= 2) {
					if(originalRaw[trace] + 9 == PLAYER_CHECK ||originalRaw[trace] + 9 == PLAYER_KING || originalRaw[trace] + 7 == PLAYER_CHECK || originalRaw[trace] + 7 == PLAYER_KING){
						ourNewFeatures[10]++;
					}
					if(originalRaw[trace] - 7 == PLAYER_CHECK ||originalRaw[trace] - 7 == PLAYER_KING || originalRaw[trace] - 9 == PLAYER_CHECK || originalRaw[trace] - 9 == PLAYER_KING){
						ourNewFeatures[11]++;
					}
				}
			}

			x++; //update x coordinate
			x = x%board.getBoardWidth(); //wrap around
			if(x == 0) { //if x is wrapped around 
				y++; //increase y
			}
			trace ++;
		}
		
		//difference between number of checks
		ourNewFeatures[4] = (ourNewFeatures[0]-ourNewFeatures[3]);
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
		String[] result = new String[(board.getBoardHeight() * board.getBoardWidth())+12];
		for(int j = 0; j < board.getBoardHeight(); j++) {
			for(int i = 0; i < board.getBoardWidth(); i++) {
				result[j * board.getBoardWidth() + i] = "Space ("+i+","+j+")";
			}
		}
		


		int boardSize = board.getBoardHeight() * board.getBoardWidth();
		result[boardSize]      = "PlayerChecks";
		result[boardSize + 1]  = "PlayerKings";
		result[boardSize + 2]  = "OpponentChecks";
		result[boardSize + 3]  = "OpponentKings";
		result[boardSize + 4]  = "ChecksDiff";
		result[boardSize + 5]  = "KingsDiff";
		result[boardSize + 6]  = "SafeCheck";
		result[boardSize + 7]  = "SafeKing";
		result[boardSize + 8]  = "OpSafeCheck";
		result[boardSize + 9]  = "OpSafeKing";
		result[boardSize + 10]  = "CheckAttack";
		result[boardSize + 11]  = "CheckDanger";



		return result;
	}
}
