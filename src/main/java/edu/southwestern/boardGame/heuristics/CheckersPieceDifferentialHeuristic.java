package edu.southwestern.boardGame.heuristics;

import edu.southwestern.boardGame.checkers.CheckersState;


public class CheckersPieceDifferentialHeuristic<T extends CheckersState> implements BoardGameHeuristic<T>{

	@Override
	public double heuristicEvalution(T bgs) {
		assert bgs.getNumPlayers() == 2 : "PieceDifferentialBoardGameHeuristic only applies to two player games!";
		// Number of player 1's pieces minus number of player 2's pieces
		double friendlyPieces = 0;
		double OpPieces = 0;
		double score = 0;
		
		double[] board = bgs.getDescriptor();
		
		
		int x = 0;
		int y = 0;
		for(double d : board) {
			if(d == 0.5) {
				friendlyPieces++;
				if(y > 3 && y < 5) {
					friendlyPieces += 0.1;
				}
			}
			if(d == 0.75) {
				friendlyPieces += 1.5;
				if(y > 3 && y < 5) {
					friendlyPieces += 0.2;
				}
			}
			if(d == -0.5) {
				OpPieces++;
			}
			if(d == -0.75) {
				OpPieces += 1.5;
			}
			
			x++; //update x coordinate
			x = x%8; //wrap around
			if(x == 0) { //if x is wrapped around 
				y++; //increase y
			}
			
		}
		
		score = friendlyPieces - OpPieces;
		
		return score;
	}
	
}
