cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:toruspred teams:10 maxGens:500 mu:300 io:true netio:true mating:false fs:false task:edu.southwestern.tasks.gridTorus.cooperative.CooperativePredatorsVsStaticPreyTask log:TorusPred-cooperativeBalancedManyAgentsSenseTwo saveTo:cooperativeBalancedManyAgentsSenseTwo allowDoNothingActionForPredators:true torusPreys:5 torusPredators:5 staticPreyController:edu.southwestern.gridTorus.controllers.PreyFleeClosestPredatorController predatorCatchClose:false cooperativeBalancedIndividualAndTeamSelection:true torusSenseByProximity:true torusSenseTeammates:true ea:edu.southwestern.evolution.nsga2.CoevolutionNSGA2 experiment:edu.southwestern.experiment.evolution.LimitedMultiplePopulationGenerationalEAExperiment teamLog:true bestTeamScore:false predsSenseAllPreds:false predsSenseAllPrey:false numberPredsSensedByPreds:2 numberPreySensedByPreds:2