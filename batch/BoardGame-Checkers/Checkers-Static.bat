cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:1 randomSeed:1 base:checkers trials:10 
maxGens:100 mu:10 io:true netio:true mating:true 
task:edu.southwestern.tasks.boardGame.StaticOpponentBoardGameTask 
cleanOldNetworks:true 
fs:false log:Checkers-Static 
saveTo:Static 
boardGame:edu.southwestern.boardGame.checkers.Checkers 
boardGameOpponent:edu.southwestern.boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning 
boardGameOpponentHeuristic:edu.southwestern.boardGame.heuristics.PieceDifferentialBoardGameHeuristic 
boardGamePlayer:edu.southwestern.boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning