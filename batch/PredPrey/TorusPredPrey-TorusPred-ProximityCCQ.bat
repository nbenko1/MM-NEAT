cd ..
cd ..
java -jar dist/MM-NEATv2.jar runNumber:%1 randomSeed:%1 base:toruspred trials:10 maxGens:500 mu:100 io:true netio:true mating:false fs:false task:edu.utexas.cs.nn.tasks.gridTorus.TorusEvolvedPredatorsVsStaticPreyTask log:TorusPred-ProximityCCQ saveTo:ProximityCCQ allowDoNothingActionForPredators:true torusPreys:2 torusPredators:3 staticPreyController:edu.utexas.cs.nn.gridTorus.controllers.PreyFleeClosestPredatorController predatorCatchClose:false predatorCatchCloseQuick:true torusSenseByProximity:true torusSenseTeammates:true