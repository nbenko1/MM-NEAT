REM Usage:   postBestObjectiveWatch.bat <experiment directory> <log prefix> <run type> <run number> <number of trials per individual>
REM Example: postBestObjectiveWatch.bat onelifeconflict OneLifeConflict OneModule 0 5
java -jar "target/MM-NEAT-0.0.1-SNAPSHOT.jar" runNumber:%4 parallelEvaluations:false base:%1 log:%2-%3 saveTo:%3 trials:%5 io:false netio:false onlyWatchPareto:true printFitness:true animateNetwork:false ucb1Evaluation:false showSubnetAnalysis:true monitorInputs:true experiment:edu.southwestern.experiment.post.BoardGameBenchmarkBestExperiment  logLock:true watchLastBest:false monitorSubstrates:true showVizDoomInputs:true showCPPN:true substrateGridSize:10 showWeights:true watch:true showNetworks:true inheritFitness:false boardGameOpponentHeuristic:boardGame.heuristics.StaticOthelloWPCHeuristic boardGameOpponent:boardGame.agents.treesearch.BoardGamePlayerMinimaxAlphaBetaPruning 

REM minimaxSearchDepth:5