cd ..
cd ..
java -jar dist/MM-NEATv2.jar runNumber:%1 randomSeed:%1 base:toruspred teams:10 maxGens:500 mu:100 io:true netio:true mating:false fs:false task:edu.utexas.cs.nn.tasks.gridTorus.cooperative.CooperativePredatorsVsStaticPreyTask log:TorusPred-CoOpHerdCCQ saveTo:CoOpHerdCCQ allowDoNothingActionForPredators:true torusPreys:2 torusPredators:3 staticPreyController:edu.utexas.cs.nn.gridTorus.controllers.PreyFleeClosestPredatorController predatorCatchClose:false predatorOneHerdCoOpCCQ:true torusSenseByProximity:true torusSenseTeammates:true ea:edu.utexas.cs.nn.evolution.nsga2.CoevolutionNSGA2 experiment:edu.utexas.cs.nn.experiment.LimitedMultiplePopulationGenerationalEAExperiment teamLog:true bestTeamScore:false