cd ..
cd ..
java -jar dist/MM-NEATv2.jar runNumber:%1 randomSeed:%1 base:othello trials:10 maxGens:200 mu:50 io:true netio:true mating:true task:edu.utexas.cs.nn.tasks.boardGame.StaticOpponentBoardGameTask cleanOldNetworks:true fs:false log:Othello-HNWPCStaticWPC05Second saveTo:HNWPCStaticWPC05Second boardGame:boardGame.othello.Othello boardGameOpponent:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning boardGameOpponentHeuristic:boardGame.heuristics.StaticOthelloWPCHeuristic boardGamePlayer:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning minimaxSearchDepth:2 genotype:edu.utexas.cs.nn.evolution.genotypes.HyperNEATCPPNGenotype hyperNEAT:true minimaxSecondBestRate:0.05 boardGameOthelloFitness:true boardGameSimpleFitness:true boardGameWinPercentFitness:true randomArgMaxTieBreak:true evalReport:true heuristicOverrideTerminalStates:true HNProcessDepth:0 extraHNLinks:true