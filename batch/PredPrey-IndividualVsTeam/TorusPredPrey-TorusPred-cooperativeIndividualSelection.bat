cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:toruspred teams:10 maxGens:500 mu:100 io:true netio:true mating:false fs:false task:edu.southwestern.tasks.gridTorus.cooperative.CooperativePredatorsVsStaticPreyTask log:TorusPred-cooperativeIndividualSelection saveTo:cooperativeIndividualSelection allowDoNothingActionForPredators:true torusPreys:2 torusPredators:3 staticPreyController:edu.southwestern.gridTorus.controllers.PreyFleeClosestPredatorController predatorCatchClose:false cooperativeIndividualSelection:true torusSenseByProximity:true torusSenseTeammates:true ea:edu.southwestern.evolution.nsga2.CoevolutionNSGA2 experiment:edu.southwestern.experiment.evolution.LimitedMultiplePopulationGenerationalEAExperiment teamLog:true bestTeamScore:false