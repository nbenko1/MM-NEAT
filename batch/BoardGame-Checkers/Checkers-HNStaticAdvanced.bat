cd ..
cd ..
java -jar dist/MM-NEATv2.jar runNumber:%1 randomSeed:%1 base:checkers trials:10 maxGens:500 mu:100 io:true netio:true mating:true task:edu.utexas.cs.nn.tasks.boardGame.StaticOpponentBoardGameTask cleanOldNetworks:true fs:false log:Checkers-HNStaticAdvanced saveTo:HNStaticAdvanced boardGame:boardGame.checkers.Checkers boardGameOpponent:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning boardGameOpponentHeuristic:boardGame.heuristics.PieceDifferentialBoardGameHeuristic boardGamePlayer:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning genotype:edu.utexas.cs.nn.evolution.genotypes.HyperNEATCPPNGenotype hyperNEAT:true boardGameCheckersFitness:true boardGameSimpleFitness:false