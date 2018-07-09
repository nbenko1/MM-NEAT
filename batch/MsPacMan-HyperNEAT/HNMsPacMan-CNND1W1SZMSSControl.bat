cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:HNMsPacMan trials:3 maxGens:500 mu:10 io:true netio:true mating:true task:edu.southwestern.tasks.mspacman.MsPacManTask cleanOldNetworks:true pacManLevelTimeLimit:8000 pacmanInputOutputMediator:edu.southwestern.tasks.mspacman.sensors.MsPacManHyperNEATMediator trials:3 hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype fs:false allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 steps:500000 perLinkMutateRate:0.05 netLinkRate:0.4 netSpliceRate:0.2 crossoverRate:0.5 pacManFullScreenOutput:false pacmanBothThreatAndEdibleSubstrate:true pacmanFullScreenProcess:true inheritFitness:false log:HNMsPacMan-CNND1W1SZMSSControl saveTo:CNND1W1SZMSSControl HNProcessDepth:1 HNProcessWidth:1 convolution:true