cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:othello teams:10 maxGens:500 mu:100 io:true netio:true mating:true task:edu.southwestern.tasks.boardGame.MultiPopulationCompetativeCoevolutionBoardGameTask cleanOldNetworks:true fs:false log:Othello-MultiPopCompCoevolve saveTo:MultiPopCompCoevolve boardGame:boardGame.othello.Othello boardGameOpponentHeuristic:boardGame.heuristics.StaticOthelloWPCHeuristic boardGameOpponent:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning boardGamePlayer:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning minimaxSearchDepth:2  ea:edu.southwestern.evolution.nsga2.CoevolutionNSGA2 experiment:edu.southwestern.experiment.evolution.LimitedMultiplePopulationGenerationalEAExperiment teamLog:true