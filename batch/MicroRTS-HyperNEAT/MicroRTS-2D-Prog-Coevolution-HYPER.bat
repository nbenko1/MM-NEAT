cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:microRTS trials:3 maxGens:500 mu:25 io:true netio:true mating:true task:edu.southwestern.tasks.microrts.SinglePopulationCompetativeCoevolutionMicroRTSTask cleanOldNetworks:true fs:false log:MicroRTS-CoHYPER saveTo:CoHYPER watch:false microRTSEvaluationFunction:edu.southwestern.tasks.microrts.evaluation.NN2DEvaluationFunction map:12x12/basesWorkers12x12.xml microRTSFitnessFunction:edu.southwestern.tasks.microrts.fitness.ProgressiveFitnessFunction hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 extraHNLinks:true