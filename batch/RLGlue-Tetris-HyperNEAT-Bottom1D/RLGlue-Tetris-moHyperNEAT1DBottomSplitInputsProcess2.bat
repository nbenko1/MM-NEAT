cd ..
cd ..
java -jar target/MM-NEAT-0.0.1-SNAPSHOT.jar runNumber:%1 randomSeed:%1 base:tetris trials:5 maxGens:500 mu:50 io:true netio:true mating:true task:edu.southwestern.tasks.rlglue.tetris.HyperNEATTetrisTask cleanOldNetworks:true noisyTaskStat:edu.southwestern.util.stats.Average log:RL-moHyperNEAT1DBottomSplitInputsProcess2 saveTo:moHyperNEAT1DBottomSplitInputsProcess2 rlGlueEnvironment:org.rlcommunity.environments.tetris.Tetris rlGlueExtractor:edu.southwestern.tasks.rlglue.featureextractors.tetris.RawTetrisStateExtractor tetrisTimeSteps:true rlGlueAgent:edu.southwestern.tasks.rlglue.tetris.TetrisAfterStateAgent hyperNEAT:true genotype:edu.southwestern.evolution.genotypes.HyperNEATCPPNGenotype allowMultipleFunctions:true ftype:1 netChangeActivationRate:0.3 senseHolesDifferently:true  substrateMapping:edu.southwestern.networks.hyperneat.Bottom1DSubstrateMapping splitRawTetrisInputs:true HNTTetrisProcessDepth:2
