cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:checkers trials:10 maxGens:500 mu:100 io:true netio:true mating:true task:edu.southwestern.tasks.boardGame.SinglePopulationCompetativeCoevolutionBoardGameTask cleanOldNetworks:true fs:false log:Checkers-SinglePopCompCoevolve saveTo:SinglePopCompCoevolve boardGame:boardGame.checkers.Checkers boardGameOpponent:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning boardGameOpponentHeuristic:boardGame.heuristics.PieceDifferentialBoardGameHeuristic boardGamePlayer:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning