cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:microRTS trials:1 maxGens:500 mu:10 io:true netio:true mating:true task:edu.southwestern.tasks.microrts.MicroRTSTask cleanOldNetworks:true fs:false log:MicroRTS-2DSimpleHYPER saveTo:2DSimpleHYPER watch:false microRTSEvaluationFunction:edu.southwestern.tasks.microrts.evaluation.NN2DEvaluationFunction microRTSFitnessFunction:SimpleFitnessFunction hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3