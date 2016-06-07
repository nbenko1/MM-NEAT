REM Usage:   postBestObjectiveWatchCooperative.bat <experiment directory> <log prefix> <run type> <run number> <number of teams/trials per individual>
REM Example: postBestObjectiveWatchCooperative.bat onelifeconflict OneLifeConflict OneModule 0 5
java -jar "dist/MM-NEATv2.jar" runNumber:%4 parallelEvaluations:false base:%1 log:%2-%3 saveTo:%3 teams:%5 watch:true showNetworks:true io:false netio:false onlyWatchPareto:true printFitness:true animateNetwork:false ucb1Evaluation:false showSubnetAnalysis:true monitorInputs:true experiment:edu.utexas.cs.nn.experiment.ObjectiveBestTeamsExperiment logLock:true watchLastBestOfTeams:true rlGluePort:4200