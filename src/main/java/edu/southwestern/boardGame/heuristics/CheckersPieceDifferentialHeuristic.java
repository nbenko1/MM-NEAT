package edu.southwestern.boardGame.heuristics;

import edu.southwestern.boardGame.checkers.CheckersState;


public class CheckersPieceDifferentialHeuristic<T extends CheckersState> implements BoardGameHeuristic<T>{

	@Override
	public double heuristicEvalution(T bgs) {
		double friendlyPieces = 0;
		double OpPieces = 0;
		
		double[] board = bgs.getDescriptor();
		
		
		int x = 0;
		int y = 0;
		for(double d : board) { //loops through each position on the board
			if(d == 0.5) { // friendly check
				friendlyPieces++;
				if(y > 3 && y < 5) {
					friendlyPieces += 0.1; //slight boost if near the center of the board
				}
			}
			if(d == 0.75) { // friendly King
				friendlyPieces += 1.5;  //kings worth more
				if(y > 3 && y < 5) {
					friendlyPieces += 0.2; //boost if near the center of the board
				}
			}
			if(d == -0.5) { //opponent check
				OpPieces++;
			}
			if(d == -0.75) { //opponent king
				OpPieces += 1.5;
			}
			
			x++; //update x coordinate
			x = x%8; //wrap around
			if(x == 0) { //if x is wrapped around 
				y++; //increase y
			}
			
		}
		
		
		return friendlyPieces - OpPieces; //returns difference between values
	
	}
	
}
