cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:toruspred trials:10 maxGens:500 mu:100 io:true netio:true mating:false fs:false task:edu.southwestern.tasks.gridTorus.TorusEvolvedPredatorsVsStaticPreyTask log:TorusPred-MultiIndivCCQModalMMD saveTo:MultiIndivCCQModalMMD allowDoNothingActionForPredators:true torusPreys:2 torusPredators:3 staticPreyController:edu.southwestern.gridTorus.controllers.PreyFleeClosestPredatorController predatorCatchClose:false predatorCatch:true predatorMinimizeIndividualDistance:true predatorsEatQuick:true torusSenseByProximity:true torusSenseTeammates:true mmdRate:0.1 logTWEANNData:true