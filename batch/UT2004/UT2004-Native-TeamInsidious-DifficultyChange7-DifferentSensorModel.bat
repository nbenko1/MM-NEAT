cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:ut2004 trials:3 maxGens:100 mu:10 io:true netio:true mating:true cleanOldNetworks:false log:UT2004-Native-TeamInsidious-DifficultyChange7-DiffSensorModel saveTo:Native-TeamInsidious-DifficultyChange7-DiffSensorModel experiment:edu.southwestern.experiment.evolution.LimitedSinglePopulationGenerationalEAExperiment ea:edu.southwestern.evolution.nsga2.NSGA2 genotype:edu.southwestern.evolution.genotypes.TWEANNGenotype crossover:edu.southwestern.evolution.crossover.network.TWEANNCrossover utDrive:C utPath:SCOPE2018\UT2004 utMap:DM-Insidious utSensorModel:edu.southwestern.tasks.ut2004.sensors.OpponentAndTeammateRelativeSensorModel utOutputModel:edu.southwestern.tasks.ut2004.actuators.OpponentRelativeMovementOutputModel utWeaponManager:edu.southwestern.tasks.ut2004.weapons.SimpleWeaponManager task:edu.southwestern.tasks.ut2004.UT2004ManyVsNativeBotsTeamDeathMatchTask parallelEvaluations:true utEvolvingBotSkill:7 utEvalMinutes:2 threads:10 utGameType:BotTeamGame utTeamSize:2 utNumNativeBots:2 utNativeBotSkill:7